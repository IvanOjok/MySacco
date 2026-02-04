package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.entities.AuditLog;
import com.jambo.mysacco.repository.AuditRepository;
import com.jambo.mysacco.service.AuditService;

public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public AuditLog createLog(AuditLog request) {
        return auditRepository.save(request);
    }

}
