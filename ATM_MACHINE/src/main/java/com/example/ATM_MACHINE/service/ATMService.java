package com.example.ATM_MACHINE.service;

import com.example.ATM_MACHINE.model.Account;
import com.example.ATM_MACHINE.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ATMService {

    @Autowired
    private AccountRepository accountRepository;

    public boolean validateUser(String accountNumber, String pin) {
        Optional<Account> account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
        return account.isPresent();
    }

    public boolean deposit(String accountNumber, double amount) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            Account acc = account.get();
            acc.setBalance(acc.getBalance() + amount);
            accountRepository.save(acc);
            return true;
        }
        return false;
    }
}
