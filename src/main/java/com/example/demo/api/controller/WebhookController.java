package com.example.demo.api.controller;

import com.example.demo.api.constants.ApiGroups;
import com.example.demo.dto.WebhookMessageDTO;
import com.example.demo.dto.WebhookRequestDTO;
import com.example.demo.dto.WebhookResponseDTO;
import com.example.demo.service.WebhookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.api.constants.ApiGroups.RESPONSE_CONTENT_TYPE;

@RestController
@RequestMapping(value= ApiGroups.Webhook.API_URL,  produces = RESPONSE_CONTENT_TYPE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Api(value= ApiGroups.Webhook.API)
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping(value="/api/{secretKey}")
    @ApiOperation(value = "webhookMessageProcessor", notes = "Send message to client that has secretKey")
    public void webhookMessageProcessor(@RequestBody WebhookMessageDTO messageDTO, @PathVariable("secretKey") String secretKey,
                                        @RequestHeader(required = false) MultiValueMap<String, String> headerMap){
        webhookService.webhookMessageProcessor(messageDTO, secretKey, headerMap);
    }

    @PostMapping(value = "/generatekey")
    @ApiOperation(value = "generateKey", notes = "Generate new secret key for webhook api")
    public WebhookResponseDTO generateKey(@RequestBody WebhookRequestDTO requestDTO){
        return webhookService.generate(requestDTO);
    }
}
