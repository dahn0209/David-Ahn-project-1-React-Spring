package com.example.project_1.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testTicketConstructorAndGetters() {
        // Arrange
        Integer ticketId = 1;
        Integer userId = 101;
        Double amount = 150.0;
        String description = "Test description";
        Ticket.Status status = Ticket.Status.APPROVED;
        LocalDateTime submissionDate = LocalDateTime.now();

        // Act
        Ticket ticket = new Ticket(ticketId, userId, amount, description, status, submissionDate);

        // Assert
        assertEquals(ticketId, ticket.getTicketId());
        assertEquals(userId, ticket.getUserId());
        assertEquals(amount, ticket.getAmount());
        assertEquals(description, ticket.getDescription());
        assertEquals(status, ticket.getStatus());
        assertEquals(submissionDate, ticket.getSubmissionDate());
    }

    @Test
    void testSetters() {
        // Arrange
        Ticket ticket = new Ticket();
        LocalDateTime now = LocalDateTime.now();

        // Act
        ticket.setTicketId(2);
        ticket.setUserId(102);
        ticket.setAmount(200.0);
        ticket.setDescription("Updated description");
        ticket.setStatus(Ticket.Status.DENIED);
        ticket.setSubmissionDate(now);

        // Assert
        assertEquals(2, ticket.getTicketId());
        assertEquals(102, ticket.getUserId());
        assertEquals(200.0, ticket.getAmount());
        assertEquals("Updated description", ticket.getDescription());
        assertEquals(Ticket.Status.DENIED, ticket.getStatus());
        assertEquals(now, ticket.getSubmissionDate());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Ticket ticket1 = new Ticket(1, 101, 150.0, "Test description", Ticket.Status.APPROVED, now);
        Ticket ticket2 = new Ticket(1, 101, 150.0, "Test description", Ticket.Status.APPROVED, now);
        Ticket ticket3 = new Ticket(2, 102, 200.0, "Other description", Ticket.Status.PENDING, now);

        // Act & Assert
        assertEquals(ticket1, ticket2);
        assertNotEquals(ticket1, ticket3);
        assertEquals(ticket1.hashCode(), ticket2.hashCode());
        assertNotEquals(ticket1.hashCode(), ticket3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Ticket ticket = new Ticket(1, 101, 150.0, "Test description", Ticket.Status.APPROVED, now);

        // Act
        String toString = ticket.toString();

        // Assert
        assertTrue(toString.contains("Test description"));
        assertTrue(toString.contains("APPROVED"));
    }
}
