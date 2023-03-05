package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    @Query("SELECT s FROM Solicitud s WHERE s.estado.abreviatura = 'ATENDER'")
    public List<Solicitud> findSolicitudesPendientes();

    @Query("SELECT s FROM Solicitud s WHERE s.estado.abreviatura = 'ATENDER' AND s.usuario.id = :idUsuario")
    public List<Solicitud> findSolicitudesPendientes(@Param("idUsuario") Long idUsuario);
}
