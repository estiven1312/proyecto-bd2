package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.dto.AuthRequest;
import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.AuthResponse;
import com.zp.zorritoplus.model.response.ExitoResponse;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest);
    public ResponseEntity<ExitoResponse> editDatosByUser(UsuarioDTO usuarioDTO);
    public ResponseEntity<ExitoResponse> registrarUsuario(UsuarioDTO usuarioDTO);
    public ResponseEntity<ExitoResponse> eliminarUsuario(UsuarioDTO usuarioDTO);
}
