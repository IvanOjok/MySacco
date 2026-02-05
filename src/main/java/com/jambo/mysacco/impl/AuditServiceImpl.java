package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.entities.AuditLog;
import com.jambo.mysacco.repository.AuditRepository;
import com.jambo.mysacco.service.AuditService;
import com.jambo.mysacco.utils.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void createLog(String action, String entity, Long entityId, String description) {
        //audit
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        Long memberId = null;
        String role = null;

        if (auth != null && auth.isAuthenticated()
                && !"anonymousUser".equals(auth.getName())) {
            memberId = Long.parseLong(auth.getName());
            role = auth.getAuthorities()
                    .stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse(null);
        }

        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntity(entity);
        log.setEntityId(entityId);
        log.setDescription(description);
        log.setPerformedBy(memberId);
        log.setPerformedByRole(role);
        log.setIpAddress(RequestContext.getClientIp());

        auditRepository.save(log);
    }

}
