package com.example.demo.service;

import com.example.demo.dto.ContactDTO;

import java.util.List;

public interface ContactQueryService {
    List<ContactDTO> getContactListByUserId(Long id);
}
