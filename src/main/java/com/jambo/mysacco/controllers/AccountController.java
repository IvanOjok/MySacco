package com.jambo.mysacco.controllers;


import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.Transaction;
import com.jambo.mysacco.models.util.AccountResponse;
import com.jambo.mysacco.models.util.SaccoAccountResponse;
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
            bal.put(acc.getType().name().toLowerCase(), acc.getBalance());
        }

        AccountResponse response = new AccountResponse(account.getFirst().getUserId(), account.getFirst().getSaccoId(), bal);

        return ResponseEntity.ok(response);
    }

    @GetMapping("balance/sacco")
    public ResponseEntity<SaccoAccountResponse> getSaccoBalance(@RequestParam(name = "saccoId") Long saccoId) {
        return ResponseEntity.ok(accountService.getSaccoBalances(saccoId));
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
