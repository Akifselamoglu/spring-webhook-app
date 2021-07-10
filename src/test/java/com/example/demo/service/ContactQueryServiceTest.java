package com.example.demo.service;

import com.example.demo.domain.Contact;
import com.example.demo.domain.User;
import com.example.demo.dto.ContactDTO;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.impl.ContactQueryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class ContactQueryServiceTest {
    ContactQueryService contactQueryService;

    @Mock
    ContactRepository contactRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        contactQueryService = new ContactQueryServiceImpl(contactRepository);
    }

    @Test
    public void getContactListByUserId_test(){
        List<Contact> contacts = new ArrayList<>();
        User user = new User();
        user.setName("user");
        contacts.add(new Contact("contact", "email@super", null, user));
        Mockito.when(contactRepository.findAllByUserId(any())).thenReturn(contacts);
        List<ContactDTO> response = contactQueryService.getContactListByUserId(any());
        Assert.assertNotNull(response);
    }
}
