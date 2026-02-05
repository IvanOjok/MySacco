package com.jambo.mysacco.service;

import org.springframework.stereotype.Service;


@Service
public interface AuditService {

    void createLog(String action, String entity, Long entityId, String description);

}
