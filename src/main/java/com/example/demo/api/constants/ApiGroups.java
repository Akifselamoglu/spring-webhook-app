package com.example.demo.api.constants;

import org.springframework.http.MediaType;

public final class ApiGroups {
    public static final String API_CHAT = "/chat";
    public static final String RESPONSE_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    public static class Contact{
        public static final String API = "contact-api";
        public static final String API_URL = API_CHAT + "/contact";
    }

    public static class Message{
        public static final String API = "message-api";
        public static final String API_URL = API_CHAT + "/message";
    }

    public static class Webhook{
        public static final String API = "webhook-api";
        public static final String API_URL = API_CHAT + "/webhook";
    }


}
