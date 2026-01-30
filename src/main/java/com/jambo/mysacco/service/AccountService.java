package com.jambo.mysacco.service;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;
import com.jambo.mysacco.models.util.AccountResponse;
import com.jambo.mysacco.models.util.SaccoAccountResponse;

import java.util.HashMap;
import java.util.List;

public interface AccountService {
    public String createAccount(Long userId);

    public List<Account> getAccount(Long userId);

    public AccountResponse getAccountBalance(Long userId);

    public List<Account> getAllSaccoAccounts(Long saccoId);

    public SaccoAccountResponse getSaccoBalances(Long saccoId);

    public Transaction makeTransaction(Transaction request);

    public List<Transaction> transactionHistory(Long userId);
}
