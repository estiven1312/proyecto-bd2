package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CatalogoServiceImpl implements CatalogoService {
    @Autowired
    CatalogoRepository catalogoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Catalogo> getAllCatalogos() {
        return catalogoRepository.findAllCatalogosByCursor();
    }

    @Override
    @Transactional(readOnly = true)
    public Catalogo findCatalogoById(Long id) {
        return catalogoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Catalogo findCatalogoByAbreviatura(String abreviatura) {
        return catalogoRepository.findCatalogoByAbreviatura(abreviatura);
    }
}
