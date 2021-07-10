package com.example.demo.service;

import com.example.demo.domain.Blacklist;
import com.example.demo.domain.Contact;
import com.example.demo.domain.User;
import com.example.demo.dto.MessageRequestDTO;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.helper.PlaceholderHelper;
import com.example.demo.repository.BlacklistRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.MessageCommandServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class MessageCommandServiceTest {

    MessageCommandService messageCommandService;

    @Mock
    MessageRepository messageRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ContactRepository contactRepository;
    @Mock
    PlaceholderHelper placeholderHelper;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        messageCommandService = new MessageCommandServiceImpl(messageRepository, userRepository, contactRepository, placeholderHelper);
    }

    @Test(expected = RuntimeException.class)
    public void send_test_userNotFound(){
        MessageRequestDTO requestDTO = new MessageRequestDTO(anyString(), anyLong(), anyLong(), any());
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        messageCommandService.send(requestDTO);
    }

    @Test(expected = RuntimeException.class)
    public void send_test_contactNotFound(){
        MessageRequestDTO requestDTO = new MessageRequestDTO(anyString(), anyLong(), anyLong(), any());
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        messageCommandService.send(requestDTO);
    }

    @Test(expected = RuntimeException.class)
    public void send_test_placeHolderException(){
        MessageRequestDTO requestDTO = new MessageRequestDTO(anyString(), anyLong(), anyLong(), any());
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        Mockito.when(contactRepository.findById(anyLong())).thenReturn(Optional.of(new Contact()));
        messageCommandService.send(requestDTO);
        Mockito.verify(messageRepository, Mockito.atLeastOnce()).save(any());
    }

    @Test
    public void webhookMessage_test(){
        WebhookMessageDTO webhookMessageDTO = new WebhookMessageDTO();
        webhookMessageDTO.setName("name");
        webhookMessageDTO.setText("message");
        webhookMessageDTO.setEmail("email@superchat");
        messageCommandService.webhookMessage(webhookMessageDTO, any(), any(User.class));
        Mockito.verify(messageRepository, Mockito.atLeastOnce()).save(any());
    }
}
