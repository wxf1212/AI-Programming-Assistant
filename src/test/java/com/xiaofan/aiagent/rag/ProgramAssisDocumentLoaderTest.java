package com.xiaofan.aiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProgramAssisDocumentLoaderTest {

    @Resource
    private ProgramAssisDocumentLoader ProgramAssisDocumentLoader;

    @Test
    void loadMarkdowns() {
        ProgramAssisDocumentLoader.loadMarkdowns();
    }
}