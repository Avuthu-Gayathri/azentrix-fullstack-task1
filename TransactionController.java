package com.azentrix.budgettracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.azentrix.budgettracker.entity.Transaction;
import com.azentrix.budgettracker.service.TransactionService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/")
    public String home(Model model) {

        List<Transaction> transactions =
                service.getAllTransactions();

        model.addAttribute("transactions", transactions);

        model.addAttribute("transaction",
                new Transaction());

        return "index";
    }

    @PostMapping("/save")
    public String saveTransaction(
            @ModelAttribute Transaction transaction) {

        service.saveTransaction(transaction);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(
            @PathVariable Long id) {

        service.deleteTransaction(id);

        return "redirect:/";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        double totalIncome = service.getTotalIncome();

        double totalExpense = service.getTotalExpense();

        double balance = service.getBalance();

        model.addAttribute("totalIncome", totalIncome);

        model.addAttribute("totalExpense", totalExpense);

        model.addAttribute("balance", balance);
        
        model.addAttribute("transactions",
                service.getAllTransactions());
        
        model.addAttribute(
                "monthIncome",
                service.getCurrentMonthIncome());

        model.addAttribute(
                "monthExpense",
                service.getCurrentMonthExpense());

        model.addAttribute(
                "monthBalance",
                service.getCurrentMonthBalance());
        model.addAttribute(
                "totalTransactions",
                service.getTotalTransactions());

        return "dashboard";
    }
    
    @GetMapping("/history")
    public String history(Model model) {

        model.addAttribute(
                "transactions",
                service.getAllTransactions());

        return "transaction-history";
    }
    @GetMapping("/edit/{id}")
    public String editTransaction(
            @PathVariable Long id,
            Model model) {

        Transaction transaction =
                service.getTransactionById(id);

        model.addAttribute(
                "transaction",
                transaction);

        model.addAttribute(
                "transactions",
                service.getAllTransactions());

        return "index";
    }
    @GetMapping("/reports")
    public String reports(Model model) {

        model.addAttribute(
                "totalIncome",
                service.getTotalIncome());

        model.addAttribute(
                "totalExpense",
                service.getTotalExpense());

        return "reports";
    }
    
    
    
}