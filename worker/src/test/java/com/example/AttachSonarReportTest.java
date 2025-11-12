package com.example;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AttachSonarReportTest {

    @Test
    void attachSonarSummaryIfExists() throws Exception {
        File file = new File("target/allure-results/sonarqube-summary.json");

        assertTrue(file.exists(), 
            "SonarQube summary JSON file should exist before attaching to Allure.");

        // Attach to Allure only if file exists
        if (file.exists()) {
            Allure.addAttachment(
                "SonarQube Quality Gate Summary",
                new FileInputStream(file)
            );
        }
    }
}
