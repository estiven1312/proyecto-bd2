package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository  extends JpaRepository<Perfil, Long> {
    @Query("SELECT p FROM Perfil p WHERE p.solicitud.usuario.id = :idUsuario")
    public Perfil findPerfilByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT p FROM Perfil p WHERE p.solicitud.usuario.correo = :correo")
    public List<Perfil> findPerfilByUsuario(@Param("correo") String correo);

    @Query("SELECT p FROM Perfil p WHERE p.solicitud.estado.abreviatura = 'ACTIVO'")
    public List<Perfil> findPerfilActivo();
}
