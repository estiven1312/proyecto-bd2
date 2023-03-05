package com.zp.zorritoplus.model.response;

import lombok.Data;

public @Data class AuthResponse {
    private String token;
    private String date;
    private String codigo;
}
