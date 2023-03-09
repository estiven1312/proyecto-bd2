package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Plataforma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public @Data class PlataformaDTO {
    private Long id;
    private String nombrePlataforma;
    private Float precioPlataforma;
    private String estado;

    public PlataformaDTO(Plataforma plataforma){
        this.nombrePlataforma = plataforma.getNombrePlataforma();
        this.id = plataforma.getId();
        this.precioPlataforma = plataforma.getPrecioPlataforma();
        this.estado = plataforma.getEstado().getNombre();
    }
}
