package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.AuthRequest;
import com.zp.zorritoplus.model.response.AuthResponse;
import com.zp.zorritoplus.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return usuarioService.authenticateUser(authRequest);
    }
}
