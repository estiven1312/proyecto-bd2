package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import com.zp.zorritoplus.service.SolicitudService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("solicitud")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;
    @PostMapping
    public ResponseEntity<SolicitudResponse> saveSolicitud(@RequestBody SolicitudDTO solicitudDTO){
        return solicitudService.registrarSolicitud(solicitudDTO);
    }
    @GetMapping("/admin")
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudes(){
        return solicitudService.listarSolicitudesPendientes();
    }
    @GetMapping("/user")
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudes(@RequestHeader HttpHeaders headers){
        try{
            String header = headers.get("authorization").get(0);
            JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
            String usuarioCreacion = jsonObject.getString("sub");
            return solicitudService.listarSolicitudesPendientesByUsuario(usuarioCreacion);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
