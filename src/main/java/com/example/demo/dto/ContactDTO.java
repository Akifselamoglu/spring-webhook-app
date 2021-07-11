package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactDTO extends BaseDTO{
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "eMail cannot be null")
    @Email(message = "eMail is not valid")
    private String email;
    private String phoneNumber;

    public ContactDTO(){}

    public ContactDTO(Long id, String name, String email, String phoneNumber) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
