package com.zp.zorritoplus.model.dto;

import lombok.Data;

import javax.persistence.Column;

public @Data class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String dni;
    private String contrasenia;
}
