package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WebhookRequestDTO {
    @NotNull(message = "User id cannot be null")
    private Long userId;
}
