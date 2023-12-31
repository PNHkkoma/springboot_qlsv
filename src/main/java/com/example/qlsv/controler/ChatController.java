package com.example.qlsv.controler;

import com.example.qlsv.model.ChatMessageModel;
import com.example.qlsv.ChatMessageOuterClass.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(
            @Payload ChatMessageModel chatMessage
    ) {
        log.info("tao nhận được cái này: {}{}", chatMessage.getContent(),chatMessage.getSender());
        ChatMessage protoMessage = ChatMessage.newBuilder()
                .setType(ChatMessage.MessageType.CHAT)
                .setContent(chatMessage.getContent())
                .setSender(chatMessage.getSender())
                .build();
        byte[] messageBytes = protoMessage.toByteArray();
        messagingTemplate.convertAndSend("/topic/public", messageBytes);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageModel addUser(
            @Payload ChatMessageModel chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        log.info("thằng này đã đc đk", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}