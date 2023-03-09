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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class PerfilServiceImpl implements PerfilService {
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    CatalogoRepository catalogoRepository;
    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> aprobarSolicitud(PerfilDTO perfilDTO) {
        try {
            Solicitud solicitud = solicitudRepository.findById(perfilDTO.getIdSolicitud()).get();
            Perfil perfil = new Perfil();
            perfil.setPinPerfil(perfilDTO.getPinPerfil());
            perfil.setCorreoPerfil(perfilDTO.getCorreoPerfil());
            perfil.setContraseniaPerfil(perfilDTO.getContraseniaPerfil());
            perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
            perfil.setSolicitud(solicitud);
            Catalogo estado = catalogoRepository.findCatalogoByAbreviatura("ACTIVO");
            Catalogo estadoSolicitud = catalogoRepository.findCatalogoByAbreviatura("ATENDIDO");

            perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
            perfil.setEstado(estado);
            solicitud.setEstado(estadoSolicitud);
            solicitudRepository.save(solicitud);
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
    @Transactional
    public ResponseEntity<List<PerfilDTO>> listaPerfiles() {
        try {
            List<PerfilDTO> perfilDTOS = perfilRepository.findPerfilActivo().stream().map(PerfilDTO::new).toList();
            return new ResponseEntity<>(perfilDTOS, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> eliminarPerfil(Long id) {
        try{
            Catalogo estado = catalogoRepository.findCatalogoByAbreviatura("ELIMINADO");
            Perfil perfil = perfilRepository.findById(id).get();
            perfil.setEstado(estado);
            perfilRepository.save(perfil);
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("200");
            response.setMensaje("Eliminado con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex){
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("500");
            response.setMensaje("Fallo al eliminar");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> eliminarPerfilBD(Long id) {
        try{
            perfilRepository.deleteById(id);
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("200");
            response.setMensaje("Eliminado con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex){
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("500");
            response.setMensaje("Fallo al eliminar");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<PerfilDTO>> listarPerfilesByUser(String correo) {
        try {
            List<PerfilDTO> perfilDTOS = perfilRepository.findPerfilByUsuario(correo).stream().map(PerfilDTO::new).toList();
            return new ResponseEntity<>(perfilDTOS, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
