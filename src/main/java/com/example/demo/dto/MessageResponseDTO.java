package com.example.demo.dto;

import com.example.demo.api.constants.MessageDirection;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class MessageResponseDTO extends BaseDTO {
    private String text;
    private String userName;
    private String contactName;
    private MessageDirection messageDirection;
    private OffsetDateTime createDate;

    public MessageResponseDTO(String text, String userName, String contactName, MessageDirection messageDirection, OffsetDateTime createDate) {
        this.text = text;
        this.userName = userName;
        this.contactName = contactName;
        this.messageDirection = messageDirection;
        this.createDate = createDate;
    }

    public MessageResponseDTO(String userName, String contactName) {
        this.userName = userName;
        this.contactName = contactName;
    }
}
