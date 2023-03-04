package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
