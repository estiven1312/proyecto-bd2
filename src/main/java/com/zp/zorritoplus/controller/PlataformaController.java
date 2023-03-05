package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.dto.PlataformaDTO;
import com.zp.zorritoplus.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlataformaController {
    @Autowired
    PlataformaService plataformaService;
    @GetMapping
    public ResponseEntity<List<PlataformaDTO>> getAllPlataformas(){
        try{
            List<PlataformaDTO> plataformaList = plataformaService.getAllPlataformas();
            return new ResponseEntity<>(plataformaList, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
