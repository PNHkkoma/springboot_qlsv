package com.example.qlsv.controler;


import com.example.qlsv.model.ChatMessage;
import com.example.qlsv.model.Student;
import com.example.qlsv.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final StudentRepository studentRepository;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        // Lấy thông tin sinh viên từ DB bằng studentId
        Optional<Student> studentOptional = studentRepository.findByStudentName(chatMessage.getSenderName());
        String username = chatMessage.getSenderName();

        // Kiểm tra nếu sinh viên tồn tại trong DB
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Cập nhật thông tin của tin nhắn trước khi gửi đi
            chatMessage.setSenderName(student.getStudentName());
            chatMessage.setContent("[" + student.getStudentName() + "]: " + chatMessage.getContent());
        }else {
            // Ném ra một exception nếu username là null

            log.info("không có gì sất"+username);
        }

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        String username = chatMessage.getSenderName();
        Optional<Student> studentOptional = studentRepository.findByStudentName(username);
        if (studentOptional.isPresent()) {
            headerAccessor.getSessionAttributes().put("username", username);
        } else {
            // Ném ra một exception nếu username là null

            log.info("không có gì thật"+username);
        }

        return chatMessage;
    }
}