package com.smartconstruction.smartconstruction;

import com.smartconstruction.smartconstruction.tribuo.ConcreteStrengthClassification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartConstructionApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SmartConstructionApplication.class, args);

    }

    public static String helloWorld(String str) {
        return str;
    }

}
