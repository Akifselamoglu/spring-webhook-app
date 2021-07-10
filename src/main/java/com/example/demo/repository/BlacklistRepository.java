package com.example.demo.repository;

import com.example.demo.domain.Webhook;
import com.example.demo.domain.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Blacklist findByIpAddressAndStatus(String ipAddress, boolean status);
}
