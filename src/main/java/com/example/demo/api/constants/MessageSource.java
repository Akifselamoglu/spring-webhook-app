package com.example.demo.api.constants;

public enum MessageSource {
    INTERNAL("INTERNAL"),
    OUTWARD("OUTWARD");

    private final String code;

    private MessageSource(String code){this.code = code;}

    public String getCode() {
        return code;
    }
}
