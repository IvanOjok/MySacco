package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
