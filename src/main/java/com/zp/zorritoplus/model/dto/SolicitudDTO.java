package com.zp.zorritoplus.model.dto;

import lombok.Data;

public @Data class SolicitudDTO {
    private String usuario;
    private Long idUsuario;
    private String plataforma;
    private Long idPlataforma;
    private String fechaInicioSolicitud;
    private String fechaFinSolicitud;
    private String codigoPago;
}
