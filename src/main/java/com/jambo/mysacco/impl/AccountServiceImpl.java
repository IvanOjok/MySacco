package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.entities.*;
import com.jambo.mysacco.models.util.AccountResponse;
import com.jambo.mysacco.models.util.SaccoAccountResponse;
import com.jambo.mysacco.repository.AccountRepository;
import com.jambo.mysacco.repository.AuthRepository;
import com.jambo.mysacco.repository.SaccoRepository;
import com.jambo.mysacco.repository.TransactionRepository;
import com.jambo.mysacco.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;
    AuthRepository authRepository;
    SaccoRepository saccoRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, AuthRepository authRepository, SaccoRepository saccoRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.authRepository = authRepository;
        this.saccoRepository = saccoRepository;
    }

    @Override
    public String createAccount(Long userId) {
        User user = authRepository.findUserByUserId(userId);
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
            throw new IllegalArgumentException("Account with user "+ userId + "doesn't exist");
        }
        return accountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Account doesn't exist"));
    }

    @Override
    public AccountResponse getAccountBalance(Long userId) {
        List<Account> account = getAccount(userId);
        HashMap<String, Float> bal = new HashMap<>();
        for (Account acc: account) {
            bal.put(acc.getType().name().toLowerCase(), acc.getBalance());
        }

        return new AccountResponse(account.getFirst().getUserId(), account.getFirst().getSaccoId(), bal);
    }

    @Override
    public List<Account> getAllSaccoAccounts(Long saccoId) {
        return accountRepository.findAccountsBySaccoId(saccoId).orElseThrow(() -> new IllegalArgumentException("No accounts attached to Sacco"));
    }

    @Override
    public  SaccoAccountResponse getSaccoBalances(Long saccoId) {
        Sacco sacco = saccoRepository.findSaccoById(saccoId).orElseThrow(() -> new IllegalArgumentException("Sacco Doesn't Exist"));

        HashMap<String, Float> balances = new HashMap<>();
        for (AccountType type: AccountType.values()) {
            Float balance = accountRepository.getTotalSaccoSavings(type, saccoId);
            balances.put(type.name().toLowerCase(), balance);
        }

        return new SaccoAccountResponse(saccoId, sacco.getName(), balances);
    }


    @Override
    @Transactional
    public Transaction makeTransaction(Transaction request) {

        //update account
        User user = authRepository.findUserByUserId(request.getUserId());
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
