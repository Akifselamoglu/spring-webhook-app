package com.example.demo.helper;

import com.example.demo.api.handler.ValidationException;
import com.example.demo.domain.Contact;
import com.example.demo.service.impl.ContactCommandServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.api.constants.Constants.Placeholder.*;

@Component
public class PlaceholderHelper {
    private static Logger LOG = LoggerFactory.getLogger(PlaceholderHelper.class);

    public static final String COIN_PRICE_API_ADDRESS = "https://api.coindesk.com/v1/bpi/currentprice.json";
    final Pattern pattern = Pattern.compile("#\\w+");

    public String messageProecessor(String message, Contact contact){
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            switch (matcher.group().toUpperCase()) {
                case CONTACT:
                    message = contact(message, contact);
                    continue;
                case BITCOINPRICE:
                    message = bitcoin(message);
                    continue;
                case TODAY:
                    message = today(message);
                    continue;
                case TOMORROW:
                    message = tomorrow(message);
                    continue;
                case YESTERDAY:
                    message = yesterday(message);
                    continue;
                default:
            }
        }
        return message;
    }

    private String contact(String message, Contact contact){
        return message.replaceAll(CONTACT, contact.getName());
    }

    private String bitcoin(String message){
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(COIN_PRICE_API_ADDRESS, HttpMethod.GET, null, String.class);
        if(responseEntity.getStatusCode().isError())
            throw new RuntimeException("Bitcoin service provider not respond...");
        String resp = responseEntity.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(resp);
            String replacement = rootNode.path("bpi").path("USD").get("rate").toString();
            return message.replaceAll(BITCOINPRICE, replacement);
        } catch (JsonProcessingException e) {
            throw new ValidationException("Bitcoin placeholder not processed and message sending stopped. Please try again late.",LOG);
        }
    }

    private String today(String message){
        return message.replaceAll(TODAY,
                DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH).format(LocalDate.now()));
    }

    private String tomorrow(String message){
        return message.replaceAll(TOMORROW,
                DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH).format(LocalDate.now().plusDays(1L)));
    }

    private String yesterday(String message){
        return message.replaceAll(YESTERDAY,
                DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH).format(LocalDate.now().minusDays(1L)));
    }

}
