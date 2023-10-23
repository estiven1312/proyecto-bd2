package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.service.UsuarioService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @GetMapping("/infoUser/{id}")
    public ResponseEntity<UsuarioDTO> infoUsuario(Long id){
        return usuarioService.infoUsuario(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ExitoResponse> deleteUser(@PathVariable Long id){
        return usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/info")
    public ResponseEntity<UsuarioDTO> infoUsuario( @RequestHeader HttpHeaders headers){
        String header = headers.get("authorization").get(0);
        JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
        String usuarioCreacion = jsonObject.getString("sub");
        return usuarioService.infoUsuario(usuarioCreacion);
    }

}

