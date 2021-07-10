package com.example.demo.controller;

import com.example.demo.api.controller.ContactController;
import com.example.demo.dto.ContactDTO;
import com.example.demo.service.ContactCommandService;
import com.example.demo.service.ContactQueryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class ContactControllerTest {
    ContactController contactController;

    @Mock
    ContactQueryService contactQueryService;
    @Mock
    ContactCommandService contactCommandService;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        contactController = new ContactController(contactQueryService, contactCommandService);
    }

    @Test
    public void createContact_test(){
        contactController.createContact(any());
        Mockito.verify(contactCommandService, Mockito.atLeastOnce()).save(any());
    }

    @Test
    public void listContact_test(){
        Mockito.when(contactQueryService.getContactListByUserId(any())).thenReturn(new ArrayList<>());
        List<ContactDTO> response = contactController.listContact(any());
        Assert.assertNotNull(response);
    }
}
