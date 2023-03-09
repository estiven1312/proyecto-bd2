package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
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
    private String plataformaPerfil;

    public PerfilDTO (Perfil perfil){
        this.id = perfil.getId();
        this.nombrePerfil = perfil.getNombrePerfil();
        this.correoPerfil = perfil.getCorreoPerfil();
        this.contraseniaPerfil = perfil.getContraseniaPerfil();
        this.pinPerfil = perfil.getPinPerfil();
        this.idSolicitud = perfil.getSolicitud().getId();
        this.fechaInicio = perfil.getSolicitud().getFechaInicio();
        this.fechaFin = perfil.getSolicitud().getFechaFin();
        this.usuario = perfil.getSolicitud().getUsuario().getCorreo();
        this.plataformaPerfil = perfil.getSolicitud().getPlataforma().getNombrePlataforma();
    }
}
