package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @PostMapping("/registrar")
    public ResponseEntity<ExitoResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.registrarUsuario(usuarioDTO);
    }
    @PostMapping("/editar")
    public ResponseEntity<ExitoResponse> editarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.editDatosByUser(usuarioDTO);
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<UsuarioDTO> infoUsuario(Long id){
        return usuarioService.infoUsuario(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ExitoResponse> deleteUser(@PathVariable Long id){
        return usuarioService.eliminarUsuario(id);
    }
}
