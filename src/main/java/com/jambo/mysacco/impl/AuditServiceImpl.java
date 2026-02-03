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
        AuditLog log = new AuditLog();
        log.setAction(request.action);
        log.setEntity(request.entity);
        log.setEntityId(request.entityId);
        log.setDescription(request.description);
        log.setPerformedBy(request.getPerformedBy());
        log.setPerformedByRole(request.getPerformedByRole());
        log.setIpAddress(request.getIpAddress());
        return auditRepository.save(log);
    }

}
