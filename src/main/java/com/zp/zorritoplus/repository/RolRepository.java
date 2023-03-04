package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
