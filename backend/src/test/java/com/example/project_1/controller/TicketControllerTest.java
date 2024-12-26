// package com.example.project_1.controller;

// import com.example.project_1.entity.Ticket;
// import com.example.project_1.entity.Ticket.Status;
// import com.example.project_1.service.TicketService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDateTime;
// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(TicketController.class)
// class TicketControllerTest {

//     private static final Logger logger = LoggerFactory.getLogger(TicketControllerTest.class);

//     @Autowired
//     private MockMvc mockMvc;

//     @Mock
//     private TicketService ticketService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         logger.debug("Mocks initialized for TicketControllerTest.");
//     }

//     @Test
//     void testGetAllTickets() throws Exception {
//         logger.info("Starting test: testGetAllTickets");
//         Ticket ticket = new Ticket(1, 101, 100.0, "Sample Ticket", Status.PENDING, LocalDateTime.now());
//         when(ticketService.getAllTickets()).thenReturn(List.of(ticket));

//         mockMvc.perform(get("/tickets"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].description").value("Sample Ticket"));

//         verify(ticketService, times(1)).getAllTickets();
//         logger.info("Completed test: testGetAllTickets");
//     }

//     @Test
//     void testCreateTicket_Success() throws Exception {
//         logger.info("Starting test: testCreateTicket_Success");
//         Ticket ticket = new Ticket(1, 101, 150.0, "New Ticket", Status.PENDING, LocalDateTime.now());
//         when(ticketService.createTicket(any(Ticket.class))).thenReturn(ticket);

//         mockMvc.perform(post("/tickets")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("{\"userId\":101,\"amount\":150.0,\"description\":\"New Ticket\"}"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.description").value("New Ticket"));

//         verify(ticketService, times(1)).createTicket(any(Ticket.class));
//         logger.info("Completed test: testCreateTicket_Success");
//     }
// }
