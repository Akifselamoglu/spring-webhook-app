package com.example.demo.service;

import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.impl.MessageQueryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class MessageQueryServiceTest {
    MessageQueryService messageQueryService;

    @Mock
    MessageRepository messageRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        messageQueryService = new MessageQueryServiceImpl(messageRepository);
    }

    @Test
    public void findAllMessageByUser_test(){
        Mockito.when(messageRepository.findAllConversationByUser(anyLong())).thenReturn(new ArrayList<>());
        List<MessageResponseDTO> response = messageQueryService.findAllMessageByUser(anyLong());
        Assert.assertNotNull(response);
    }

    @Test
    public void findAllMessageByUserAndContact_test(){
        Mockito.when(messageRepository.findAllByUserIdAndContactId(anyLong(), anyLong())).thenReturn(new ArrayList<>());
        List<MessageResponseDTO> response = messageQueryService.findAllMessageByUserAndContact(anyLong(), anyLong());
        Assert.assertNotNull(response);
    }
}
