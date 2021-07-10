package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.domain.Webhook;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.dto.WebhookRequestDTO;
import com.example.demo.dto.WebhookResponseDTO;
import com.example.demo.repository.BlacklistRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WebhookRepository;
import com.example.demo.service.MessageCommandService;
import com.example.demo.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Optional;
import java.util.UUID;


@Service
public class WebhookServiceImpl implements WebhookService {
    private static Logger LOG = LoggerFactory.getLogger(WebhookServiceImpl.class);

    private final WebhookRepository webhookRepository;
    private final UserRepository userRepository;
    private final MessageCommandService messageCommandService;
    private final BlacklistRepository blacklistRepository;

    public WebhookServiceImpl(WebhookRepository webhookRepository, UserRepository userRepository,
                              MessageCommandService messageCommandService, BlacklistRepository blacklistRepository) {
        this.webhookRepository = webhookRepository;
        this.userRepository = userRepository;
        this.messageCommandService = messageCommandService;
        this.blacklistRepository = blacklistRepository;
    }

    // TODO: 10.7.2021 implement retry limit
    public void webhookMessageProcessor(WebhookMessageDTO messageDTO, String secretKey, MultiValueMap<String, String> headerMap) {
        Webhook webhook = webhookRepository.findBySecretKey(secretKey);
        if(webhook==null || !webhook.isStatus())
            throw new RuntimeException("There is no active webhook user with this secret key " + secretKey);

        String ipAddress = headerMap.getFirst("host");
        if(blacklistRepository.findByIpAddressAndStatus(ipAddress, true)!=null)
            throw new RuntimeException("This ip does not have access authorization");

        messageCommandService.webhookMessage(messageDTO, ipAddress, webhook.getUser());
    }

    public WebhookResponseDTO generate(WebhookRequestDTO requestDTO){
        Optional<User> user = userRepository.findById(requestDTO.getUserId());
        if(user.isEmpty())
            throw new RuntimeException("User is not found");

        Webhook webhook = new Webhook(UUID.randomUUID().toString().replaceAll("-",""), user.get(), true);
        webhookRepository.save(webhook);
        LOG.info("New webhook created...");
        return new WebhookResponseDTO(user.get().getName(), webhook.getSecretKey());
    }
}
