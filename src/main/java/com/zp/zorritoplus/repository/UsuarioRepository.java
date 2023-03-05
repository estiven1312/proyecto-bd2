package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.correo = :correo AND usuario.contrasenia = :contrasenia")
    public Usuario findByCorreoAndPassword(@Param("correo") String correo, @Param("contrasenia") String contrasenia);
}
