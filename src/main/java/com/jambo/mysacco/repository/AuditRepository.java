package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}
