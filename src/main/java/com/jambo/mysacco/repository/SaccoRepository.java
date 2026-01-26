package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.Sacco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaccoRepository extends JpaRepository<Sacco, Integer> {

    Optional<Sacco> findById(Long saccoId);
}
