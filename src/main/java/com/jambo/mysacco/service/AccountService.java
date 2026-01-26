package com.jambo.mysacco.service;

import com.jambo.mysacco.models.Account;

import java.util.List;

public interface AccountService {
    public String createAccount(Account account);
    public Account getAccount(int userId);
    public List<Account> getAllSaccoAccounts();
    public String updateAccount(Account account);
}
