package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.Webhook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookRepository extends JpaRepository<Webhook, Long> {
    Webhook findBySecretKey(String secretKey);
}
