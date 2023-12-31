package com.example.qlsv.model;

import com.example.qlsv.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageModel {

    private MessageType type;
    private String content;
    private String sender;
}
