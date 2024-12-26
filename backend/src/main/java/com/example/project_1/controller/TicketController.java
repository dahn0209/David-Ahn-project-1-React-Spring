package com.example.project_1.controller;

import com.example.project_1.entity.Ticket;
import com.example.project_1.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets") // Base URL for ticket-related APIs
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Get all tickets.
     * @return List of all tickets.
     */
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    /**
     * Get a ticket by its ID.
     * @param ticketId The ID of the ticket.
     * @return The ticket, if found.
     */
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer ticketId) {
        Optional<Ticket> ticket = ticketService.getTicketById(ticketId);
        return ticket.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get all tickets for a specific user.
     * @param userId The ID of the user.
     * @return List of tickets for the user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Integer userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    /**
     * Create a new ticket.
     * @param ticket The ticket object to create.
     * @return The created ticket.
     */
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    /**
     * Update an existing ticket.
     * @param ticketId The ID of the ticket to update.
     * @param ticket The updated ticket details.
     * @return The updated ticket.
     */
    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Integer ticketId, @RequestBody Ticket ticket) {
        try {
            Ticket updatedTicket = ticketService.updateTicket(ticketId, ticket);
            return ResponseEntity.ok(updatedTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update the status of a ticket.
     * @param ticketId The ID of the ticket to update.
     * @param status The new status for the ticket.
     * @param accountId The ID of the account attempting the update.
     * @return The updated ticket.
     */
    @PutMapping("/{ticketId}/status")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable Integer ticketId,
            @RequestParam Ticket.Status status,
            @RequestParam Integer accountId) {
        try {
            Ticket updatedTicket = ticketService.updateTicketStatus(ticketId, status, accountId);
            return ResponseEntity.ok(updatedTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    /**
     * Delete a ticket by its ID.
     * @param ticketId The ID of the ticket to delete.
     * @return HTTP 204 (No Content) if successful.
     */
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

