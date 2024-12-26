package com.example.project_1.repository;

import com.example.project_1.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        Ticket ticket1 = new Ticket(null, 101, 100.0, "Ticket1", Ticket.Status.PENDING, LocalDateTime.now().minusDays(1));
        Ticket ticket2 = new Ticket(null, 102, 200.0, "Ticket2", Ticket.Status.APPROVED, LocalDateTime.now());
        Ticket ticket3 = new Ticket(null, 101, 300.0, "Ticket3", Ticket.Status.DENIED, LocalDateTime.now().minusDays(2));

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);
    }

    @Test
    void testFindByUserId() {
        // Act
        List<Ticket> tickets = ticketRepository.findByUserId(101);

        // Assert
        assertEquals(2, tickets.size());
        assertTrue(tickets.stream().allMatch(ticket -> ticket.getUserId().equals(101)));
    }

    @Test
    void testFindByStatus() {
        // Act
        List<Ticket> tickets = ticketRepository.findByStatus("APPROVED");

        // Assert
        assertEquals(1, tickets.size());
        assertEquals(Ticket.Status.APPROVED, tickets.get(0).getStatus());
    }

    @Test
    void testFindBySubmissionDateAfter() {
        // Arrange
        LocalDateTime date = LocalDateTime.now().minusDays(1);

        // Act
        List<Ticket> tickets = ticketRepository.findBySubmissionDateAfter(date);

        // Assert
        assertEquals(1, tickets.size());
        assertTrue(tickets.get(0).getSubmissionDate().isAfter(date));
    }
}
