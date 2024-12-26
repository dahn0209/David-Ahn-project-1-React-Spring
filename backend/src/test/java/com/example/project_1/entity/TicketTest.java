// package com.example.project_1.entity;

// import org.junit.jupiter.api.Test;

// import java.time.LocalDateTime;

// import static org.junit.jupiter.api.Assertions.*;

// class TicketTest {

//     @Test
//     void testTicketConstructor() {
//         LocalDateTime now = LocalDateTime.now();
//         Ticket ticket = new Ticket(1, 101, 200.0, "Sample Ticket", Ticket.Status.PENDING, now);

//         assertEquals(1, ticket.getTicketId());
//         assertEquals(101, ticket.getUserId());
//         assertEquals(200.0, ticket.getAmount());
//         assertEquals("Sample Ticket", ticket.getDescription());
//         assertEquals(Ticket.Status.PENDING, ticket.getStatus());
//         assertEquals(now, ticket.getSubmissionDate());
//     }

//     @Test
//     void testTicketEqualsAndHashCode() {
//         LocalDateTime now = LocalDateTime.now();
//         Ticket ticket1 = new Ticket(1, 101, 200.0, "Sample Ticket", Ticket.Status.PENDING, now);
//         Ticket ticket2 = new Ticket(1, 101, 200.0, "Sample Ticket", Ticket.Status.PENDING, now);
//         Ticket ticket3 = new Ticket(2, 102, 300.0, "Another Ticket", Ticket.Status.APPROVED, now);

//         assertEquals(ticket1, ticket2, "Tickets with the same ID and properties should be equal.");
//         assertNotEquals(ticket1, ticket3, "Tickets with different IDs should not be equal.");

//         assertEquals(ticket1.hashCode(), ticket2.hashCode(), "HashCodes for equal tickets should match.");
//         assertNotEquals(ticket1.hashCode(), ticket3.hashCode(), "HashCodes for different tickets should not match.");
//     }

//     @Test
//     void testTicketToString() {
//         LocalDateTime now = LocalDateTime.now();
//         Ticket ticket = new Ticket(1, 101, 200.0, "Sample Ticket", Ticket.Status.PENDING, now);

//         String expected = "Ticket{ticketId=1, userId=101, amount=200.0, description='Sample Ticket', status=PENDING, submissionDate=" + now + "}";
//         assertEquals(expected, ticket.toString(), "toString method should produce the expected format.");
//     }
// }
