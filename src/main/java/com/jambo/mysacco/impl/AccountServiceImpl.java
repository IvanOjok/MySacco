package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;
import com.jambo.mysacco.models.entities.User;
import com.jambo.mysacco.repository.AccountRepository;
import com.jambo.mysacco.repository.TransactionRepository;
import com.jambo.mysacco.service.AccountService;
import com.jambo.mysacco.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;
    AuthService authService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, AuthService authService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.authService = authService;
    }

    @Override
    public String createAccount(Account account) {
        accountRepository.save(account);
        return "Account Successfully Created";
    }

    @Override
    public Account getAccount(Long userId) {
        User user = authService.getUserById(userId);
        if (!accountRepository.existsByUserId(userId)) {
            Account account = new Account();
            account.setUserId(userId);
            account.setBalance(0);
            account.setSaccoId(user.getSaccoId());
            accountRepository.save(account);
            //throw new IllegalArgumentException("Account Not Created");
        }
        return accountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Account Not Created"));
    }

    @Override
    public List<Account> getAllSaccoAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String updateAccount(Account account) {
        return "";
    }

    @Override
    public Transaction makeTransaction(Transaction request) {
        //update account
        User user = authService.getUserById(request.getUserId());
        if (!accountRepository.existsByUserId(request.getUserId())) {
            if (Objects.equals(request.getType(), "deposit")) {
                Account account = new Account();
                account.setUserId(request.getUserId());
                account.setBalance(request.getAmount());
                account.setSaccoId(user.getSaccoId());
                accountRepository.save(account);
            } else {
                throw new IllegalArgumentException("Cannot Perform Transaction Before Depositing");
            }
        } else {
            if (Objects.equals(request.getType(), "deposit")) {
                Account account = accountRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("Account Not Created"));
                account.setBalance(account.getBalance() + (request.getAmount()));
                accountRepository.save(account);
            } else if (Objects.equals(request.getType(), "withdraw")) {
                Account account = accountRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("Account Not Created"));
                float amount = account.getBalance() - (request.getAmount());
                if (amount > 0) {
                    account.setBalance(amount);
                    accountRepository.save(account);
                } else {
                    throw new IllegalArgumentException("Cannot withdraw more than Savings");
                }
            }
        }

        return transactionRepository.save(request);
    }

    @Override
    public List<Transaction> transactionHistory(Long userId) {
        return  transactionRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("No User Transactions Available"));
    }
}
