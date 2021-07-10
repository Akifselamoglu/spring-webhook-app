package com.example.demo.service;

import com.example.demo.domain.Blacklist;
import com.example.demo.domain.User;
import com.example.demo.domain.Webhook;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.dto.WebhookRequestDTO;
import com.example.demo.dto.WebhookResponseDTO;
import com.example.demo.repository.BlacklistRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WebhookRepository;
import com.example.demo.service.impl.WebhookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;

public class WebhookServiceTest {
    WebhookService webhookService;

    @Mock
    WebhookRepository webhookRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    MessageCommandService messageCommandService;
    @Mock
    BlacklistRepository blacklistRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        webhookService = new WebhookServiceImpl(webhookRepository, userRepository, messageCommandService, blacklistRepository);
    }

    @Test(expected = RuntimeException.class)
    public void webhookMessageProcessor_test_statusPassive(){
        Webhook webhook = new Webhook();
        webhook.setStatus(false);
        Mockito.when(webhookRepository.findBySecretKey(any())).thenReturn(webhook);
        webhookService.webhookMessageProcessor(any(), any(), any());
    }

    @Test
    public void webhookMessageProcessor_test(){
        Webhook webhook = new Webhook();
        webhook.setStatus(true);
        webhook.setSecretKey("key");
        webhook.setUser(new User());
        Mockito.when(webhookRepository.findBySecretKey(webhook.getSecretKey())).thenReturn(webhook);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        String ipAddress = "ip";
        multiValueMap.put("host", Arrays.asList(ipAddress));
        WebhookMessageDTO messageDTO = new WebhookMessageDTO();
        webhookService.webhookMessageProcessor(messageDTO, webhook.getSecretKey(), multiValueMap);
        Mockito.verify(messageCommandService, Mockito.atLeastOnce()).webhookMessage(messageDTO, ipAddress, webhook.getUser());
    }

    @Test(expected = RuntimeException.class)
    public void create_test_userNotFound(){
        WebhookRequestDTO requestDTO = new WebhookRequestDTO();
        requestDTO.setUserId(1L);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        webhookService.generate(requestDTO);
    }

    @Test(expected = RuntimeException.class)
    public void create_test_blacklist(){
        WebhookRequestDTO requestDTO = new WebhookRequestDTO();
        requestDTO.setUserId(1L);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(blacklistRepository.findByIpAddressAndStatus(any() ,anyBoolean())).thenReturn(new Blacklist());
        webhookService.generate(requestDTO);
    }

    @Test
    public void create_test(){
        WebhookRequestDTO requestDTO = new WebhookRequestDTO();
        requestDTO.setUserId(1L);

        User user = new User();
        user.setName("Mustafa");
        Optional<User> userOpt = Optional.of(user);
        Mockito.when(userRepository.findById(any())).thenReturn(userOpt);

        WebhookResponseDTO response = webhookService.generate(requestDTO);

        Mockito.verify(webhookRepository, Mockito.atLeastOnce()).save(any());
        Assert.assertNotNull(response);
    }
}
