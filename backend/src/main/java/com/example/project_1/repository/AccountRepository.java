package com.example.project_1.repository;

import com.example.project_1.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);  
    Optional<Account> findByAccountId(Integer accountId);
}

