package com.example.demo.controller;

import com.example.demo.api.controller.MessageController;
import com.example.demo.api.controller.WebhookController;
import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.service.MessageCommandService;
import com.example.demo.service.MessageQueryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class MessageControllerTest {
    MessageController messageController;

    @Mock
    MessageCommandService messageCommandService;
    @Mock
    MessageQueryService messageQueryService;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        messageController = new MessageController(messageCommandService, messageQueryService);
    }

    @Test
    public void listAllConversationOfUser_test(){
        Mockito.when(messageQueryService.findAllMessageByUser(Mockito.any())).thenReturn(new ArrayList<>());
        List<MessageResponseDTO> response = messageController.listAllConversationOfUser(Mockito.any());
        Assert.assertNotNull(response);
    }

    @Test
    public void listMessagesBetweenUserAndContact_test(){
        Mockito.when(messageQueryService.findAllMessageByUserAndContact(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        List<MessageResponseDTO> response = messageController.listMessagesBetweenUserAndContact(Mockito.any(), Mockito.any());
        Assert.assertNotNull(response);
    }


    @Test
    public void sendMessage_test(){
        messageController.sendMessage(Mockito.any());
        Mockito.verify(messageCommandService, Mockito.atLeastOnce()).send(Mockito.any());
    }
}
