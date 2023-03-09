package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.dto.PerfilDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerfilService {
    public ResponseEntity<ExitoResponse> aprobarSolicitud(PerfilDTO perfilDTO);
    public ResponseEntity<List<PerfilDTO>> listaPerfiles();
    public ResponseEntity<ExitoResponse> eliminarPerfil(Long id);
    public ResponseEntity<ExitoResponse> eliminarPerfilBD(Long id);
    public ResponseEntity<List<PerfilDTO>> listarPerfilesByUser(String correo);
}
