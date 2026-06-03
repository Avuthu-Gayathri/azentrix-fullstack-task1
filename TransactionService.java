package com.azentrix.budgettracker.service;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azentrix.budgettracker.entity.Transaction;
import com.azentrix.budgettracker.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
    
    public double getTotalIncome() {

        return repository.findAll()
                .stream()
                .filter(t -> "Income".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {

        return repository.findAll()
                .stream()
                .filter(t -> "Expense".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {

        return getTotalIncome() - getTotalExpense();
    }
    public double getCurrentMonthIncome() {

        LocalDate now = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(t -> t.getTransactionDate() != null)
                .filter(t ->
                        t.getTransactionDate().getMonthValue() ==
                        now.getMonthValue())
                .filter(t ->
                        "Income".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    
    public double getCurrentMonthExpense() {

        LocalDate now = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(t -> t.getTransactionDate() != null)
                .filter(t ->
                        t.getTransactionDate().getMonthValue() ==
                        now.getMonthValue())
                .filter(t ->
                        "Expense".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    public double getCurrentMonthBalance() {
        return getCurrentMonthIncome() - getCurrentMonthExpense();
    }
    public long getTotalTransactions() {
        return repository.count();
    }
}