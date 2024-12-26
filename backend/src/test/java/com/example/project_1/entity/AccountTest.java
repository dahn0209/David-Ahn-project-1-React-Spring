package com.example.project_1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountConstructorAndGetters() {
        // Arrange
        Integer accountId = 1;
        String username = "testUser";
        String password = "testPass";
        Account.Role role = Account.Role.EMPLOYEE;

        // Act
        Account account = new Account(accountId, username, password, role);

        // Assert
        assertEquals(accountId, account.getAccountId());
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
        assertEquals(role, account.getRole());
    }

    @Test
    void testSetters() {
        // Arrange
        Account account = new Account();

        // Act
        account.setAccountId(2);
        account.setUsername("newUser");
        account.setPassword("newPass");
        account.setRole(Account.Role.MANAGER);

        // Assert
        assertEquals(2, account.getAccountId());
        assertEquals("newUser", account.getUsername());
        assertEquals("newPass", account.getPassword());
        assertEquals(Account.Role.MANAGER, account.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Account account1 = new Account(1, "testUser", "testPass", Account.Role.EMPLOYEE);
        Account account2 = new Account(1, "testUser", "testPass", Account.Role.EMPLOYEE);
        Account account3 = new Account(2, "otherUser", "otherPass", Account.Role.MANAGER);

        // Act & Assert
        assertEquals(account1, account2);
        assertNotEquals(account1, account3);
        assertEquals(account1.hashCode(), account2.hashCode());
        assertNotEquals(account1.hashCode(), account3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Account account = new Account(1, "testUser", "testPass", Account.Role.EMPLOYEE);

        // Act
        String toString = account.toString();

        // Assert
        assertTrue(toString.contains("testUser"));
        assertTrue(toString.contains("testPass"));
        assertTrue(toString.contains("EMPLOYEE"));
    }
}
