package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
