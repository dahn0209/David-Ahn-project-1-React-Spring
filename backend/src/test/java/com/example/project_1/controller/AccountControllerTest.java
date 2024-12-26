package com.example.project_1.controller;

import com.example.project_1.entity.Account;
import com.example.project_1.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccounts() {
        // Arrange
        Account account1 = new Account(1, "user1", "pass1", Account.Role.EMPLOYEE);
        Account account2 = new Account(2, "user2", "pass2", Account.Role.MANAGER);
        when(accountService.getAllAccounts()).thenReturn(Arrays.asList(account1, account2));

        // Act
        ResponseEntity<Object> response = accountController.getAllAccounts();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, ((List<Account>) response.getBody()).size());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void testRegisterAccountSuccess() {
        // Arrange
        Account newAccount = new Account(null, "user1", "pass1", Account.Role.EMPLOYEE);
        when(accountService.createAccount(newAccount)).thenReturn(newAccount);

        // Act
        ResponseEntity<Object> response = accountController.registerAccount(newAccount);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(accountService, times(1)).createAccount(newAccount);
    }

    @Test
    void testRegisterAccountUsernameExists() {
        // Arrange
        Account newAccount = new Account(null, "user1", "pass1", Account.Role.EMPLOYEE);
        when(accountService.usernameExists("user1")).thenReturn(true);

        // Act
        ResponseEntity<Object> response = accountController.registerAccount(newAccount);

        // Assert
        assertEquals(409, response.getStatusCodeValue());
        verify(accountService, times(1)).usernameExists("user1");
    }

    @Test
    void testLoginAccountSuccess() throws Exception {
        // Arrange
        Account account = new Account(1, "user1", "pass1", Account.Role.EMPLOYEE);
        when(accountService.login("user1", "pass1")).thenReturn(account);

        // Act
        ResponseEntity<Object> response = accountController.loginAccount(account);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(account, response.getBody());
        verify(accountService, times(1)).login("user1", "pass1");
    }

    @Test
    void testLoginAccountFailure() throws Exception {
        // Arrange
        Account account = new Account(1, "user1", "wrongpass", Account.Role.EMPLOYEE);
        when(accountService.login("user1", "wrongpass")).thenThrow(new Exception("Invalid password"));

        // Act
        ResponseEntity<Object> response = accountController.loginAccount(account);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        verify(accountService, times(1)).login("user1", "wrongpass");
    }
}
