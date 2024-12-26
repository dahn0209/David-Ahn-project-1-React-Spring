package com.example.project_1.repository;

import com.example.project_1.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Logger logger = LoggerFactory.getLogger(TicketRepository.class);

    List<Ticket> findByUserId(Integer userId);

    List<Ticket> findByStatus(String status);

    List<Ticket> findBySubmissionDateAfter(LocalDateTime submissionDate);

    default List<Ticket> logAndFetchByStatus(String status) {
        List<Ticket> tickets = findByStatus(status);
        return tickets;
    }
}
