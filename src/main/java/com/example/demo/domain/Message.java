package com.example.demo.domain;

import com.example.demo.api.constants.MessageDirection;
import com.example.demo.api.constants.MessageSource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Message extends BaseEntity{
    private String text;
    @Enumerated(EnumType.STRING)
    private MessageDirection messageDirection;
    @Enumerated(EnumType.STRING)
    private MessageSource messageSource;
    private String ipAddress;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    public Message(){}

    public Message(String text, MessageDirection messageDirection, MessageSource messageSource, User user, Contact contact) {
        this.text = text;
        this.messageDirection = messageDirection;
        this.messageSource = messageSource;
        this.user = user;
        this.contact = contact;
    }

    public Message(String text, MessageSource messageSource, String ipAddress, User user) {
        this.text = text;
        this.messageSource = messageSource;
        this.ipAddress = ipAddress;
        this.user = user;
    }
}
