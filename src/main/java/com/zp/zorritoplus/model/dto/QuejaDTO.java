package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Queja;
import com.zp.zorritoplus.model.domain.TipoQueja;
import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.service.QuejaService;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public @Data class QuejaDTO {
    private Long id;
    private String comentario;
    private Long idUsuario;
    private String usuario;
    private String nombreTipoQueja;
    private Long idTipoQueja;
    private String estado;
    public QuejaDTO(Queja queja){
        this.id = queja.getId();
        this.comentario = queja.getComentario();
        this.idUsuario = queja.getUsuario().getId();
        this.usuario = queja.getUsuario().getCorreo();
        this.nombreTipoQueja = queja.getTipoQueja().getCategoriaQueja();
        this.idTipoQueja = queja.getTipoQueja().getId();
        this.estado = queja.getEstado().getAbreviatura();
    }
}
