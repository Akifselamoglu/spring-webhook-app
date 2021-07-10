package com.example.demo.service.impl;

import com.example.demo.api.constants.MessageSource;
import com.example.demo.domain.Contact;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.dto.MessageRequestDTO;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.helper.PlaceholderHelper;
import com.example.demo.repository.BlacklistRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MessageCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private static Logger LOG = LoggerFactory.getLogger(MessageCommandServiceImpl.class);

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final PlaceholderHelper placeholderHelper;

    public MessageCommandServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ContactRepository contactRepository,
                                     PlaceholderHelper placeholderHelper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.placeholderHelper = placeholderHelper;
    }

    public void send(MessageRequestDTO messageRequestDTO) {
        Optional<User> user = userRepository.findById(messageRequestDTO.getUserId());
        if (user.isEmpty())
            throw new RuntimeException("User not found");

        Optional<Contact> contact = contactRepository.findById(messageRequestDTO.getContactId());
        if (contact.isEmpty())
            throw new RuntimeException("Contact not found");

        String text = placeholderHelper.messageProecessor(messageRequestDTO.getText(), contact.get());

        Message message = new Message(text, messageRequestDTO.getMessageDirection(), MessageSource.INTERNAL, user.get(), contact.get());
        messageRepository.save(message);
        LOG.info("New message saved. MessageId: " + message.getId());
    }

    public void webhookMessage(WebhookMessageDTO messageDTO, String ipAddress, User user){
        Message message = new Message(messageDTO.getText(), MessageSource.OUTWARD, ipAddress, user);
        messageRepository.save(message);
        LOG.info("New message saved via webhook request. MessageId: " + message.getId());
    }
}
