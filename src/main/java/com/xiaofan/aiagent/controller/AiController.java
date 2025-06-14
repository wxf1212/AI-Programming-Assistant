package com.xiaofan.aiagent.controller;

import com.xiaofan.aiagent.agent.XiaofanManus;
import com.xiaofan.aiagent.app.ProgramAssis;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private ProgramAssis ProgramAssis;

    @Resource
    private ToolCallback[] allTools;

    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * 同步调用 AI 编程助手应用
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping("/program_assis/chat/sync")
    public String doChatWithProgramAssisSync(String message, String chatId) {
        return ProgramAssis.doChat(message, chatId);
    }

    /**
     * SSE 流式调用 AI 编程助手应用
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/program_assis/chat/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithProgramAssisSSE(String message, String chatId) {
        return ProgramAssis.doChatByStream(message, chatId);
    }

    /**
     * SSE 流式调用 AI 编程助手应用，和上面的是一样的功能
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/program_assis/chat/server_sent_event")
    public Flux<ServerSentEvent<String>> doChatWithProgramAssisServerSentEvent(String message, String chatId) {
        return ProgramAssis.doChatByStream(message, chatId)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }

    /**
     * SSE 流式调用 AI 编程助手应用
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/program_assis/chat/sse_emitter")
    public SseEmitter doChatWithProgramAssisServerSseEmitter(String message, String chatId) {
        // 创建一个超时时间较长的 SseEmitter
        SseEmitter sseEmitter = new SseEmitter(180000L); // 3 分钟超时
        // 获取 Flux 响应式数据流并且直接通过订阅推送给 SseEmitter
        ProgramAssis.doChatByStream(message, chatId)
                .subscribe(//处理每条消息
                        chunk -> {
                    try {
                        sseEmitter.send(chunk);
                    } catch (IOException e) {
                        sseEmitter.completeWithError(e);
                    }
                }, sseEmitter::completeWithError, sseEmitter::complete);
        // 返回
        return sseEmitter;
    }

    /**
     * 流式调用 Manus 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping("/manus/chat")
    public SseEmitter doChatWithManus(String message) {
        XiaofanManus xiaofanManus = new XiaofanManus(allTools, dashscopeChatModel);
        return xiaofanManus.runStream(message);
    }
}
