package com.example.app.service;

import com.example.app.DTO.ReportPosts;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseReportGeneratorService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generatePostPdf(List<ReportPosts> posts) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream("/reports/posts.jrxml");
        JasperReport report = JasperCompileManager.compileReport(reportStream);


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(posts);


        HashMap<String, Object> params = new HashMap<>();
        params.put("generatedBy", "postsApp");


        JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);


        return JasperExportManager.exportReportToPdf(print);
    }

    public byte[] exportReport() throws Exception {

        Resource resource = resourceLoader.getResource("classpath:reports/posts.jrxml");

        if (!resource.exists()) {
            throw new IllegalStateException("JRXML file not found at classpath:/reports/posts.jrxml");
        }

        try (
                InputStream reportStream = resource.getInputStream();
                Connection conn = DataSourceUtils.getConnection(dataSource)
        ) {

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);


            Map<String, Object> parameters = new HashMap<>();


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);


            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Error generating Jasper report: " + e.getMessage(), e);
        }
    }
}
