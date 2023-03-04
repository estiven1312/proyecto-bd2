package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {

}
