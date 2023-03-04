package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.repository.PlataformaRepository;
import com.zp.zorritoplus.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PlataformaServiceImpl implements PlataformaService {
    @Autowired
    PlataformaRepository plataformaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Plataforma> getAllPlataformas() {
        return plataformaRepository.findAll();
    }
}
