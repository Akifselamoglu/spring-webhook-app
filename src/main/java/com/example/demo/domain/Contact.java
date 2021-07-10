package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Contact extends BaseEntity{
    private String name;
    private String email;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Contact(){}

    public Contact(String name, String email, String phoneNumber, User user) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
