package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.correo = :correo AND usuario.contrasenia = :contrasenia AND usuario.activo = true")
    public Usuario findByCorreoAndPassword(@Param("correo") String correo, @Param("contrasenia") String contrasenia);
    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.activo = true")
    public List<Usuario> findUsuariosActivos();
    @Query("SELECT u FROM Usuario u WHERE UPPER(u.correo) = UPPER(:correo)")
    public Usuario findUsuarioByCorreo(@Param("correo") String correo);
}
