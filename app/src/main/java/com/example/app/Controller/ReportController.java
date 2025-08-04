package com.example.app.Controller;

import com.example.app.service.DataBaseReportGeneratorService;
import com.example.app.service.ExcelGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/reports")
@RestController
public class ReportController {


    @Autowired
    private DataBaseReportGeneratorService reportService;
    @Autowired
    private ExcelGeneratorService excelReportService;


    @GetMapping("/posts")
    public ResponseEntity<byte[]> getUserReport() throws Exception {
        byte[] pdfBytes = reportService.exportReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=posts.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


    @GetMapping("/posts.xlsx")
    public ResponseEntity<byte[]> downloadExcel() throws Exception {


        byte[] excelFile = excelReportService.generatePostReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=posts.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelFile);
    }

}


