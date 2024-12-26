package com.example.project_1.entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * This is a class that models an Account.
 */
@Entity
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}) // Enforces unique username
public class Account {

    /**
     * An ID for this Account. Used as the Entity's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure auto-generation
    @Column(name = "account_id", nullable = false, updatable = false)
    private Integer accountId;

    /**
     * A username for this Account (must be unique and not blank).
     */
    @Column(nullable = false, unique = true) // Enforces uniqueness in the database
    private String username;

    /**
     * A password for this account
     */
    @Column(nullable = false) // Cannot be null in the database
    private String password;

    /**
     * A role for this Account (must be either EMPLOYEE or MANAGER). Defaults to EMPLOYEE.
     */
    @Enumerated(EnumType.STRING) // Stores the enum as a String in the database
    @Column(nullable = false) // Cannot be null in the database
    private Role role = Role.EMPLOYEE; // Default role

    /**
     * Enum to represent the possible roles for an account.
     */
    public enum Role {
        EMPLOYEE, MANAGER
    }

    /**
     * Default constructor required for JPA and Jackson.
     */
    public Account() {}

    /**
     * Constructor for creating a new account with username and password.
     * Role defaults to EMPLOYEE.
     *
     * @param username the username for the account
     * @param password the password for the account
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.EMPLOYEE; // Default role
    }

    /**
     * Constructor for creating a new account with all fields specified.
     *
     * @param accountId the ID of the account
     * @param username  the username for the account
     * @param password  the password for the account
     * @param role      the role for the account
     */
    public Account(Integer accountId, String username, String password, Role role) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return Objects.equals(accountId, account.accountId) &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password) &&
                role == account.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, username, password, role);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
