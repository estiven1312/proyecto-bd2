package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.dto.PlataformaDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/registrar")
    public ResponseEntity<ExitoResponse> registarPlataforma(@RequestBody PlataformaDTO plataformaDTO){
        return plataformaService.registrarNuevaPlataforma(plataformaDTO);
    }
    @PostMapping("/editar")
    public ResponseEntity<ExitoResponse> editarPlataforma(@RequestBody PlataformaDTO plataformaDTO){
        return plataformaService.editarPlataforma(plataformaDTO);
    }
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<ExitoResponse> eliminarPlataforma(@PathVariable Long id){
        return plataformaService.eliminarPlataforma(id);
    }
}
