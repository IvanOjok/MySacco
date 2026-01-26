package com.jambo.mysacco.models;

import jakarta.persistence.Id;

import java.time.LocalDate;

public class Sacco {
    @Id
    private Long id;
    private String name;
    private String village;
    private LocalDate startDate;
    private boolean active;

    public Sacco(Long id, String name, String village, LocalDate startDate, boolean active) {
        this.id = id;
        this.name = name;
        this.village = village;
        this.startDate = startDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

