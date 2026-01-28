package com.jambo.mysacco.service;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;

import java.util.List;

public interface AccountService {
    public String createAccount(Long userId);

    public List<Account> getAccount(Long userId);

    public List<Account> getAllSaccoAccounts();

    public Transaction makeTransaction(Transaction request);

    public List<Transaction> transactionHistory(Long userId);
}
