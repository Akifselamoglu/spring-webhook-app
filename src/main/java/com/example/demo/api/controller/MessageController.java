package com.example.demo.api.controller;

import com.example.demo.api.constants.ApiGroups;
import com.example.demo.dto.MessageRequestDTO;
import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.service.MessageCommandService;
import com.example.demo.service.MessageQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.example.demo.api.constants.ApiGroups.RESPONSE_CONTENT_TYPE;

@RestController
@RequestMapping(value= ApiGroups.Message.API_URL, produces = RESPONSE_CONTENT_TYPE)
@Api(value= ApiGroups.Message.API_URL)
public class MessageController {
    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessageController(MessageCommandService messageCommandService, MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @GetMapping(value = "/listallconversation/{user}")
    @ApiOperation(value = "listAllConversation", notes = "list all coversation of user by id (only conversation not include outward messages)")
    public List<MessageResponseDTO> listAllConversationOfUser(@PathVariable("user") Long userId){
        return messageQueryService.findAllMessageByUser(userId);
    }

    @GetMapping(value = "/listsingleconversation/{user}/{contact}")
    @ApiOperation(value = "listSingleConversation", notes = "list single coversation of user with contact by ids ")
    public List<MessageResponseDTO> listMessagesBetweenUserAndContact(@PathVariable("user") Long userId, @PathVariable("contact") Long contactId){
        return messageQueryService.findAllMessageByUserAndContact(userId, contactId);
    }

    @PostMapping(value="/send")
    @ApiOperation(value = "sendMessage", notes = "Send message to specific contact")
    public void sendMessage(@RequestBody MessageRequestDTO requestDTO){
        messageCommandService.send(requestDTO);
    }

    // TODO: 10.7.2021 Send notification to all contact...
}
