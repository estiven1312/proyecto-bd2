package com.zp.zorritoplus.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

public @Data class PerfilDTO {
    private Long id;
    private String nombrePerfil;
    private String correoPerfil;
    private String contraseniaPerfil;
    private String pinPerfil;
    private Long idSolicitud;
    private Date fechaInicio;
    private Date fechaFin;
    private String usuario;
}
