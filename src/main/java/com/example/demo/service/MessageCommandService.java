package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.MessageRequestDTO;
import com.example.demo.dto.WebhookMessageDTO;

public interface MessageCommandService {
    void send(MessageRequestDTO messageRequestDTO);
    void webhookMessage(WebhookMessageDTO messageDTO, String ipAddress, User user);
}
