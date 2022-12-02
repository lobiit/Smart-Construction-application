package com.smartconstruction.smartconstruction.api.controller;

import com.oracle.labs.mlrg.olcut.provenance.ProvenanceUtil;

import com.smartconstruction.smartconstruction.api.dtos.request.ConcreteStrengthRequest;
import com.smartconstruction.smartconstruction.api.dtos.response.ConcreteStrengthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tribuo.Example;
import org.tribuo.Model;
import org.tribuo.Prediction;
import org.tribuo.impl.ArrayExample;
import org.tribuo.regression.RegressionFactory;
import org.tribuo.regression.Regressor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * Thss  class creates an api for you to be ab;e to parse your data to the traines model,
 * through clients i.e browser, postman, appp etc.
 * **/
@RestController
@RequestMapping("/predict")
public class ConcreteStrengthPredictionController {
    private static Logger logger = LoggerFactory.getLogger(ConcreteStrengthPredictionController.class);
    private static final String MODEL_PATH = "src/main/resources/models/concrete-strength-regressor.ser";
    private final Model<Regressor> model;
//    @Autowired
//    private PredictionRepository predictionRepository;

    public ConcreteStrengthPredictionController() throws Exception {
        this.model = loadModel();
    }


    @SuppressWarnings("unchecked")
    private Model<Regressor> loadModel() throws Exception {

        File modelFile = new File(MODEL_PATH);

        Model<Regressor> loadedModel;
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(modelFile)))) {
            loadedModel = (Model<Regressor>) ois.readObject();
        }

        if (!loadedModel.validate(Regressor.class)) {
            throw new Exception("The saved model is not a regressor!");
        }

        logger.info("Dataset Provenance: --------------------");
        logger.info(ProvenanceUtil.formattedProvenanceString(loadedModel.getProvenance().getDatasetProvenance()));
        logger.info("Trainer Provenance: --------------------");
        logger.info(ProvenanceUtil.formattedProvenanceString(loadedModel.getProvenance().getTrainerProvenance()));

        return loadedModel;
    }

    @PostMapping("/concrete-strength")
    public ConcreteStrengthResponse concreteStrenth(@RequestBody ConcreteStrengthRequest concreteStrengthRequest) {
        logger.info("Received " + concreteStrengthRequest);

        Regressor outputPlaceHolder = RegressionFactory.UNKNOWN_REGRESSOR;

        Example<Regressor> example = new ArrayExample<>(
                outputPlaceHolder,
                ConcreteStrengthRequest.featureNames,
                concreteStrengthRequest.getFeatureValues()
        );

//        predictionRepository.save(concreteStrengthRequest);

        Prediction<Regressor> prediction = model.predict(example);
        double result = prediction.getOutput().getValues()[0];

        logger.info("Prediction result = " + result);

        return new ConcreteStrengthResponse(result);
    }
}
