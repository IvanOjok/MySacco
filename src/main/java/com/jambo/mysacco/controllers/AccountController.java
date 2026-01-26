package com.jambo.mysacco.controllers;


import com.jambo.mysacco.models.Account;
import com.jambo.mysacco.models.Transaction;
import com.jambo.mysacco.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("Account Successfully Created");
    }

    @GetMapping("balance")
    public ResponseEntity<String> getBalance(@RequestParam Long userId) {
        Account account = accountService.getAccount(userId);
        return ResponseEntity.ok(String.valueOf(account.getBalance()));
    }

    @GetMapping("balance/sacco")
    public ResponseEntity<String> getSaccoBalance(@RequestParam(name = "saccoId") int saccoId) {
        List<Account> accounts = accountService.getAllSaccoAccounts();
        if (accounts == null) {
            return ResponseEntity.notFound().build();
        }
        float total = 0;
        for (Account account : accounts) {
            if (account.getSaccoId() == saccoId) {
                total += account.getBalance();
            }
        }
        return ResponseEntity.ok(String.valueOf(total));
    }

    @PostMapping("transact")
    public Transaction transact(@RequestBody Transaction request) {
        return accountService.makeTransaction(request);
    }

    @GetMapping("history")
    public List<Transaction> getHistory(@RequestParam("userId") Long userId) {
        return accountService.transactionHistory(userId);
    }




}
