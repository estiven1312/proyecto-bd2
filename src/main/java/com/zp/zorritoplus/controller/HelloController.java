package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.domain.Catalogo;
import com.zp.zorritoplus.model.domain.Rol;
import com.zp.zorritoplus.repository.CatalogoRepository;
import com.zp.zorritoplus.repository.RolRepository;
import com.zp.zorritoplus.repository.RolRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloController {

    Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    CatalogoRepository catalogoRepository;
    @Autowired
    RolRepositoryImpl rolRepository;

    @GetMapping("/test01")
    @Transactional
    public void test1(){
        Catalogo catalogo = catalogoRepository.findById(10L).get();
        LOGGER.info(catalogo.toString());
        List<Catalogo> catalogoList = catalogoRepository.findAllCatalogosByCursor();


        Rol rol = new Rol();
        rol = rolRepository.rolByStoredProcedure(10L);
        LOGGER.info(catalogoList.toString());
        LOGGER.info("=>>>>>>>>" + "  " +rol.toString());

    }
}
