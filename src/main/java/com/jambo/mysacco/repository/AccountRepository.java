package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.Account;
import com.jambo.mysacco.models.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<List<Account>> findByUserId(Long userId);
    boolean existsByUserId(Long userId);

    Optional<Account> findByUserIdAndType(Long userId, AccountType type);

    Optional<List<Account>> findAccountsBySaccoId(Long saccoId);

    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.type = :type AND a.saccoId = :saccoId")
    Float getTotalSaccoSavings(@Param("type") AccountType accountType, @Param("saccoId") Long saccoId);


}
