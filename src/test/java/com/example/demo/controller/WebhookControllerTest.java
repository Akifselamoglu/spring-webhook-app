package com.example.demo.controller;

import com.example.demo.api.controller.WebhookController;
import com.example.demo.dto.WebhookResponseDTO;
import com.example.demo.service.WebhookService;
import com.example.demo.service.impl.WebhookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WebhookControllerTest {

    WebhookController webhookController;

    @Mock
    WebhookService webhookService;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        webhookController = new WebhookController(webhookService);
    }

    @Test
    public void webhookMessageProcessor_test(){
        webhookController.webhookMessageProcessor(Mockito.any(),Mockito.any(),Mockito.any());
        Mockito.verify(webhookService, Mockito.atLeastOnce()).webhookMessageProcessor(Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    public void generateKey_test(){
        Mockito.when(webhookService.generate(Mockito.any())).thenReturn(new WebhookResponseDTO("Ata", "123ko6ko90cda011500"));
        WebhookResponseDTO response = webhookController.generateKey(Mockito.any());
        Assert.assertNotNull(response);
    }
}
