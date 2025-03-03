package com.spring.lanxr.ai.godaitest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")

public class ChatController {
    private final ChatClient chatClient;

    // 构造方法注入 ChatClient.Builder，用于构建 ChatClient 实例
    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @CrossOrigin
    @GetMapping("/chat")
    public ResponseEntity<Flux<String>> chat(@RequestParam(value = "message") String message) {
        try {
            // 调用 ChatClient 生成响应，并以 Flux<String>（响应流）形式返回

            Flux<String> response = chatClient.prompt(message).stream().content();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
