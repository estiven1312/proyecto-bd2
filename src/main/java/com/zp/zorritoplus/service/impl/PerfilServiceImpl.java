package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Perfil;
import com.zp.zorritoplus.model.domain.Solicitud;
import com.zp.zorritoplus.model.dto.PerfilDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.repository.PerfilRepository;
import com.zp.zorritoplus.repository.SolicitudRepository;
import com.zp.zorritoplus.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PerfilServiceImpl implements PerfilService {
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    CatalogoRepository catalogoRepository;
    @Override
    public ResponseEntity<ExitoResponse> aprobarSolicitud(PerfilDTO perfilDTO) {
        try {
            Solicitud solicitud = solicitudRepository.findById(perfilDTO.getIdSolicitud()).get();
            Perfil perfil = new Perfil();
            perfil.setPinPerfil(perfilDTO.getPinPerfil());
            perfil.setCorreoPerfil(perfilDTO.getCorreoPerfil());
            perfil.setContraseniaPerfil(perfilDTO.getContraseniaPerfil());
            perfil.setSolicitud(solicitud);
            Catalogo estado = catalogoRepository.findCatalogoByAbreviatura("ACTIVO");
            perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
            perfil.setEstado(estado);
            perfilRepository.save(perfil);
            ExitoResponse response = new ExitoResponse();
            response.setMensaje("Se aprobo el perfil correctamente");
            response.setCodigo("200");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            ExitoResponse response = new ExitoResponse();
            response.setMensaje("No se realizo correctamente");
            response.setCodigo("500");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<PerfilDTO>> listaPerfiles() {
        return null;
    }

    @Override
    public ResponseEntity<ExitoResponse> eliminarPerfil(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<PerfilDTO>> listarPerfilesByUser(String correo) {
        return null;
    }
}
