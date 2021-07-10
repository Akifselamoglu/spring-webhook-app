package com.example.demo.service.impl;

import com.example.demo.domain.Contact;
import com.example.demo.dto.ContactDTO;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactQueryServiceImpl implements ContactQueryService {
    private static Logger LOG = LoggerFactory.getLogger(ContactQueryServiceImpl.class);

    private final ContactRepository contactRepository;

    public ContactQueryServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDTO> getContactListByUserId(Long id){
        List<Contact> contacts = contactRepository.findAllByUserId(id);
        return contacts.stream().map(x-> new ContactDTO(x.getId(), x.getName(), x.getEmail(), x.getPhoneNumber()))
                .collect(Collectors.toList());
    }
}
