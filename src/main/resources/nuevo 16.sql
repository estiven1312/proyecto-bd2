--CREACION DE USUARIO

ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER ZP_PORTAL IDENTIFIED BY portalzorrito
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP
PROFILE DEFAULT;

GRANT connect to ZP_PORTAL;
grant resource to ZP_PORTAL;
GRANT UNLIMITED TABLESPACE TO ZP_PORTAL;

GRANT CREATE TABLE TO ZP_PORTAL;
GRANT CREATE SESSION TO ZP_PORTAL;
GRANT CREATE SEQUENCE TO ZP_PORTAL;
GRANT CREATE VIEW TO ZP_PORTAL;
GRANT CREATE TRIGGER TO ZP_PORTAL;
GRANT CREATE PROCEDURE TO ZP_PORTAL;
GRANT EXECUTE ANY PROCEDURE TO ZP_PORTAL;










CREATE TABLE TEST(
    test_field VARCHAR2(50)
);


CREATE TABLE CATALOGO(
    catalogo_id number,
    nombre varchar2(50) CONSTRAINT catalogo_nombre_notnull NOT NULL,
    descripcion varchar2(50) CONSTRAINT catalogo_descripcion_notnull NOT NULL,
    abreviatura varchar2(50) CONSTRAINT catalogo_abreviatura_notnull NOT NULL,
    CONSTRAINT catalogo_pk PRIMARY KEY(catalogo_id),
    CONSTRAINT catalogo_abreviatura_unique UNIQUE(abreviatura)
);

CREATE SEQUENCE ZP_PORTAL.CATALOGO_SEQ START WITH 10 INCREMENT BY 10;
desc catalogo;
INSERT INTO CATALOGO(catalogo_id, nombre, descripcion, abreviatura) VALUES(ZP_PORTAL.CATALOGO_SEQ.nextVAL, 'RECHAZADO', 'ITEM EN ESTADO RECHAZADO', 'REC_ADO');
commit;
SELECT * FROM CATALOGO;
CREATE OR REPLACE PROCEDURE SP_FIND_ALL_CATALOGO(catalogo_cursor OUT SYS_REFCURSOR)
IS
    begin
        OPEN catalogo_cursor FOR SELECT * FROM CATALOGO;
    END;
    
    
DECLARE 
    catalogo SYS_REFCURSOR;
    v_catalogo ZP_PORTAL.CATALOGO%rowtype;
BEGIN
    sp_find_all_catalogo(catalogo);
    loop
        fetch catalogo into v_catalogo;
        EXIT WHEN catalogo%NOTFOUND;
        DBMS_OUTPUT.put_line(v_catalogo.abreviatura);
    end loop;
    close catalogo;
end;

CREATE TABLE tipo_queja (
    tipo_queja_id   NUMBER
        CONSTRAINT tipo_queja_pk PRIMARY KEY,
    categoria_queja VARCHAR2(50)
        CONSTRAINT tipo_queja_categoria_notnull NOT NULL,
    escala_queja    NUMBER
        CONSTRAINT escala_queja_range CHECK ( escala_queja >= 0
                                              AND escala_queja <= 5 )
);

CREATE SEQUENCE zp_portal.tipo_queja_seq START WITH 10 INCREMENT BY 10;

INSERT INTO tipo_queja (
    tipo_queja_id,
    categoria_queja,
    escala_queja
) VALUES (
    zp_portal.tipo_queja_seq.nextval,
    'QUEJA DE CUENTA',
    5
);

CREATE TABLE ROL(
    rol_id number CONSTRAINT rol_pk PRIMARY KEY,
    nombre varchar2(50) CONSTRAINT rol_nombre_notnull NOT NULL, CONSTRAINT rol_nombre_unique UNIQUE (nombre),
    codigo varchar2(50) CONSTRAINT rol_codigo_notnull NOT NULL, CONSTRAINT rol_codigo_unique UNIQUE (codigo)
);

CREATE SEQUENCE zp_portal.ROL_SEQ START WITH 10 INCREMENT BY 10;

CREATE TABLE USUARIO(
    usuario_id number CONSTRAINT usuario_pk PRIMARY KEY,
    rol_id number CONSTRAINT rol_fk NOT NULL,
    nombre varchar2(50) CONSTRAINT usuario_nombre_notnull NOT NULL,
    apellido varchar2(50) CONSTRAINT usuario_apellido_notnull NOT NULL,
    celular varchar2(10) CONSTRAINT usuario_celular_format CHECK(REGEXP_LIKE(celular, '[[:digit:]]{10}')),
    correo  VARCHAR2(50) CONSTRAINT usuario_correo_format CHECK(REGEXP_LIKE(correo, '^[[:alpha:]][[:alnum:]]{1,25}@[[:alpha:]]{5,19}.[[:alpha:]]{2,3}')), CONSTRAINT usuario_correo_unique UNIQUE (correo),
    dni VARCHAR2(8) CONSTRAINT usuario_dni_format CHECK(REGEXP_LIKE(dni, '[[:digit:]]{8}')), CONSTRAINT usuario_dni_unique UNIQUE(dni),
    contrasenia VARCHAR2(50) CONSTRAINT usuario_contrasenia_notnull NOT NULL,
    CONSTRAINT usuario_rol_fk FOREIGN KEY (rol_id) REFERENCES ROL(rol_id)
);
CREATE SEQUENCE zp_portal.USUARIO_SEQ START WITH 10 INCREMENT BY 10;

CREATE TABLE PLATAFORMA(
    plataforma_id number CONSTRAINT plataforma_pk PRIMARY KEY,
    nombre_plataforma varchar2(50) CONSTRAINT plataforma_nombre_not_null NOT NULL, CONSTRAINT plataforma_nombre_unique UNIQUE(nombre_plataforma),
    precio_plataforma number CONSTRAINT precio_plataforma_non_negative CHECK(precio_plataforma>0)
);
CREATE SEQUENCE zp_portal.PLATAFORMA_SEQ START WITH 10 INCREMENT BY 10;


CREATE TABLE SOLICITUD(
    solicitud_id number CONSTRAINT solicitud_pk PRIMARY KEY,
    usuario_id number CONSTRAINT solicitud_usuario_id_not_null NOT NULL,
    plataforma_id number CONSTRAINT solicitud_plataforma_id_not_null NOT NULL,
    fecha_solicitud date DEFAULT SYSDATE CONSTRAINT solicitud_fecha_solicitud_not_null  NOT NULL,
    fecha_inicio date CONSTRAINT solicitud_fecha_inicio_not_null  NOT NULL,
    fecha_fin date CONSTRAINT solicitud_fecha_fin_not_null  NOT NULL,
    codigo_pago varchar2(50) CONSTRAINT codigo_pago_not_null NOT NULL,
    estado_catalogo_id number CONSTRAINT solicitud_estado_catalogo_id NOT NULL,
    CONSTRAINT solicitud_codigo_pago_unique UNIQUE(codigo_pago),
    CONSTRAINT solicitud_usuario_fk FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id),
    CONSTRAINT solicitud_plataforma_fk FOREIGN KEY (plataforma_id) REFERENCES PLATAFORMA(plataforma_id),
    CONSTRAINT solicitud_estado_catalogo_fk FOREIGN KEY (estado_catalogo_id) REFERENCES CATALOGO(catalogo_id)
);
CREATE SEQUENCE zp_portal.SOLICITUD_SEQ START WITH 10 INCREMENT BY 10;

CREATE TABLE QUEJA(
    queja_id number CONSTRAINT queja_pk PRIMARY KEY,
    usuario_id number CONSTRAINT queja_usuario_id_not_null NOT NULL,
    comentario VARCHAR2(500) CONSTRAINT queja_comentario_not_null NOT NULL,
    tipo_queja_id number CONSTRAINT queja_tipo_queja_id_not_null NOT NULL,
    estado_catalogo_id number CONSTRAINT queja_estado_catalogo_id_not_null NOT NULL,
    CONSTRAINT queja_usuario_fk FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id),
    CONSTRAINT queja_tipo_queja_fk FOREIGN KEY (tipo_queja_id) REFERENCES tipo_queja(tipo_queja_id),
    CONSTRAINT queja_estado_catalogo_fk FOREIGN KEY (estado_catalogo_id) REFERENCES CATALOGO(catalogo_id)
);

DROP TABLE QUEJA;
CREATE SEQUENCE zp_portal.QUEJA_SEQ START WITH 10 INCREMENT BY 10;
DROP TABLE PERFIL
CREATE TABLE PERFIL(
    perfil_id number CONSTRAINT perfil_pk PRIMARY KEY,
    nombre_perfil VARCHAR2(50) CONSTRAINT nombre_perfil_not_null NOT NULL,
    correo_perfil  VARCHAR2(80) CONSTRAINT correo_perfil_format CHECK(REGEXP_LIKE(correo_perfil, '^[[:alpha:]][[:alnum:]]{1,25}@[[:alpha:]]{5,19}.[[:alpha:]]{2,3}')),
    contrasenia_perfil VARCHAR2(50) CONSTRAINT contrasenia_perfil_not_null NOT NULL,
    pin_perfil VARCHAR2(6) CONSTRAINT pin_perfil_format CHECK(REGEXP_LIKE(pin_perfil, '[[:digit:]]{6}')),
    solicitud_id number CONSTRAINT solicitud_id_not_null NOT NULL,
    estado_catalogo_id number CONSTRAINT perfil_estado_catalogo_id_not_null NOT NULL,
    CONSTRAINT solicitud_id_unique UNIQUE(solicitud_id),
    CONSTRAINT perfil_solicitud_fk FOREIGN KEY (solicitud_id) REFERENCES SOLICITUD(solicitud_id),
    CONSTRAINT perfil_estado_fk FOREIGN KEY (estado_catalogo_id) REFERENCES CATALOGO(catalogo_id)

);

CREATE SEQUENCE zp_portal.PERFIL_SEQ START WITH 10 INCREMENT BY 10;




SELECT * FROM CATALOGO;

select * from tipo_queja;
SELECT * FROM QUEJA;



SELECT * FROM ROL;

SELECT * FROM USUARIO;

DESC USUARIO;
INSERT INTO USUARIO (USUARIO_ID, ROL_ID, NOMBRE, APELLIDO , CELULAR, CORREO, DNI, CONTRASENIA) values(zp_portal.usuario_seq.nextval, 20, 'JASMIN ROSARIO', 'SANEZ PONCE', '51972217143', 'JAROSAPO@gmail.com', '72157891', 'micontrasenia2')
UPDATE USUARIO SET celular = 51933833682 WHERE usuario_id = 20;
commit;
ALTER TABLE USUARIO DROP CONSTRAINT usuario_celular_format;

ALTER  TABLE USUARIO MODIFY CELULAR VARCHAR(12);

ALTER TABLE USUARIO ADD CONSTRAINT usuario_celular_format
     CHECK(REGEXP_LIKE(celular, '[[:digit:]]{11,12}'));


INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 30, 'COMENTARIO DE PRUEBA', 20, 610);

INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 30, 'COMENTARIO DE PRUEBA 2', 20, 620);


INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 20, 'COMENTARIO DE PRUEBA 3', 20, 610);

INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 20, 'COMENTARIO DE PRUEBA 4', 250, 610);

INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 20, 'COMENTARIO DE PRUEBA 5', 240, 620);

INSERT INTO QUEJA(QUEJA_ID, USUARIO_ID, COMENTARIO, TIPO_QUEJA_ID, ESTADO_CATALOGO_ID) VALUES (ZP_PORTAL.QUEJA_SEQ.NEXTVAL, 20, 'COMENTARIO DE PRUEBA 6', 230, 610);


CREATE OR REPLACE PROCEDURE SP_FIND_QUEJAS_POR_ATENDER(queja_cursor OUT SYS_REFCURSOR)
IS
begin
    OPEN queja_cursor FOR SELECT * FROM QUEJA WHERE ESTADO_CATALOGO_ID = 610;
END;

SET SERVEROUTPUT ON

DECLARE 
    queja_cursor SYS_REFCURSOR;
    v_QUEJA ZP_PORTAL.QUEJA%rowtype;
BEGIN
    SP_FIND_QUEJAS_POR_ATENDER(queja_cursor);
    loop
        fetch queja_cursor into v_QUEJA;
        EXIT WHEN queja_cursor%NOTFOUND;
        DBMS_OUTPUT.put_line(v_QUEJA.QUEJA_ID||'  ' ||v_QUEJA.COMENTARIO);
    end loop;
    close queja_cursor;
end;
