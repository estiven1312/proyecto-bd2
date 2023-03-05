package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Queja;
import com.zp.zorritoplus.model.domain.TipoQueja;
import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.QuejaDTO;
import com.zp.zorritoplus.model.response.QuejaResponse;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.repository.QuejaRepository;
import com.zp.zorritoplus.repository.TipoQuejaRepository;
import com.zp.zorritoplus.repository.UsuarioRepository;
import com.zp.zorritoplus.service.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuejaServiceImpl implements QuejaService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TipoQuejaRepository tipoQuejaRepository;

    @Autowired
    CatalogoRepository catalogoRepository;

    @Autowired
    QuejaRepository quejaRepository;

    @Override
    public ResponseEntity<List<Queja>> obtenerQuejasPorAtender() {
        try {
            List<Queja> quejas = this.quejaRepository.findQuejasPorAtender();
            return new ResponseEntity<>(quejas, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Queja>> obtenerQuejasPorAtender(Long idUsuario) {
        try {
            List<Queja> quejas = this.quejaRepository.findQuejasPorAtender(idUsuario);
            return new ResponseEntity<>(quejas, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<QuejaResponse> registrarQueja(QuejaDTO quejaDTO) {
        HttpStatus httpStatus;
        QuejaResponse quejaResponse = new QuejaResponse();
        try{
            Usuario usuario = usuarioRepository.findById(quejaDTO.getIdUsuario()).get();
            TipoQueja tipoQueja = tipoQuejaRepository.findById(quejaDTO.getIdTipoQueja()).get();
            Catalogo catalogo = catalogoRepository.findById(10L).get();
            Queja queja = new Queja();
            queja.setTipoQueja(tipoQueja);
            queja.setEstado(catalogo);
            queja.setComentario(quejaDTO.getComentario());
            queja.setUsuario(usuario);
            quejaRepository.save(queja);
            quejaResponse.setCodigo("200");
            quejaResponse.setMensaje("Registrado correctamente");
            httpStatus = HttpStatus.OK;
        } catch (Exception ex){
            quejaResponse.setCodigo("500");
            quejaResponse.setMensaje("Error al registrar la queja");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(quejaResponse, httpStatus);
    }

    @Override
    @Transactional
    public  ResponseEntity<QuejaResponse>  atenderQueja(Long idQueja) {
        HttpStatus httpStatus;
        QuejaResponse quejaResponse = new QuejaResponse();
        try {
            Queja queja = quejaRepository.findById(idQueja).orElse(null);
            Catalogo estado = catalogoRepository.findById(20L).orElse(null);
            queja.setEstado(estado);
            quejaRepository.save(queja);
            quejaResponse.setMensaje("Se actualizó el estado de la queja correctamente");
            quejaResponse.setCodigo("200");
            httpStatus = HttpStatus.OK;
        }catch (Exception ex){
            quejaResponse.setMensaje("Error al actualizar el estado de la queja");
            quejaResponse.setCodigo("500");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        }

        return new ResponseEntity<>(quejaResponse, httpStatus);
    }

    @Override
    public ResponseEntity<QuejaResponse> eliminarQueja(Long idQueja) {
        HttpStatus httpStatus;
        QuejaResponse quejaResponse = new QuejaResponse();
        try {
            Queja queja = quejaRepository.findById(idQueja).orElse(null);
            Catalogo estado = catalogoRepository.findCatalogoByAbreviatura("ELIMINADO");
            queja.setEstado(estado);
            quejaRepository.save(queja);
            quejaResponse.setMensaje("Se actualizó el estado de la queja correctamente");
            quejaResponse.setCodigo("200");
            httpStatus = HttpStatus.OK;
        }catch (Exception ex){
            quejaResponse.setMensaje("Error al actualizar el estado de la queja");
            quejaResponse.setCodigo("500");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(quejaResponse, httpStatus);
    }

    @Override
    @Transactional
    public ResponseEntity<QuejaResponse> editarQueja(QuejaDTO quejaDTO) {
        try {
            Queja queja = quejaRepository.findById(quejaDTO.getId()).orElse(null);
            TipoQueja tipoQueja = tipoQuejaRepository.findById(quejaDTO.getIdTipoQueja()).orElse(null);
            queja.setTipoQueja(tipoQueja);
            queja.setComentario(quejaDTO.getComentario());
            quejaRepository.save(queja);
            QuejaResponse quejaResponse = new QuejaResponse();
            quejaResponse.setCodigo("200");
            quejaResponse.setMensaje("Se ha editado con exito la queja");
            return new ResponseEntity<>(quejaResponse, HttpStatus.OK);
        }catch (Exception ex){
            QuejaResponse quejaResponse = new QuejaResponse();
            quejaResponse.setCodigo("500");
            quejaResponse.setMensaje("Error al editar");
            return new ResponseEntity<>(quejaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
