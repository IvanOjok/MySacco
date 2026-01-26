package com.jambo.mysacco.service;

import com.jambo.mysacco.models.Sacco;
import org.springframework.stereotype.Service;


@Service
public interface SaccoService {
    public Sacco createSacco(Sacco request);
}
