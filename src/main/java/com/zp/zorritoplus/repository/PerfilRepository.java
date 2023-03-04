package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository  extends JpaRepository<Perfil, Long> {
}
