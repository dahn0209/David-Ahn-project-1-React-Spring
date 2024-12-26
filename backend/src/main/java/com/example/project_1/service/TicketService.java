package com.example.project_1.service;

import com.example.project_1.entity.Account;
import com.example.project_1.entity.Ticket;
import com.example.project_1.repository.AccountRepository;
import com.example.project_1.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Retrieve all tickets from the database.
     * @return List of tickets.
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Retrieve a ticket by its ID.
     * @param ticketId The ID of the ticket.
     * @return The ticket, if found.
     */
    public Optional<Ticket> getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId);
    }

    /**
     * Retrieve all tickets for a specific user.
     * @param userId The ID of the user.
     * @return List of tickets for the user.
     */
    public List<Ticket> getTicketsByUserId(Integer userId) {
        return ticketRepository.findByUserId(userId);
    }

    /**
     * Create a new ticket in the database.
     * @param ticket The ticket to create.
     * @return The created ticket.
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Update an existing ticket.
     * @param ticketId The ID of the ticket to update.
     * @param updatedTicket The updated ticket details.
     * @return The updated ticket.
     * @throws RuntimeException if the ticket is not found.
     */
    public Ticket updateTicket(Integer ticketId, Ticket updatedTicket) {
        return ticketRepository.findById(ticketId).map(existingTicket -> {
            if (updatedTicket.getStatus() != null) {
                existingTicket.setStatus(updatedTicket.getStatus());
            }
            // Optionally update other fields if needed
            return ticketRepository.save(existingTicket);
        }).orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
    }
    

    /**
     * Update the status of a ticket if the account has a Manager role.
     * @param ticketId The ID of the ticket to update.
     * @param newStatus The new status to set.
     * @param accountId The ID of the account attempting the update.
     * @return The updated ticket.
     * @throws RuntimeException if the account is not a Manager or the ticket is not found.
     */
    public Ticket updateTicketStatus(Integer ticketId, Ticket.Status newStatus, Integer accountId) {
        // Fetch the account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Check if the account role is Manager
        if (account.getRole() != Account.Role.MANAGER) {
            throw new RuntimeException("Only accounts with the Manager role can update ticket status.");
        }

        // Fetch the ticket
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));

        // Update the status
        ticket.setStatus(newStatus);
        return ticketRepository.save(ticket);
    }

    /**
     * Delete a ticket by its ID.
     * @param ticketId The ID of the ticket to delete.
     */
    public void deleteTicket(Integer ticketId) {
        if (ticketRepository.existsById(ticketId)) {
            ticketRepository.deleteById(ticketId);
        } else {
            throw new RuntimeException("Ticket not found with ID: " + ticketId);
        }
    }
}
