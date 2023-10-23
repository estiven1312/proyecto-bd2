package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TIPO_QUEJA")
public class TipoQueja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIPO_QUEJA_ID")
    private Long id;
    @Column(name = "CATEGORIA_QUEJA")
    private String categoriaQueja;
    @Column(name = "ESCALA_QUEJA")
    private Integer escalaQueja;
}
