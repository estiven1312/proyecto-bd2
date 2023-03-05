package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SolicitudService {

    public ResponseEntity<SolicitudResponse> registrarSolicitud(SolicitudDTO solicitudDTO);
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudesPendientesByUsuario(Long id);
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudesPendientes();
    public ResponseEntity<SolicitudResponse> eliminarSolicitud(Long idSolicitud);
    public ResponseEntity<SolicitudResponse> modificarSolicitud(SolicitudDTO solicitudDTO);
}
