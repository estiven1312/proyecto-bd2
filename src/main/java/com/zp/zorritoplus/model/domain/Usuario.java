package com.zp.zorritoplus.model.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Data

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSeq")
    @SequenceGenerator(name = "usuarioSeq", sequenceName = "USUARIO_SEQ", schema = "ZP_PORTAL", allocationSize = 10, initialValue = 10)
    @Column(name = "USUARIO_ID")
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROL_ID")
    private Rol rol;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "CONTRASENIA")
    private String contrasenia;
    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ACTIVO", nullable = false)
    private Boolean activo ;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Queja> quejas;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudes;
}
