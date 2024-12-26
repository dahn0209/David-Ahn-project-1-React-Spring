// package com.example.project_1.entity;

// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// class AccountTest {

//     @Test
//     void testAccountConstructor() {
//         Account account = new Account(1, "testUser", "password", Account.Role.EMPLOYEE);
//         assertEquals(1, account.getAccountId());
//         assertEquals("testUser", account.getUsername());
//         assertEquals("password", account.getPassword());
//         assertEquals(Account.Role.EMPLOYEE, account.getRole());
//     }

//     @Test
//     void testAccountEqualsAndHashCode() {
//         Account account1 = new Account(1, "user1", "password", Account.Role.EMPLOYEE);
//         Account account2 = new Account(1, "user1", "password", Account.Role.EMPLOYEE);
//         Account account3 = new Account(2, "user2", "password2", Account.Role.MANAGER);

//         assertEquals(account1, account2, "Accounts with the same ID and properties should be equal.");
//         assertNotEquals(account1, account3, "Accounts with different IDs should not be equal.");

//         assertEquals(account1.hashCode(), account2.hashCode(), "HashCodes for equal accounts should match.");
//         assertNotEquals(account1.hashCode(), account3.hashCode(), "HashCodes for different accounts should not match.");
//     }

//     @Test
//     void testAccountToString() {
//         Account account = new Account(1, "testUser", "password", Account.Role.EMPLOYEE);
//         String expected = "Account{accountId=1, username='testUser', password='password', role=EMPLOYEE}";
//         assertEquals(expected, account.toString(), "toString method should produce the expected format.");
//     }
// }

