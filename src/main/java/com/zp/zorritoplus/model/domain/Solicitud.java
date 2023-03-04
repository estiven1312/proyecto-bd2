package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SOLICITUD")
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitudSeq")
    @SequenceGenerator(name = "solicitudSeq", sequenceName = "SOLICITUD_SEQ", schema = "ZP_PORTAL", allocationSize = 10, initialValue = 10)
    @Column(name = "SOLICITUD_ID")
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLATAFORMA_ID", nullable = false)
    private Plataforma plataforma;
    @Column(name = "FECHA_SOLICITUD", nullable = false)
    private Date fechaSolicitud;
    @Column(name = "FECHA_INICIO", nullable = false)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN", nullable = false)
    private Date fechaFin;
    @Column(name = "CODIGO_PAGO")
    private String codigoPago;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ESTADO_CATALOGO_ID")
    private Catalogo estado;
    @OneToOne(mappedBy = "solicitud")
    private Perfil perfil;
}
