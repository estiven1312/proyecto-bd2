package com.zp.zorritoplus.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PLATAFORMA")
@Data

public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plataformaSeq")
    @SequenceGenerator(name = "plataformaSeq", sequenceName = "PLATAFORMA_SEQ", schema = "ZP_PORTAL", allocationSize = 10, initialValue = 10)
    @Column(name = "plataforma_id")
    private Long id;
    @Column(name = "nombre_plataforma")
    private String nombrePlataforma;
    @Column(name = "precio_plataforma")
    private Float precioPlataforma;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ESTADO", nullable = false)
    private Catalogo estado;
}
