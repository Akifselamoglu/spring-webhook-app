package com.example.demo.api.controller;

import com.example.demo.api.constants.ApiGroups;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ContactRequestDTO;
import com.example.demo.service.ContactCommandService;
import com.example.demo.service.ContactQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.api.constants.ApiGroups.RESPONSE_CONTENT_TYPE;

@RestController
@RequestMapping(value= ApiGroups.Contact.API_URL, produces = RESPONSE_CONTENT_TYPE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Api(value=ApiGroups.Contact.API)
public class ContactController {
    private final ContactQueryService contactQueryService;
    private final ContactCommandService contactCommandService;

    public ContactController(ContactQueryService contactQueryService, ContactCommandService contactCommandService) {
        this.contactQueryService = contactQueryService;
        this.contactCommandService = contactCommandService;
    }

    @PostMapping(value="/create")
    @ApiOperation(value = "", notes = "Create contact list for specified user")
    public void createContact(@RequestBody ContactRequestDTO requestDTO){
        contactCommandService.save(requestDTO);
    }

    @GetMapping(value = "/list/{userId}")
    @ApiOperation(value = "", notes = "List all contact by userId")
    public List<ContactDTO>  listContact(@PathVariable("userId") Long userId){
        return contactQueryService.getContactListByUserId(userId);
    }

}
