package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;

import org.springframework.jdbc.datasource.DataSourceUtils;


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


        InputStream reportStream = getClass().getResourceAsStream("/reports/posts.jrxml");

        if (reportStream == null) {
            throw new IllegalStateException("JRXML not found in classpath under /reports/posts.jrxmljrxml");
        }

        JasperReport report = JasperCompileManager.compileReport(reportStream);


        Map<String, Object> params = new HashMap<>();


        Connection conn = DataSourceUtils.getConnection(dataSource);


        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, conn);


        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
