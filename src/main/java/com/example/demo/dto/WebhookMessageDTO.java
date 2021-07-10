package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WebhookMessageDTO {
    @NotNull
    private String text;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String phone;
    private String ipAddress;
}
