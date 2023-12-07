package com.example.qlsv.model;

<<<<<<< HEAD
import com.example.qlsv.enums.MessageType;
=======
import com.example.qlsv.controler.MessageType;
>>>>>>> 076be80 (websocket v3)
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
<<<<<<< HEAD
=======

    public MessageType getType() {
        return this.type;
    }
>>>>>>> 076be80 (websocket v3)
}
