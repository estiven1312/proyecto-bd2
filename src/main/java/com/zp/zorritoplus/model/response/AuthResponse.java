package com.zp.zorritoplus.model.response;

import com.zp.zorritoplus.model.dto.UsuarioDTO;
import lombok.Data;

public @Data class AuthResponse {
    private String token;
    private String date;
    private String codigo;
    private UsuarioDTO usuarioDTO;
}
