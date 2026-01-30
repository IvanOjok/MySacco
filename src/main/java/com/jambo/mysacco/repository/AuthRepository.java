package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserPhone(String phoneNumber);

    boolean existsByUserPhone(String phoneNumber);

    User findUserByUserId(Long userId);

}
