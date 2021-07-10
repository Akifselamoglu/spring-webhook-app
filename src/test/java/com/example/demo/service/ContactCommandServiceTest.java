package com.example.demo.service;

import com.example.demo.domain.Contact;
import com.example.demo.domain.User;
import com.example.demo.dto.ContactRequestDTO;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.ContactCommandServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class ContactCommandServiceTest {

    ContactCommandService contactCommandService;

    @Mock
    ContactRepository contactRepository;
    @Mock
    UserRepository userRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        contactCommandService = new ContactCommandServiceImpl(contactRepository, userRepository);
    }

    @Test
    public void save_test(){
        ContactRequestDTO requestDTO = new ContactRequestDTO();
        requestDTO.setUserId(1L);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        Mockito.when(contactRepository.findByEmailAndUserId(any(), any())).thenReturn(null);
        contactCommandService.save(requestDTO);
        Mockito.verify(contactRepository, Mockito.atLeastOnce()).save(any(Contact.class));
    }

    @Test(expected = RuntimeException.class)
    public void save_test_userEmpty(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        contactCommandService.save(any());
    }

    @Test(expected = RuntimeException.class)
    public void save_test_contactCreatedBefore(){
        ContactRequestDTO requestDTO = new ContactRequestDTO();
        requestDTO.setUserId(1L);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        Mockito.when(contactRepository.findByEmailAndUserId(any(), any())).thenReturn(null);
        contactCommandService.save(any());
    }
}
