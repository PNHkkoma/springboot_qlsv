package com.example.qlsv.handler;

import com.example.qlsv.ChatMessageOuterClass;
import com.example.qlsv.ChatMessageOuterClass.ChatMessage;
import com.example.qlsv.controler.MessageType;
import com.example.qlsv.model.ChatMessageModel;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Xử lý khi một kết nối WebSocket được thiết lập
        System.out.println("WebSocket connection established: " + session.getId());
    }
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            // Xử lý tin nhắn dạng binary (protobuf) ở đây
            byte[] messageBytes = message.getPayload().array();
            ChatMessageOuterClass.ChatMessage protoMessage = ChatMessageOuterClass.ChatMessage.parseFrom(messageBytes);

            // Bổ sung mã xử lý protobuf ở đây nếu cần
        } catch (InvalidProtocolBufferException e) {
            // Xử lý ngoại lệ khi giải mã protobuf thất bại
            e.printStackTrace();
        }
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Deserialize message using protobuf
        byte[] messageBytes = (byte[]) message.getPayload();
        ChatMessageOuterClass.ChatMessage protoMessage = ChatMessageOuterClass.ChatMessage.parseFrom(messageBytes);

        // Handle the protobuf message
        System.out.println("Deserialized message: " + protoMessage.getContent());

        // Trả lời lại client
        ChatMessageModel chatMessageModel = ChatMessageModel.builder()
                .type(MessageType.valueOf(protoMessage.getType().name()))
                .content(protoMessage.getContent())
                .sender(protoMessage.getSender())
                .build();

        // Process the received message

        // Reply to the client
        ChatMessageOuterClass.ChatMessage replyMessage = ChatMessageOuterClass.ChatMessage.newBuilder()
                .setType(ChatMessageOuterClass.ChatMessage.MessageType.CHAT)
                .setContent("Server received: " + protoMessage.getContent())
                .setSender("Server")
                .build();

        // Serialize and send the reply using protobuf
        byte[] replyBytes = replyMessage.toByteArray();
        session.sendMessage(new BinaryMessage(replyBytes));
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Xử lý khi một kết nối WebSocket bị đóng
        log.info("WebSocket connection closed: " + session.getId());
    }
}
