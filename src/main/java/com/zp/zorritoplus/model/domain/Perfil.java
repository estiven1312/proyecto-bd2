package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PERFIL")
@Data
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFIL_ID")
    private Long id;
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Column(name = "CORREO_PERFIL")
    private String correoPerfil;
    @Column(name = "CONTRASENIA_PERFIL")
    private String contraseniaPerfil;
    @Column(name = "PIN_PERFIL")
    private String pinPerfil;
    @OneToOne
    @JoinColumn(name = "SOLICITUD_ID")
    private Solicitud solicitud;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ESTADO_CATALOGO_ID", nullable = false)
    private Catalogo estado;
}
