
package com.example.project_1.entity;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ticket") // Map this entity to the ticket table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ticket_id
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;

    @Column(name = "user_id", nullable = false) // Store user_id directly
    private Integer userId;

    @Column(name = "amount", nullable = false) // Ticket amount
    private Double amount;

    @Column(name = "description", nullable = false, length = 500) // Ticket description
    private String description;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    @Column(name = "status", nullable = false) // Ticket status
    private Status status = Status.PENDING; // Default to PENDING

    @Column(name = "submission_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime submissionDate = LocalDateTime.now(); // Default to now

    /**
     * Enum to represent the possible statuses of a ticket.
     */
    public enum Status {
        PENDING, APPROVED, DENIED
    }

    // Default constructor required by JPA
    public Ticket() {}

    /**
     * Constructor for creating a new ticket.
     *
     * @param userId      the ID of the user submitting the ticket
     * @param amount      the reimbursement amount
     * @param description the description of the request
     */
    public Ticket(Integer userId, Double amount, String description) {
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.status = Status.PENDING;
        this.submissionDate = LocalDateTime.now();
    }

    /**
     * Constructor for creating a ticket with a specified ID.
     *
     * @param ticketId    the ticket ID
     * @param userId      the ID of the user submitting the ticket
     * @param amount      the reimbursement amount
     * @param description the description of the request
     * @param status      the status of the ticket
     * @param submissionDate the date and time of submission
     */
    public Ticket(Integer ticketId, Integer userId, Double amount, String description, Status status, LocalDateTime submissionDate) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    // Getters and setters
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) &&
                Objects.equals(userId, ticket.userId) &&
                Objects.equals(amount, ticket.amount) &&
                Objects.equals(description, ticket.description) &&
                status == ticket.status &&
                Objects.equals(submissionDate, ticket.submissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, userId, amount, description, status, submissionDate);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", submissionDate=" + submissionDate +
                '}';
    }
}

