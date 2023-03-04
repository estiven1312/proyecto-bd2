package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;


@NamedStoredProcedureQueries({ //
        @NamedStoredProcedureQuery(name = "findAllQuejasPorAtender", procedureName = "SP_FIND_QUEJAS_POR_ATENDER",
                resultClasses = Queja.class, parameters = { //
                @StoredProcedureParameter(name = "queja_cursor", mode = ParameterMode.REF_CURSOR, type = void.class) }) //
})
@Entity
@Table(name = "QUEJA")
@Data
public class Queja {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quejaSeq")
    @SequenceGenerator(name = "quejaSeq", sequenceName = "QUEJA_SEQ", schema = "ZP_PORTAL", allocationSize = 10, initialValue = 10)
    @Column(name = "QUEJA_ID")
    private Long id;
    private String comentario;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "TIPO_QUEJA_ID", nullable = false)
    private TipoQueja tipoQueja;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ESTADO_CATALOGO_ID", nullable = false)
    private Catalogo estado;
}
