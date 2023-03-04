package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    @Query("SELECT queja FROM Queja queja WHERE queja.estado.id = :idPorAtender")
    public List<Queja> findQuejasPorAtender(@Param("idPorAtender") Long id);

    @Procedure(name = "findAllQuejasPorAtender")
    public List<Queja> findQuejasPorAtender();
}
