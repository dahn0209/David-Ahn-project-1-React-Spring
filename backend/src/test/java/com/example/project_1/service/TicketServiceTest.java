// package com.example.project_1.service;

// import com.example.project_1.entity.Account;
// import com.example.project_1.entity.Ticket;
// import com.example.project_1.entity.Ticket.Status;
// import com.example.project_1.repository.AccountRepository;
// import com.example.project_1.repository.TicketRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.time.LocalDateTime;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// class TicketServiceTest {

//     @Mock
//     private TicketRepository ticketRepository;

//     @Mock
//     private AccountRepository accountRepository;

//     @InjectMocks
//     private TicketService ticketService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetAllTickets() {
//         // Arrange
//         when(ticketRepository.findAll()).thenReturn(
//             List.of(new Ticket(1, 101, 100.0, "Sample Ticket 1", Status.PENDING, LocalDateTime.now()),
//                     new Ticket(2, 102, 200.0, "Sample Ticket 2", Status.APPROVED, LocalDateTime.now()))
//         );

//         // Act
//         var tickets = ticketService.getAllTickets();

//         // Assert
//         assertEquals(2, tickets.size());
//         verify(ticketRepository, times(1)).findAll();
//     }

//     @Test
//     void testCreateTicket() {
//         // Arrange
//         Ticket ticket = new Ticket(1, 101, 150.0, "New Ticket", Status.PENDING, LocalDateTime.now());
//         when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

//         // Act
//         Ticket createdTicket = ticketService.createTicket(ticket);

//         // Assert
//         assertNotNull(createdTicket);
//         assertEquals("New Ticket", createdTicket.getDescription());
//         verify(ticketRepository, times(1)).save(ticket);
//     }

//     @Test
//     void testUpdateTicketStatus() {
//         // Arrange
//         Account manager = new Account(1, "manager", "password", Account.Role.MANAGER);
//         Ticket ticket = new Ticket(1, 101, 200.0, "Sample Ticket", Status.PENDING, LocalDateTime.now());
//         when(accountRepository.findById(1)).thenReturn(Optional.of(manager));
//         when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

//         // Act
//         Ticket updatedTicket = ticketService.updateTicketStatus(1, Status.APPROVED, 1);

//         // Assert
//         assertEquals(Status.APPROVED, updatedTicket.getStatus());
//         verify(ticketRepository, times(1)).save(ticket);
//     }

//     @Test
//     void testUpdateTicketStatusFailure() {
//         // Arrange
//         Account nonManager = new Account(1, "employee", "password", Account.Role.EMPLOYEE);
//         when(accountRepository.findById(1)).thenReturn(Optional.of(nonManager));

//         // Act & Assert
//         assertThrows(RuntimeException.class, () -> ticketService.updateTicketStatus(1, Status.APPROVED, 1));
//         verify(accountRepository, times(1)).findById(1);
//         verify(ticketRepository, never()).save(any(Ticket.class));
//     }
// }
