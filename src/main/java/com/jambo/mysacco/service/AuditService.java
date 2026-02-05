package com.jambo.mysacco.service;

import com.jambo.mysacco.models.entities.AuditLog;
import org.springframework.stereotype.Service;


@Service
public interface AuditService {

    public AuditLog createLog(String action, String entity, Long entityId, String description);

}
