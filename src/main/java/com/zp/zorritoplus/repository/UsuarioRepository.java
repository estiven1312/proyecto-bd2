package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
