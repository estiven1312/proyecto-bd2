package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.AuthRequest;
import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.AuthResponse;
import com.zp.zorritoplus.model.response.ExitoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioService {
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest);
    public ResponseEntity<ExitoResponse> editDatosByUser(UsuarioDTO usuarioDTO);
    public ResponseEntity<ExitoResponse> registrarUsuario(UsuarioDTO usuarioDTO);
    public ResponseEntity<ExitoResponse> eliminarUsuario(Long idUsuario);
    public ResponseEntity<UsuarioDTO> infoUsuario(Long idUsuario);

    @Transactional(readOnly = true)
    ResponseEntity<UsuarioDTO> infoUsuario(String idUsuario);

    public ResponseEntity<List<UsuarioDTO>> listaUsuario();
    public Usuario findUsuario(String correo);
}
