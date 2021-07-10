package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebhookResponseDTO {
    private String userName;
    private String secretKey;

    public WebhookResponseDTO(String userName, String secretKey) {
        this.userName = userName;
        this.secretKey = secretKey;
    }
}
