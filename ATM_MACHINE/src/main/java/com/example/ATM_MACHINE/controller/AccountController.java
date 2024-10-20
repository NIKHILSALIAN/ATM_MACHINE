package com.example.ATM_MACHINE.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // Correct import for Jakarta EE 9+
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ATM_MACHINE.model.Account;
import com.example.ATM_MACHINE.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/atm")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Check balance endpoint
    @GetMapping("/checkBalance")
    public ResponseEntity<?> checkBalance(@RequestParam String accountNumber, @RequestParam String pin) {
        if (accountNumber.isBlank() || pin.isBlank()) {
            return ResponseEntity.badRequest().body("Account number or PIN cannot be empty.");
        }
    
        Optional<Account> accountOpt = accountService.checkBalance(accountNumber, pin);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            return ResponseEntity.ok(" Your Balance: " + account.getBalance()); // Updated format
        } else {
            return ResponseEntity.badRequest().body("Invalid account number or PIN.");
        }
    }
    
    

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String accountNumber, @RequestParam String pin) {
        if (accountNumber.isBlank() || pin.isBlank()) {
            return ResponseEntity.badRequest().body("Account number or PIN cannot be empty.");
        }

        boolean isValid = accountService.validateAccount(accountNumber, pin);
        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid account number or PIN.");
        }
    }

    

    // Create account endpoint
    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account) {
        System.out.println("Received request to create account with account number: " + account.getAccountNumber());

        Optional<Account> existingAccount = accountService.findByAccountNumber(account.getAccountNumber());
        if (existingAccount.isPresent()) {
            System.out.println("Account already exists for account number: " + account.getAccountNumber());
            Map<String, String> response = new HashMap<>();
            response.put("error", "Account already exists.");
            return ResponseEntity.badRequest().body(response);
        }

        System.out.println("Saving new account for account number: " + account.getAccountNumber());
        Account savedAccount = accountService.saveAccount(account);

        System.out.println("Account created successfully. Account number: " + savedAccount.getAccountNumber());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account created successfully!");
        response.put("accountNumber", savedAccount.getAccountNumber());
        return ResponseEntity.ok(response);
    }

    // Deposit endpoint
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        // Validate input
        if (accountNumber.isBlank()) {
            return ResponseEntity.badRequest().body("Account number cannot be empty.");
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid deposit amount. Amount must be greater than zero.");
        }

        // Attempt to deposit the amount into the account
        Optional<Account> accountOpt = accountService.deposit(accountNumber, amount);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            return ResponseEntity.ok("Deposit successful! New balance: " + account.getBalance());
        } else {
            return ResponseEntity.badRequest().body("Account not found.");
        }
    }

    // Withdraw endpoint
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        if (accountNumber.isBlank()) {
            return ResponseEntity.badRequest().body("Account number cannot be empty.");
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid withdrawal amount. Amount must be greater than zero.");
        }

        try {
            Optional<Account> accountOpt = accountService.withdraw(accountNumber, amount);
            if (accountOpt.isPresent()) {
                Account account = accountOpt.get();
                return ResponseEntity.ok("Withdrawal successful! New balance: " + account.getBalance());
            } else {
                return ResponseEntity.badRequest().body("Account not found.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
