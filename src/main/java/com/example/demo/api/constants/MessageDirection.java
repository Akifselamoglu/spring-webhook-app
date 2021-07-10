package com.example.demo.api.constants;

public enum MessageDirection {
    TO_USER("TO_USER"),
    FROM_USER("FROM_USER");

    private final String code;

    private MessageDirection(String code){this.code = code;}

    public String getCode() {
        return code;
    }
}
