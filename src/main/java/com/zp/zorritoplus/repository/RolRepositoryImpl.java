package com.zp.zorritoplus.repository;

import com.zp.zorritoplus.model.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
public class RolRepositoryImpl{
    @Autowired
    EntityManager entityManager;
    public Rol rolByStoredProcedure(Long idRol) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_SELECT_ROL_BY_ID");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Object.class, ParameterMode.INOUT);
        storedProcedureQuery.setParameter(1, idRol);
        storedProcedureQuery.setParameter(2, new Object());


        Rol rolResult = (Rol) storedProcedureQuery.getOutputParameterValue(2);
        return rolResult;
    }
}
