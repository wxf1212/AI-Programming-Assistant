package com.xiaofan.aiagent.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "编程助手.pdf";
        String content = "这是测试pdf生成";
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}