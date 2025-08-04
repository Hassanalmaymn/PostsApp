package com.example.app.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataBaseReportGeneratorService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] exportReport() throws Exception {
        // Load JRXML from resources (classpath:/reports/posts.jrxml)
        Resource resource = resourceLoader.getResource("classpath:reports/posts.jrxml");

        if (!resource.exists()) {
            throw new IllegalStateException("JRXML file not found at classpath:/reports/posts.jrxml");
        }

        try (
                InputStream reportStream = resource.getInputStream();
                Connection conn = DataSourceUtils.getConnection(dataSource)
        ) {
            // Compile the JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Empty parameters map (or add your own params)
            Map<String, Object> parameters = new HashMap<>();

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // Export to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Error generating Jasper report: " + e.getMessage(), e);
        }
    }
}
