package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import com.zp.zorritoplus.service.SolicitudService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(SolicitudController.class);
    @Autowired
    SolicitudService solicitudService;
    @PostMapping("/registrarPorId")
    public ResponseEntity<SolicitudResponse> saveSolicitud(@RequestBody SolicitudDTO solicitudDTO){
        return solicitudService.registrarSolicitud(solicitudDTO);
    }
    @PostMapping("/registrar")
    public ResponseEntity<SolicitudResponse> saveSolicitudV2(@RequestBody SolicitudDTO solicitudDTO, @RequestHeader HttpHeaders headers){
        try{
            String header = headers.get("authorization").get(0);
            JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
            String usuarioCreacion = jsonObject.getString("sub");
            logger.info(usuarioCreacion);
            return solicitudService.registrarSolicitud(solicitudDTO, usuarioCreacion);
        }catch (Exception ex){
            SolicitudResponse solicitudResponse = new SolicitudResponse();
            solicitudResponse.setCodigoSolicitud("NULL");
            solicitudResponse.setMessage("ERROR");
            solicitudResponse.setEstado("500");
            return new ResponseEntity<>(solicitudResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/modificar")
    public ResponseEntity<SolicitudResponse> modifySolicitud(@RequestBody SolicitudDTO solicitudDTO, @RequestHeader HttpHeaders headers){
        String header = headers.get("authorization").get(0);
        JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
        String usuarioCreacion = jsonObject.getString("sub");
        return solicitudService.modificarSolicitud(solicitudDTO, usuarioCreacion);
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
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<SolicitudResponse> eliminarSolicitud(@PathVariable Long id){
        return solicitudService.eliminarSolicitud(id);
    }
}
