package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.PerfilDTO;
import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.service.PerfilService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    Logger logger = LoggerFactory.getLogger(PerfilController.class);
    @Autowired
    PerfilService perfilService;

    @PostMapping("/aprobar")
    public ResponseEntity<ExitoResponse> aprobarSolicitud(@RequestBody PerfilDTO perfilDTO){
        return perfilService.aprobarSolicitud(perfilDTO);
    }
    @GetMapping("/admin")
    public ResponseEntity<List<PerfilDTO>> listarPerfil() {
        try {
            return perfilService.listaPerfiles();
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<PerfilDTO>> listarPerfilesByUser(@RequestHeader HttpHeaders headers) {
        try{
            String header = headers.get("authorization").get(0);
            JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
            String usuarioCreacion = jsonObject.getString("sub");
            logger.info(usuarioCreacion);
            return perfilService.listarPerfilesByUser(usuarioCreacion);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ExitoResponse> eliminarLogica(@PathVariable Long id) {
        return perfilService.eliminarPerfil(id);
    }
    @DeleteMapping("/eliminarBD/{id}")
    public ResponseEntity<ExitoResponse> eliminarBD(@PathVariable Long id) {
        return perfilService.eliminarPerfilBD(id);
    }
}
