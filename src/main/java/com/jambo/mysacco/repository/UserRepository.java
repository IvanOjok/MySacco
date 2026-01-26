package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
