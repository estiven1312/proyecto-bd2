package com.zp.zorritoplus.model.domain;

import lombok.Data;

import javax.persistence.*;


@NamedStoredProcedureQueries({ //
        @NamedStoredProcedureQuery(name = "findAllCatalogosByCursor", procedureName = "SP_FIND_ALL_CATALOGO",
                resultClasses = Catalogo.class, parameters = { //
                @StoredProcedureParameter(name = "catalogo_cursor", mode = ParameterMode.REF_CURSOR, type = void.class) }) //
})
@Entity
@Data
@Table(name = "CATALOGO")
@SequenceGenerator(name = "catalogoSeq", sequenceName = "CATALOGO_SEQ", schema = "ZP_PORTAL", allocationSize = 10, initialValue = 10)
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogoSeq")
    @Column(name = "CATALOGO_ID")
    private Long id;
    private String nombre;
    private String descripcion;
    private String abreviatura;
}
