package com.xiaofan.aiagent.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceDownloadToolTest {

    @Test
    public void testDownloadResource() {
        ResourceDownloadTool tool = new ResourceDownloadTool();
        String url = "https://pic2.zhimg.com/v2-d72420fc2d1a1b097971a4ced17a0bfb_1200x500.jpg";
        String fileName = "study.jpg";
        String result = tool.downloadResource(url, fileName);
        assertNotNull(result);
    }
}