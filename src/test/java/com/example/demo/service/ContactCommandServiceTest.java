package com.example.demo.service;

import com.example.demo.domain.Contact;
import com.example.demo.domain.User;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ContactRequestDTO;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.ContactCommandServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
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
        List<ContactDTO> contactDTOList = new ArrayList<>();
        ContactDTO contactDTO = new ContactDTO();
        contactDTOList.add(contactDTO);
        requestDTO.setContactList(contactDTOList);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        Mockito.when(contactRepository.findByEmailAndUserId(any(), any())).thenReturn(null);
        contactCommandService.save(requestDTO);
        Mockito.verify(contactRepository, Mockito.atLeastOnce()).save(any(Contact.class));
    }

    @Test(expected = RuntimeException.class)
    public void save_test_userEmpty(){
        ContactRequestDTO requestDTO = new ContactRequestDTO();
        requestDTO.setUserId(1L);
        Mockito.when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.empty());
        contactCommandService.save(requestDTO);
    }

    @Test(expected = RuntimeException.class)
    public void save_test_contactCreatedBefore(){
        ContactRequestDTO requestDTO = new ContactRequestDTO();
        requestDTO.setUserId(1L);
        List<ContactDTO> contactDTOList = new ArrayList<>();
        ContactDTO contactDTO = new ContactDTO();
        contactDTOList.add(contactDTO);
        requestDTO.setContactList(contactDTOList);
        Mockito.when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.of(new User()));
        Mockito.when(contactRepository.findByEmailAndUserId(any(), any())).thenReturn(new Contact());
        contactCommandService.save(requestDTO);
    }
}
