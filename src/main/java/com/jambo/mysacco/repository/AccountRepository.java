package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<List<Account>> findByUserId(Long userId);
    boolean existsByUserId(Long userId);

    Optional<Account> findByUserIdAndType(Long userId, AccountType type);
}
