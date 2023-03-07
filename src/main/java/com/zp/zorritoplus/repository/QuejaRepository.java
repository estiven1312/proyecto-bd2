package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    @Query("SELECT queja FROM Queja queja WHERE queja.estado.abreviatura = 'ATENDER' AND queja.usuario.id = :idUsuario")
    public List<Queja> findQuejasPorAtender(@Param("idUsuario") Long idUsuario);
    @Query("SELECT queja FROM Queja queja WHERE queja.usuario.correo = :correo")
    public List<Queja> findQuejasPorAtender(@Param("correo") String correo);
    @Procedure(name = "findAllQuejasPorAtender")
    public List<Queja> findQuejasPorAtender();
}
