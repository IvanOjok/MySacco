package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.Account;
import com.jambo.mysacco.repository.AccountRepository;
import com.jambo.mysacco.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(Account account) {
        accountRepository.save(account);
        return "Account Successfully Created";
    }

    @Override
    public Account getAccount(int userId) {
        return accountRepository.findById(userId).get();
    }

    @Override
    public List<Account> getAllSaccoAccounts() {
        return List.of();
    }

    @Override
    public String updateAccount(Account account) {
        return "";
    }
}
