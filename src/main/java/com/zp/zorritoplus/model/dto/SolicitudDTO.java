package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Solicitud;
import com.zp.zorritoplus.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class SolicitudDTO {
    private Long id;
    private String usuario;
    private Long idUsuario;
    private String plataforma;
    private Long idPlataforma;
    private String fechaInicioSolicitud;
    private String fechaFinSolicitud;
    private String codigoPago;
    public SolicitudDTO (Solicitud solicitud){
        this.id = solicitud.getId();
        this.idUsuario = solicitud.getUsuario().getId();
        this.usuario = solicitud.getUsuario().getCorreo();
        this.codigoPago = solicitud.getCodigoPago();
        this.idPlataforma = solicitud.getPlataforma().getId();
        this.plataforma = solicitud.getPlataforma().getNombrePlataforma();
        this.fechaFinSolicitud = DateUtil.convertDateToString(solicitud.getFechaFin(), DateUtil.FORMAT_DATE);
        this.fechaInicioSolicitud = DateUtil.convertDateToString(solicitud.getFechaInicio(), DateUtil.FORMAT_DATE);
    }
}
