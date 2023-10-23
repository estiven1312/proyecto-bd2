package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {

    @Procedure(name = "findAllCatalogosByCursor")
    public List<Catalogo> findAllCatalogosByCursor();
    @Query("SELECT cat FROM Catalogo cat WHERE UPPER(cat.abreviatura) = UPPER(:abreviatura)")
    public Catalogo findCatalogoByAbreviatura(@Param("abreviatura") String abreviatura);
}

