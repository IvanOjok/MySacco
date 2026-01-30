package com.jambo.mysacco.service;

import com.jambo.mysacco.models.entities.Sacco;
import org.springframework.stereotype.Service;


@Service
public interface SaccoService {
    public Sacco createSacco(Sacco request);

    public Sacco getSaccoById(Long saccoId);
}
