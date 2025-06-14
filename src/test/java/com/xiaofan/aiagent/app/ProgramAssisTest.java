package com.xiaofan.aiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ProgramAssisTest {

    @Resource
    private ProgramAssis ProgramAssis;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "Hello";
        String answer = ProgramAssis.doChat(message, chatId);
        // 第二轮
        message = "我叫魏小凡";
        answer = ProgramAssis.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我叫什么？刚跟你说过，帮我回忆一下";
        answer = ProgramAssis.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是魏小凡，我想学习java，但我不知道怎么做";
        ProgramAssis.LoveReport loveReport = ProgramAssis.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我想学习C语言中volatile关键字的一些知识";
        String answer = ProgramAssis.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithTools() {
        // 测试联网搜索问题的答案
        testMessage("我想学习java，推荐几个适合的课程？");

        // 测试网页抓取
        testMessage("请为我抓取www.baidu.com网页");

        // 测试资源下载：图片下载
        testMessage("直接下载一张java虚拟机架构图片为文件");

        // 测试终端操作：执行代码
        testMessage("执行 Python3 脚本来生成数据分析报告");

        // 测试文件操作：保存用户档案
        testMessage("保存我的编程文档为文件");

        // 测试 PDF 生成
        testMessage("生成一份java学习路线的pdf文件");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = ProgramAssis.doChatWithTools(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithMcp() {
        String chatId = UUID.randomUUID().toString();
        // 测试图片搜索 MCP
        String message = "帮我搜索一些java学习路线的图片";
        String answer =  ProgramAssis.doChatWithMcp(message, chatId);
        Assertions.assertNotNull(answer);
    }
}
