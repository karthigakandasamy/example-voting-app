package com.example;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AttachSonarReportTest {

    @Test
    void attachSonarSummaryWithDetails() throws Exception {
        File file = new File("target/allure-results/sonarqube-summary.json");

        if (!file.exists()) {
            System.out.println("SonarQube summary file not found — skipping attachment.");
            assertTrue(true, "SonarQube summary not found — skipping attachment.");
            return;
        }

        // Attach JSON file
        Allure.addAttachment("SonarQube Quality Gate Summary", new FileInputStream(file));

        // Parse JSON content to show readable info in the report
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        JSONObject json = new JSONObject(content);

        String projectKey = json.optString("projectKey", "unknown");
        String qualityGateStatus = json.optString("qualityGateStatus", "unknown");

        Allure.step("Project Key: " + projectKey);
        Allure.step("Quality Gate Status: " + qualityGateStatus);

        // Add assertion if quality gate failed
        assertTrue(!"ERROR".equalsIgnoreCase(qualityGateStatus),
            "SonarQube Quality Gate failed!");
    }
}
