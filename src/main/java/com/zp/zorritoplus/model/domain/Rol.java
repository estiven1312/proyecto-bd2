package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;



@Entity
@Data
@Table(name = "ROL")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CODIGO")
    private String codigo;
}
