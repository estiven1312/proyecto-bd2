package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.domain.Queja;
import com.zp.zorritoplus.model.dto.QuejaDTO;
import com.zp.zorritoplus.model.response.QuejaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuejaService {
    public List<Queja> obtenerQuejasPorAtender();

    public ResponseEntity<QuejaResponse> registrarQueja(QuejaDTO quejaDTO);

    public  ResponseEntity<QuejaResponse>  atenderQueja(Long idQueja);
}
