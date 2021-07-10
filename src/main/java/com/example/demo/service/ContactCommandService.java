package com.example.demo.service;

import com.example.demo.dto.ContactRequestDTO;

public interface ContactCommandService {
    void save(ContactRequestDTO requestDTO);
}
