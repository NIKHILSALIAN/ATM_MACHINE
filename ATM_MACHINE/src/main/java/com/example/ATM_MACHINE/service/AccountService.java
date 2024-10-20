package com.example.ATM_MACHINE.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ATM_MACHINE.model.Account;
import com.example.ATM_MACHINE.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Check balance logic
    public Optional<Account> checkBalance(String accountNumber, String pin) {
        return accountRepository.findByAccountNumberAndPin(accountNumber, pin);
    }

    // Validate account for login logic
    public boolean validateAccount(String accountNumber, String pin) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
        return accountOpt.isPresent();
    }

    // Deposit logic
    public Optional<Account> deposit(String accountNumber, double amount) {
        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            return Optional.of(account); // Return the updated account
        } else {
            return Optional.empty(); // Return empty if account not found
        }
    }

    // Withdraw logic
    public Optional<Account> withdraw(String accountNumber, double amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            } else {
                throw new IllegalArgumentException("Insufficient balance.");
            }
        }
        return accountOpt;
    }

    // Create a new account
    public Account saveAccount(Account account) {
        return accountRepository.save(account); // Save the new account in the repository
    }

    // Find an account by account number
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
