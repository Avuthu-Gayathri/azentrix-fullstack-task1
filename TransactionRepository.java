package com.azentrix.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azentrix.budgettracker.entity.Transaction;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

}