package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import com.zp.zorritoplus.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("solicitud")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;
    @PostMapping
    public ResponseEntity<SolicitudResponse> saveSolicitud(@RequestBody SolicitudDTO solicitudDTO){
        return solicitudService.registrarSolicitud(solicitudDTO);
    }
}
