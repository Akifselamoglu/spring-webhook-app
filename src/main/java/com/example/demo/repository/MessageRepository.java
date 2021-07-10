package com.example.demo.repository;

import com.example.demo.domain.Message;
import com.example.demo.dto.MessageResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select new com.example.demo.dto.MessageResponseDTO(m.user.name, m.contact.name) " +
            "from Message m " +
            "where m.user.id = :userId " +
            "group by m.user.name, m.contact.name")
    List<MessageResponseDTO> findAllConversationByUser(@Param("userId") Long userId);

    List<Message> findAllByUserIdAndContactId(Long userId, Long contactId);
}
