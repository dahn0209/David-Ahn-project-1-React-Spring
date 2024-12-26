package com.example.project_1.service;

import com.example.project_1.entity.Ticket;
import com.example.project_1.entity.Account;
import com.example.project_1.repository.TicketRepository;
import com.example.project_1.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTickets() {
        // Arrange
        Ticket ticket1 = new Ticket(1, 101, 100.0, "Description1", Ticket.Status.PENDING, LocalDateTime.now());
        Ticket ticket2 = new Ticket(2, 102, 200.0, "Description2", Ticket.Status.APPROVED, LocalDateTime.now());

        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        // Act
        List<Ticket> tickets = ticketService.getAllTickets();

        // Assert
        assertEquals(2, tickets.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testGetTicketByIdFound() {
        // Arrange
        Integer ticketId = 1;
        Ticket ticket = new Ticket(1, 101, 100.0, "Description1", Ticket.Status.PENDING, LocalDateTime.now());
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        // Act
        Optional<Ticket> result = ticketService.getTicketById(ticketId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(ticketId, result.get().getTicketId());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testGetTicketByIdNotFound() {
        // Arrange
        Integer ticketId = 1;
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        // Act
        Optional<Ticket> result = ticketService.getTicketById(ticketId);

        // Assert
        assertFalse(result.isPresent());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testCreateTicket() {
        // Arrange
        Ticket newTicket = new Ticket(101, 100.0, "New ticket");
        when(ticketRepository.save(newTicket)).thenReturn(new Ticket(1, 101, 100.0, "New ticket", Ticket.Status.PENDING, LocalDateTime.now()));

        // Act
        Ticket createdTicket = ticketService.createTicket(newTicket);

        // Assert
        assertNotNull(createdTicket.getTicketId());
        assertEquals("New ticket", createdTicket.getDescription());
        verify(ticketRepository, times(1)).save(newTicket);
    }

    @Test
    void testUpdateTicketSuccess() {
        // Arrange
        Integer ticketId = 1;
        Ticket existingTicket = new Ticket(ticketId, 101, 100.0, "Old description", Ticket.Status.PENDING, LocalDateTime.now());
        
        Ticket updatedDetails = new Ticket(ticketId, 101, 150.0, "Updated description", Ticket.Status.APPROVED, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(existingTicket));
        when(ticketRepository.save(existingTicket)).thenReturn(updatedDetails);

        // Act
        Ticket updatedTicket = ticketService.updateTicket(ticketId, updatedDetails);

        // Assert
        assertNotNull(updatedTicket);
        assertEquals(Ticket.Status.APPROVED, updatedTicket.getStatus());
        verify(ticketRepository, times(1)).findById(ticketId);
        verify(ticketRepository, times(1)).save(existingTicket);
    }

    @Test
    void testUpdateTicketNotFound() {
        // Arrange
        Integer ticketId = 1;
        Ticket updatedDetails = new Ticket(ticketId, 101, 150.0, "Updated description", Ticket.Status.PENDING, LocalDateTime.now());


        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.updateTicket(ticketId, updatedDetails));
        assertEquals("Ticket not found with ID: " + ticketId, exception.getMessage());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testUpdateTicketStatusSuccess() {
        // Arrange
        Integer ticketId = 1;
        Integer managerId = 1;
        Ticket existingTicket = new Ticket(ticketId, 101, 100.0, "Old description", Ticket.Status.PENDING, LocalDateTime.now());
        Account manager = new Account(managerId, "manager", "pass", Account.Role.MANAGER);

        when(accountRepository.findById(managerId)).thenReturn(Optional.of(manager));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(existingTicket));
        when(ticketRepository.save(existingTicket)).thenReturn(existingTicket);

        // Act
        Ticket updatedTicket = ticketService.updateTicketStatus(ticketId, Ticket.Status.APPROVED, managerId);

        // Assert
        assertEquals(Ticket.Status.APPROVED, updatedTicket.getStatus());
        verify(accountRepository, times(1)).findById(managerId);
        verify(ticketRepository, times(1)).findById(ticketId);
        verify(ticketRepository, times(1)).save(existingTicket);
    }

    @Test
    void testUpdateTicketStatusNonManager() {
        // Arrange
        Integer ticketId = 1;
        Integer userId = 2;
        Account employee = new Account(userId, "employee", "pass", Account.Role.EMPLOYEE);

        when(accountRepository.findById(userId)).thenReturn(Optional.of(employee));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.updateTicketStatus(ticketId, Ticket.Status.APPROVED, userId));
        assertEquals("Only accounts with the Manager role can update ticket status.", exception.getMessage());
        verify(accountRepository, times(1)).findById(userId);
    }

    @Test
    void testDeleteTicketSuccess() {
        // Arrange
        Integer ticketId = 1;
        when(ticketRepository.existsById(ticketId)).thenReturn(true);

        // Act
        ticketService.deleteTicket(ticketId);

        // Assert
        verify(ticketRepository, times(1)).existsById(ticketId);
        verify(ticketRepository, times(1)).deleteById(ticketId);
    }

    @Test
    void testDeleteTicketNotFound() {
        // Arrange
        Integer ticketId = 1;
        when(ticketRepository.existsById(ticketId)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.deleteTicket(ticketId));
        assertEquals("Ticket not found with ID: " + ticketId, exception.getMessage());
        verify(ticketRepository, times(1)).existsById(ticketId);
    }
}
