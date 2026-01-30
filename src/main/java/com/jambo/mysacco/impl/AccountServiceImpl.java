package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.entities.*;
import com.jambo.mysacco.repository.AccountRepository;
import com.jambo.mysacco.repository.TransactionRepository;
import com.jambo.mysacco.service.AccountService;
import com.jambo.mysacco.service.AuthService;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public String createAccount(Long userId) {
        User user = authService.getUserById(userId);
        if (!accountRepository.existsByUserId(userId)) {
            for (AccountType accountType: AccountType.values()) {
                Account account = new Account();
                account.setUserId(userId);
                account.setBalance(0);
                account.setType(accountType);
                account.setSaccoId(user.getSaccoId());
                accountRepository.save(account);
            }
        }
        //return accountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Account Not Created"));

        return "Accounts Created Successfully";
    }

    @Override
    public List<Account> getAccount(Long userId) {
        if (!accountRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Account with user id "+ userId + "doesn't exist");
        }
        return accountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Account doesn't exist"));
    }

    @Override
    public List<Account> getAllSaccoAccounts() {
        return accountRepository.findAll();
    }


    @Override
    public Transaction makeTransaction(Transaction request) {

        //update account
        User user = authService.getUserById(request.getUserId());
            if (request.getType() == TransactionType.DEPOSIT) {
                Account account = accountRepository.findByUserIdAndType(request.getUserId(), AccountType.SAVINGS).orElseThrow(() -> new IllegalArgumentException("Account Not Found"));

                /* Make an API call to payment service and run a cron job to update balances **/
                account.setBalance(account.getBalance() + (request.getAmount()));
                accountRepository.save(account);
            } else if (request.getType() == TransactionType.WITHDRAWAL) {
                Account account = accountRepository.findByUserIdAndType(request.getUserId(), AccountType.SAVINGS).orElseThrow(() -> new IllegalArgumentException("Account Not Found"));
                float amount = account.getBalance() - (request.getAmount());
                if (amount > 0) {
                    account.setBalance(amount);
                    accountRepository.save(account);
                } else {
                    throw new IllegalArgumentException("Cannot withdraw more than Savings");
                }
            }

        return transactionRepository.save(request);
    }

    @Override
    public List<Transaction> transactionHistory(Long userId) {
        return  transactionRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("No User Transactions Available"));
    }
}
