package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Webhook extends BaseEntity{
    private String secretKey;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private boolean status;

    public Webhook(){}
    public Webhook(String secretKey, User user, boolean status) {
        this.secretKey = secretKey;
        this.user = user;
        this.status = status;
    }
}
