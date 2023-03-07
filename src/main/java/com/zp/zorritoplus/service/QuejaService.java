package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.domain.Queja;
import com.zp.zorritoplus.model.dto.QuejaDTO;
import com.zp.zorritoplus.model.response.QuejaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuejaService {
    public ResponseEntity<List<QuejaDTO>> obtenerQuejasPorAtender();

    public ResponseEntity<List<QuejaDTO>> obtenerQuejasPorAtender(String usuario);

    public ResponseEntity<QuejaResponse> registrarQueja(QuejaDTO quejaDTO);

    public  ResponseEntity<QuejaResponse>  atenderQueja(Long idQueja);

    public ResponseEntity<QuejaResponse> eliminarQueja(Long idQueja);

    public ResponseEntity<QuejaResponse> editarQueja(QuejaDTO quejaDTO);

    public ResponseEntity<QuejaResponse> eliminarQuejaBD(Long idQueja);

}
