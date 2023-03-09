package com.zp.zorritoplus.controller;

import com.zp.zorritoplus.model.domain.TipoQueja;
import com.zp.zorritoplus.model.dto.QuejaDTO;
import com.zp.zorritoplus.model.response.QuejaResponse;
import com.zp.zorritoplus.service.QuejaService;
import com.zp.zorritoplus.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/tipos")
    public ResponseEntity<List<TipoQueja>> getTiposQueja(@RequestHeader HttpHeaders headers){

        return quejaService.getTipoQueja();
    }
    @GetMapping("/user")
    public ResponseEntity<List<QuejaDTO>> getQuejasUsuario(@RequestHeader HttpHeaders headers){
        String header = headers.get("authorization").get(0);
        JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
        String usuarioCreacion = jsonObject.getString("sub");
        return quejaService.obtenerQuejasPorAtender(usuarioCreacion);
    }
    @PostMapping("/emitirQueja")
    public ResponseEntity<QuejaResponse> ingresarQueja(@RequestBody QuejaDTO quejaDTO, @RequestHeader HttpHeaders headers){
        String header = headers.get("authorization").get(0);
        JSONObject jsonObject = JwtUtil.decodeBearerToken(header);
        String usuarioCreacion = jsonObject.getString("sub");
        return quejaService.registrarQueja(quejaDTO, usuarioCreacion);
    }
    @GetMapping("/atenderQueja/{id}")
    public ResponseEntity<QuejaResponse> atenderQueja(@PathVariable Long id){
        return quejaService.atenderQueja(id);
    }
    @PostMapping("/editarQueja")
    public ResponseEntity<QuejaResponse> editarQueja(@RequestBody QuejaDTO quejaDTO){
        return quejaService.editarQueja(quejaDTO);
    }
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<QuejaResponse> eliminarQueja(@PathVariable Long id){
        return quejaService.eliminarQueja(id);
    }
    @GetMapping("/eliminarBD/{id}")
    public ResponseEntity<QuejaResponse> eliminarQuejaBD(@PathVariable Long id){
        return quejaService.eliminarQuejaBD(id);
    }
}
