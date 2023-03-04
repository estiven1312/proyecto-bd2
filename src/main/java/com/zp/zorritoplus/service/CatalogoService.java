package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.domain.Catalogo;

import java.util.List;

public interface CatalogoService {
    public List<Catalogo> getAllCatalogos();

    public Catalogo findCatalogoById(Long id);

    public Catalogo findCatalogoByAbreviatura(String abreviatura);
}
