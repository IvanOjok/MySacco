package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.Sacco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaccoRepository extends JpaRepository<Sacco, Integer> {

    Optional<Sacco> findSaccoById(Long saccoId);
}
