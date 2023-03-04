package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.domain.Solicitud;
import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.SolicitudDTO;
import com.zp.zorritoplus.model.response.SolicitudResponse;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.repository.PlataformaRepository;
import com.zp.zorritoplus.repository.SolicitudRepository;
import com.zp.zorritoplus.repository.UsuarioRepository;
import com.zp.zorritoplus.service.SolicitudService;
import com.zp.zorritoplus.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    Logger logger = LoggerFactory.getLogger(SolicitudServiceImpl.class);
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PlataformaRepository plataformaRepository;

    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    CatalogoRepository catalogoRepository;
    @Override
    @Transactional
    public ResponseEntity<SolicitudResponse> registrarSolicitud(SolicitudDTO solicitudDTO) {
        SolicitudResponse solicitudResponse = new SolicitudResponse();
        HttpStatus httpStatus;
        try {
            Date fechaSolicitud = new Date();
            Date fechaInicio = DateUtil.convertStringToDate(solicitudDTO.getFechaInicioSolicitud(), DateUtil.FORMAT_DATE_XML);
            Date fechaFin = DateUtil.convertStringToDate(solicitudDTO.getFechaFinSolicitud(), DateUtil.FORMAT_DATE_XML);
            Usuario usuario = usuarioRepository.findById(solicitudDTO.getIdUsuario()).orElse(null);
            Plataforma plataforma = plataformaRepository.findById(solicitudDTO.getIdPlataforma()).orElse(null);
            Catalogo estado = catalogoRepository.findById(610L).orElse(null);
            Solicitud solicitud = new Solicitud();
            solicitud.setUsuario(usuario);
            solicitud.setCodigoPago(solicitudDTO.getCodigoPago());
            solicitud.setEstado(estado);
            solicitud.setPlataforma(plataforma);
            solicitud.setFechaSolicitud(fechaSolicitud);
            solicitud.setFechaInicio(fechaInicio);
            solicitud.setFechaFin(fechaFin);
            solicitud = solicitudRepository.save(solicitud);
            httpStatus = HttpStatus.OK;
            solicitudResponse.setCodigoSolicitud(solicitud.getId().toString());
            solicitudResponse.setEstado("200");
            solicitudResponse.setMessage("Solicitud guardada correctamente");

        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            solicitudResponse.setCodigoSolicitud("Error");
            solicitudResponse.setEstado("500");
            logger.info(ex.getMessage(), ex);
            solicitudResponse.setMessage("Error al guardar la solicitud");
        }
        return new ResponseEntity<>(solicitudResponse, httpStatus);
    }
}
