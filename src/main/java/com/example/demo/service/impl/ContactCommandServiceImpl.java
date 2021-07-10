package com.example.demo.service.impl;

import com.example.demo.domain.Contact;
import com.example.demo.domain.User;
import com.example.demo.dto.ContactRequestDTO;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ContactCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactCommandServiceImpl implements ContactCommandService {
    private static Logger LOG = LoggerFactory.getLogger(ContactCommandServiceImpl.class);

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactCommandServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public void save(ContactRequestDTO requestDTO){
        Optional<User> user = userRepository.findById(requestDTO.getUserId());
        if(user.isEmpty())
            throw new RuntimeException("User is not found");
        if(contactRepository.findByEmailAndUserId(requestDTO.getEmail(), requestDTO.getUserId()) != null)
            throw new RuntimeException("This contact already created before");

        Contact contact = new Contact(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPhoneNumber(), user.get());
        contactRepository.save(contact);
        LOG.info("Contact succesfully created for user: " + user.get().getName());
    }
}
