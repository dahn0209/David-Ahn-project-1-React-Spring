// package com.example.project_1.repository;

// import com.example.project_1.entity.Ticket;
// import com.example.project_1.entity.Ticket.Status;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.time.LocalDateTime;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// class TicketRepositoryTest {

//     @Autowired
//     private TicketRepository ticketRepository;

//     @Test
//     void testFindByUserId() {
//         // Arrange
//         Ticket ticket1 = new Ticket(1, 101, 100.0, "Test Ticket 1", Status.PENDING, LocalDateTime.now());
//         Ticket ticket2 = new Ticket(2, 101, 200.0, "Test Ticket 2", Status.APPROVED, LocalDateTime.now());
//         ticketRepository.save(ticket1);
//         ticketRepository.save(ticket2);

//         // Act
//         List<Ticket> tickets = ticketRepository.findByUserId(101);

//         // Assert
//         assertEquals(2, tickets.size(), "Should retrieve 2 tickets for user ID 101.");
//     }

//     @Test
//     void testFindByStatus() {
//         // Arrange
//         Ticket ticket = new Ticket(1, 102, 300.0, "Pending Ticket", Status.PENDING, LocalDateTime.now());
//         ticketRepository.save(ticket);

//         // Act
//         List<Ticket> tickets = ticketRepository.findByStatus("PENDING");

//         // Assert
//         assertEquals(1, tickets.size(), "Should retrieve 1 ticket with status PENDING.");
//         assertEquals(Status.PENDING, tickets.get(0).getStatus());
//     }

//     @Test
//     void testFindBySubmissionDateAfter() {
//         // Arrange
//         LocalDateTime date = LocalDateTime.now().minusDays(1);
//         Ticket ticket1 = new Ticket(1, 103, 400.0, "Old Ticket", Status.DENIED, date.minusDays(1));
//         Ticket ticket2 = new Ticket(2, 103, 500.0, "New Ticket", Status.APPROVED, LocalDateTime.now());
//         ticketRepository.save(ticket1);
//         ticketRepository.save(ticket2);

//         // Act
//         List<Ticket> tickets = ticketRepository.findBySubmissionDateAfter(date);

//         // Assert
//         assertEquals(1, tickets.size(), "Should retrieve 1 ticket submitted after the given date.");
//         assertEquals("New Ticket", tickets.get(0).getDescription());
//     }
// }
