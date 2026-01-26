package com.jambo.mysacco.controllers;


import com.jambo.mysacco.models.Account;
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

    @PostMapping("{userId}")
    public ResponseEntity<String> getBalance(@RequestParam(name = "userId") int userId) {
        Account account = accountService.getAccount(userId);
        return ResponseEntity.ok(account.getBalance());
    }

    @GetMapping()
    public ResponseEntity<String> getSaccoBalance(@RequestParam(name = "saccoId") int saccoId) {
        List<Account> accounts = accountService.getAllSaccoAccounts();
        if (accounts == null) {
            return ResponseEntity.notFound().build();
        }
        int total = 0;
        for (Account account : accounts) {
            total += Integer.parseInt(account.getBalance());
        }
        return ResponseEntity.ok(Integer.toString(total));
    }


}
