// package com.example.project_1.repository;

// import com.example.project_1.entity.Account;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// class AccountRepositoryTest {

//     @Autowired
//     private AccountRepository accountRepository;

//     @Test
//     void testFindByUsername() {
//         // Arrange
//         Account account = new Account("testUser", "password123", Account.Role.EMPLOYEE);
//         accountRepository.save(account);

//         // Act
//         Optional<Account> foundAccount = accountRepository.findByUsername("testUser");

//         // Assert
//         assertTrue(foundAccount.isPresent(), "Account should be found by username.");
//         assertEquals("testUser", foundAccount.get().getUsername());
//     }

//     @Test
//     void testFindByAccountId() {
//         // Arrange
//         Account account = new Account("testUser", "password123", Account.Role.EMPLOYEE);
//         Account savedAccount = accountRepository.save(account);

//         // Act
//         Optional<Account> foundAccount = accountRepository.findByAccountId(savedAccount.getAccountId());

//         // Assert
//         assertTrue(foundAccount.isPresent(), "Account should be found by account ID.");
//         assertEquals(savedAccount.getAccountId(), foundAccount.get().getAccountId());
//     }

//     @Test
//     void testSaveAccount() {
//         // Arrange
//         Account account = new Account("newUser", "newPassword", Account.Role.MANAGER);

//         // Act
//         Account savedAccount = accountRepository.save(account);

//         // Assert
//         assertNotNull(savedAccount.getAccountId(), "Account ID should not be null after saving.");
//         assertEquals("newUser", savedAccount.getUsername());
//     }
// }
