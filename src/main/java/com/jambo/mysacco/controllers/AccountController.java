package com.jambo.mysacco.controllers;


import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;
import com.jambo.mysacco.models.util.AccountResponse;
import com.jambo.mysacco.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<String> addAccount(@RequestBody Long userId) {
        return ResponseEntity.ok(accountService.createAccount(userId));
    }

    @GetMapping("balance")
    public ResponseEntity<AccountResponse> getBalance(@RequestBody Long userId) {
        List<Account> account = accountService.getAccount(userId);
        HashMap<String, Float> bal = new HashMap<>();
        for (Account acc: account) {
            bal.put(acc.getType(), acc.getBalance());
        }

        AccountResponse response = new AccountResponse(account.getFirst().getUserId(), account.getFirst().getSaccoId(), bal);

        return ResponseEntity.ok(response);
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
