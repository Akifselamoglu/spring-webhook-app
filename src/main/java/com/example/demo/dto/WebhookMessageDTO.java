package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WebhookMessageDTO {
    @NotNull(message = "Text cannot be null")
    private String text;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "eMail cannot be null")
    @Email(message = "eMail is not valid")
    private String email;
    private String phone;
    private String ipAddress;
}
