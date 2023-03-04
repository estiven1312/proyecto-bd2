package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.TipoQueja;
import com.zp.zorritoplus.model.domain.Usuario;
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
}
