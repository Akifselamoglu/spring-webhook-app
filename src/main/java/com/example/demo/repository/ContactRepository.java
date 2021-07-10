package com.example.demo.repository;

import com.example.demo.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByUserId(Long userId);
    Contact findByEmailAndUserId(String email, Long userId);
}
