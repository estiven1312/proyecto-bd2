package com.zp.zorritoplus.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;



@Entity
@Data
@Table(name = "CATALOGO")
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATALOGO_ID")
    @JsonProperty
    private Long id;
    @JsonProperty
    private String nombre;
    private String descripcion;
    @JsonProperty
    private String abreviatura;
}
