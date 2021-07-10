package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactRequestDTO {
    @NotNull
    public Long userId;
    public String name;
    private String email;
    private String phoneNumber;
}
