package com.example.demo.dto;

import com.example.demo.api.constants.MessageDirection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MessageRequestDTO extends BaseDTO{
    @NotNull
    private String text;
    @NotNull
    private Long userId;
    @NotNull
    private Long contactId;
    @NotNull
    private MessageDirection messageDirection;

    public MessageRequestDTO(){}

    public MessageRequestDTO(String text, Long userId, Long contactId, MessageDirection messageDirection) {
        this.text = text;
        this.userId = userId;
        this.contactId = contactId;
        this.messageDirection = messageDirection;
    }
}
