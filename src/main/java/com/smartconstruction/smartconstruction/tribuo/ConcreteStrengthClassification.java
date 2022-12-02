package com.smartconstruction.smartconstruction.tribuo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tribuo.*;
import org.tribuo.classification.Label;
import org.tribuo.classification.LabelFactory;
import org.tribuo.classification.evaluation.LabelEvaluation;
import org.tribuo.classification.evaluation.LabelEvaluator;
import org.tribuo.classification.xgboost.XGBoostClassificationTrainer;
import org.tribuo.data.csv.CSVIterator;
import org.tribuo.data.csv.CSVLoader;
import org.tribuo.datasource.ListDataSource;
import org.tribuo.evaluation.TrainTestSplitter;

import java.nio.file.Paths;

public class ConcreteStrengthClassification {
    private static final Logger logger = LoggerFactory.getLogger(ConcreteStrengthClassification.class);
    private static final String DATASET_PATH = "src/main/resources/datasets/concrete_dataset.csv";
//    private static final String DATASET_PATH = "src/main/resources/datasets/Concrete_Data.csv";

    protected Trainer<Label> trainer;
    protected Dataset<Label> trainSet;
    protected Dataset<Label> testSet;

    public static void main(String[] args) throws Exception {
        ConcreteStrengthClassification example = new ConcreteStrengthClassification();

        example.createTrainer();
        example.createDataSets();
        example.trainAndEvaluate();
    }


    public void createDataSets() throws Exception {
        logger.info("====CREATING DATASETS====");
        LabelFactory labelFactory = new LabelFactory();
        CSVLoader<Label> csvLoader = new CSVLoader<>(',', CSVIterator.QUOTE, labelFactory);
//        ListDataSource<Label> dataSource = csvLoader.loadDataSource(Paths.get(DATASET_PATH),"Strength");
        ListDataSource<Label> dataSource = csvLoader.loadDataSource(Paths.get(DATASET_PATH),"Concrete compressive strength(MPa, megapascals) ");

        TrainTestSplitter<Label> dataSplitter = new TrainTestSplitter<>(dataSource,0.7,1L);
        trainSet = new MutableDataset<>(dataSplitter.getTrain());
        logger.info(String.format("Train set size = %d, num of features = %d, classes = %s",
                trainSet.size(), trainSet.getFeatureMap().size(), trainSet.getOutputInfo().getDomain()));

        testSet = new MutableDataset<>(dataSplitter.getTest());
        logger.info(String.format("Test set size = %d, num of features = %d, classes = %s",
                testSet.size(), testSet.getFeatureMap().size(), testSet.getOutputInfo().getDomain()));
    }

    protected void createTrainer() {
        logger.info("Creating trainer....");
        trainer = new XGBoostClassificationTrainer(50);
    }

    public void trainAndEvaluate() throws Exception{
        logger.info("====Training Model====");
        Model<Label> model = trainer.train(trainSet);
        evaluate(model, "trainSet", trainSet);
        logger.info("Testing model...");
        evaluate(model, "testSet", testSet);
    }

    private void evaluate(Model<Label> model,String dataSetName, Dataset<Label> dataset){
        logger.info("===============");
        logger.info("Results for " + dataSetName + "--------------");
        LabelEvaluator evaluator =  new LabelEvaluator();
        LabelEvaluation evaluation = evaluator.evaluate(model, dataset);
        logger.info("Accuracy : " + evaluation.accuracy());
        logger.info("Confusion Matrix: \n" + evaluation.getConfusionMatrix());
    }

}




