package com.example.demo.service.impl;

import com.example.demo.domain.Message;
import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {
    private static Logger LOG = LoggerFactory.getLogger(MessageQueryServiceImpl.class);

    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageResponseDTO> findAllMessageByUser(Long id){
         return messageRepository.findAllConversationByUser(id);
    }

    public List<MessageResponseDTO> findAllMessageByUserAndContact(Long userId, Long contactId){
        List<Message> messages = messageRepository.findAllByUserIdAndContactId(userId,contactId);
        return messages.stream().map(x->  new MessageResponseDTO(x.getText(), x.getUser().getName(), x.getContact().getName(), x.getMessageDirection(), x.getCreateTime()))
                .collect(Collectors.toList());
    }
}
