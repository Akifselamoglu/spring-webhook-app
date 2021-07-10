package com.example.demo.service;

import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.dto.WebhookRequestDTO;
import com.example.demo.dto.WebhookResponseDTO;
import org.springframework.util.MultiValueMap;

public interface WebhookService {
    WebhookResponseDTO generate(WebhookRequestDTO requestDTO);
    void webhookMessageProcessor(WebhookMessageDTO messageDTO, String secretKey, MultiValueMap<String, String> headerMap);
}
