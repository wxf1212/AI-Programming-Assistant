package com.xiaofan.aiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiaofanManusTest {

    @Resource
    private XiaofanManus xiaofanManus;

    @Test
    public void run() {
        String userPrompt = """
                我想学习java虚拟机的架构，
                请结合一些网络图片，制定一份详细的学习计划，
                并以 PDF 格式输出""";
        String answer = xiaofanManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }
}