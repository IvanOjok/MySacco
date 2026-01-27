package com.jambo.mysacco.service;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;

import java.util.List;

public interface AccountService {
    public String createAccount(Account account);

    public Account getAccount(Long userId);

    public List<Account> getAllSaccoAccounts();

    public String updateAccount(Account account);

    public Transaction makeTransaction(Transaction request);

    public List<Transaction> transactionHistory(Long userId);
}
