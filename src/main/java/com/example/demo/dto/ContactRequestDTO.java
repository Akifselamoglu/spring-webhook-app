package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ContactRequestDTO implements Serializable {
    @NotNull(message = "User id cannot be null")
    public Long userId;
    private List<ContactDTO> contactList;
}
