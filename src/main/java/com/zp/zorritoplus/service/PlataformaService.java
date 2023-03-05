package com.zp.zorritoplus.service;

import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.dto.PlataformaDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlataformaService {
    public List<PlataformaDTO> getAllPlataformas() throws Exception;
    public ResponseEntity<ExitoResponse> registrarNuevaPlataforma(PlataformaDTO plataformaDTO);
    public ResponseEntity<ExitoResponse> editarPlataforma(PlataformaDTO plataformaDTO);
    public ResponseEntity<ExitoResponse> eliminarPlataforma(Long idPlataforma);
}
