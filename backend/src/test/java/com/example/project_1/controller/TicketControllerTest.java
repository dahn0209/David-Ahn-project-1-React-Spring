package com.example.project_1.controller;

import com.example.project_1.entity.Ticket;
import com.example.project_1.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTickets() {
        // Arrange
        Ticket ticket1 = new Ticket(1, 101, 100.0, "Description1", Ticket.Status.PENDING, LocalDateTime.now());
        Ticket ticket2 = new Ticket(2, 102, 200.0, "Description2", Ticket.Status.APPROVED, LocalDateTime.now());
        when(ticketService.getAllTickets()).thenReturn(Arrays.asList(ticket1, ticket2));

        // Act
        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        // Assert
        assertEquals(2, response.getBody().size());
        verify(ticketService, times(1)).getAllTickets();
    }

    @Test
    void testGetTicketByIdFound() {
        // Arrange
        Integer ticketId = 1;
        Ticket ticket = new Ticket(1, 101, 100.0, "Description1", Ticket.Status.PENDING, LocalDateTime.now());
        when(ticketService.getTicketById(ticketId)).thenReturn(Optional.of(ticket));

        // Act
        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        // Assert
        assertEquals(ticketId, response.getBody().getTicketId());
        verify(ticketService, times(1)).getTicketById(ticketId);
    }

    @Test
    void testGetTicketByIdNotFound() {
        // Arrange
        Integer ticketId = 1;
        when(ticketService.getTicketById(ticketId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        // Assert
        assertNull(response.getBody());
        assertEquals(404, response.getStatusCodeValue());
        verify(ticketService, times(1)).getTicketById(ticketId);
    }

    @Test
    void testCreateTicket() {
        // Arrange
        Ticket newTicket = new Ticket(101, 100.0, "New Ticket");
        when(ticketService.createTicket(newTicket)).thenReturn(newTicket);

        // Act
        ResponseEntity<Ticket> response = ticketController.createTicket(newTicket);

        // Assert
        assertNotNull(response.getBody());
        assertEquals("New Ticket", response.getBody().getDescription());
        verify(ticketService, times(1)).createTicket(newTicket);
    }

    @Test
    void testDeleteTicketSuccess() {
        // Arrange
        Integer ticketId = 1;
        doNothing().when(ticketService).deleteTicket(ticketId);

        // Act
        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(ticketService, times(1)).deleteTicket(ticketId);
    }

    @Test
    void testDeleteTicketNotFound() {
        // Arrange
        Integer ticketId = 1;
        doThrow(new RuntimeException("Ticket not found")).when(ticketService).deleteTicket(ticketId);

        // Act
        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(ticketService, times(1)).deleteTicket(ticketId);
    }
}
