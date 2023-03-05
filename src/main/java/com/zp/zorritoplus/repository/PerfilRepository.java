package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository  extends JpaRepository<Perfil, Long> {
    @Query("SELECT p FROM Perfil p WHERE p.solicitud.usuario.id = :idUsuario")
    public Perfil findPerfilByUsuario(@Param("idUsuario") Long idUsuario);
}
