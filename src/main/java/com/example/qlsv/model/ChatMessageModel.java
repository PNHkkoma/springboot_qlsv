package com.example.qlsv.model;

import com.example.qlsv.controler.MessageType;
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

    public MessageType getType() {
        return this.type;
    }
}
