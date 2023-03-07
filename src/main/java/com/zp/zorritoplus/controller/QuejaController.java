package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.dto.QuejaDTO;
import com.zp.zorritoplus.service.QuejaService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quejas")
public class QuejaController {

    @Autowired
    QuejaService quejaService;
    @GetMapping("/admin")
    public ResponseEntity<List<QuejaDTO>> getQuejasAdmin(@RequestHeader HttpHeaders headers){

        return quejaService.obtenerQuejasPorAtender();
    }
    @GetMapping("/user")
    public ResponseEntity<List<QuejaDTO>> getQuejasUsuario(@RequestHeader HttpHeaders headers){
        String header = headers.get("authorization").get(0);
        JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
        String usuarioCreacion = jsonObject.getString("sub");
        return quejaService.obtenerQuejasPorAtender(usuarioCreacion);
    }

}
