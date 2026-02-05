package com.jambo.mysacco.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String action;
    public String entity;
    public Long entityId;
    public String description;
    public Long performedBy;
    public String performedByRole;
    public String ipAddress;
    public LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog(Long id, String action, String entity, Long entityId, String description, Long performedBy, String performedByRole, String ipAddress, LocalDateTime createdAt) {
        this.id = id;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.description = description;
        this.performedBy = performedBy;
        this.performedByRole = performedByRole;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    public AuditLog() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Long performedBy) {
        this.performedBy = performedBy;
    }

    public String getPerformedByRole() {
        return performedByRole;
    }

    public void setPerformedByRole(String performedByRole) {
        this.performedByRole = performedByRole;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
