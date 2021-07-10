package com.example.demo.service;

import com.example.demo.dto.MessageResponseDTO;

import java.util.List;

public interface MessageQueryService {
    List<MessageResponseDTO> findAllMessageByUser(Long id);
    List<MessageResponseDTO> findAllMessageByUserAndContact(Long userId, Long contactId);
}
