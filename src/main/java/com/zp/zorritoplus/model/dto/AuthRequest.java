package com.zp.zorritoplus.model.dto;

import lombok.Data;

public @Data class AuthRequest {
    private String user;
    private String password;
}
