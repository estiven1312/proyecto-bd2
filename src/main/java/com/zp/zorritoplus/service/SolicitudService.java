package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import org.springframework.http.ResponseEntity;

public interface SolicitudService {

    public ResponseEntity<SolicitudResponse> registrarSolicitud(SolicitudDTO solicitudDTO);
}
