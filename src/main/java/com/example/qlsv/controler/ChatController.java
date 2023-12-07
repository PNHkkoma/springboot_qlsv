package com.example.qlsv.controler;

import com.example.qlsv.model.ChatMessageModel;
import com.example.qlsv.ChatMessageOuterClass.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(
            @Payload ChatMessageModel chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        ChatMessage protoMessage = ChatMessage.newBuilder()
                .setType(ChatMessage.MessageType.CHAT)
                .setContent(chatMessage.getContent())
                .setSender(chatMessage.getSender())
                .build();

        // Serialize đối tượng thành mảng byte
        byte[] messageBytes = protoMessage.toByteArray();

        // Gửi mảng byte thông qua WebSocket
        messagingTemplate.convertAndSend("/topic/public", messageBytes);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageModel addUser(
            @Payload ChatMessageModel chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}