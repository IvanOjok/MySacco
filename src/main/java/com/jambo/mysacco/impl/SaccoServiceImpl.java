package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.Sacco;
import com.jambo.mysacco.repository.SaccoRepository;
import com.jambo.mysacco.service.SaccoService;
import org.springframework.stereotype.Service;

@Service
public class SaccoServiceImpl implements SaccoService {

    SaccoRepository saccoRepository;

    public SaccoServiceImpl(SaccoRepository saccoRepository) {
        this.saccoRepository = saccoRepository;
    }

    @Override
    public Sacco createSacco(Sacco request) {
        return saccoRepository.save(request);
    }

}
