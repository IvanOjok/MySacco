package com.jambo.mysacco.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="sacco")
public class Sacco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String village;
    private String startDate;
    private boolean active;

    public Sacco(Long id, String name, String village, String startDate, boolean active) {
        this.id = id;
        this.name = name;
        this.village = village;
        this.startDate = startDate;
        this.active = active;
    }

    public Sacco() {

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

