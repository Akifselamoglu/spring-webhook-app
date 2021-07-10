package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactDTO extends BaseDTO{
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String phoneNumber;

    public ContactDTO(Long id, String name, String email, String phoneNumber) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
