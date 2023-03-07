package com.zp.zorritoplus.model.dto;

import com.zp.zorritoplus.model.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
public @Data class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String dni;
    private String contrasenia;
    private String rol;
    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.celular = usuario.getCelular();
        this.correo = usuario.getCorreo();
        this.dni = usuario.getDni();
        this.contrasenia = usuario.getContrasenia();
        this.rol = usuario.getRol().getNombre();
    }
}
