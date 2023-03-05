package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    @Query("SELECT p FROM Plataforma p WHERE p.estado.abreviatura = 'ACTIVO'")
    public List<Plataforma> findAllActivos();
}
