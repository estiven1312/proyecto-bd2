package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.dto.PlataformaDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.repository.PlataformaRepository;
import com.zp.zorritoplus.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlataformaServiceImpl implements PlataformaService {
    @Autowired
    PlataformaRepository plataformaRepository;

    @Autowired
    CatalogoRepository catalogoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<PlataformaDTO> getAllPlataformas() throws Exception {
        try{
            return plataformaRepository.findAllActivos().stream().map(PlataformaDTO::new).collect(Collectors.toList());

        } catch (Exception ex){
            throw  new Exception();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> registrarNuevaPlataforma(PlataformaDTO plataformaDTO) {
        try{
            Plataforma plataforma = new Plataforma();
            plataforma.setNombrePlataforma(plataformaDTO.getNombrePlataforma());
            plataforma.setPrecioPlataforma(plataformaDTO.getPrecioPlataforma());
            Catalogo catalogo = catalogoRepository.findCatalogoByAbreviatura("ACTIVO");
            plataforma.setEstado(catalogo);
            plataformaRepository.save(plataforma);
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("200");
            response.setMensaje("Se ha registrado la plataforma correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("500");
            response.setMensaje("Error al registrar la plataforma");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> editarPlataforma(PlataformaDTO plataformaDTO) {
        try{
            Plataforma plataforma = plataformaRepository.findById(plataformaDTO.getId()).get();
            plataforma.setNombrePlataforma(plataformaDTO.getNombrePlataforma());
            plataformaDTO.setPrecioPlataforma(plataformaDTO.getPrecioPlataforma());
            plataformaRepository.save(plataforma);
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("200");
            response.setMensaje("Plataforma editada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            ExitoResponse response = new ExitoResponse();
            response.setCodigo("500");
            response.setMensaje("No se pudo editar en este momento");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> eliminarPlataforma(Long idPlataforma) {
        try{
            Plataforma plataforma = plataformaRepository.findById(idPlataforma).get();
            Catalogo estado = catalogoRepository.findCatalogoByAbreviatura("ELIMINADO");
            plataforma.setEstado(estado);
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("200");
            exitoResponse.setMensaje("Se elimino correctamente");
            return new ResponseEntity<>(exitoResponse, HttpStatus.OK);
        }catch (Exception ex){
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("500");
            exitoResponse.setMensaje("No se pudo eliminar");
            return new ResponseEntity<>(exitoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
