package com.example.app.service;

import com.example.app.model.Post;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ExcelGeneratorService {
    private final PostService postService;

    public ExcelGeneratorService(PostService postService) {
        this.postService = postService;
    }


    public byte[] generatePostReport() {
        List<Post> posts = postService.getAllPosts();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Posts");

            // Create a bold style for headers
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Header
            Row headerRow = sheet.createRow(0);
            Cell titleHeader = headerRow.createCell(0);
            titleHeader.setCellValue("Title");
            titleHeader.setCellStyle(headerStyle);

            Cell contentHeader = headerRow.createCell(1);
            contentHeader.setCellValue("Content");
            contentHeader.setCellStyle(headerStyle);
            Cell createdAtHeader = headerRow.createCell(2);
            createdAtHeader.setCellValue("created At");
            createdAtHeader.setCellStyle(headerStyle);

            // Data rows
            int rowIdx = 1;
            for (Post post : posts) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(post.getTitle());
                row.createCell(1).setCellValue(post.getContent());
                row.createCell(2).setCellValue(java.sql.Timestamp.valueOf(post.getCreated_at()));
            }

            // Auto-size columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            // Return byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel report", e);
        }
    }
}




