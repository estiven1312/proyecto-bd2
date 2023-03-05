package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Plataforma;
import com.zp.zorritoplus.model.dto.PlataformaDTO;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.repository.PlataformaRepository;
import com.zp.zorritoplus.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlataformaServiceImpl implements PlataformaService {
    @Autowired
    PlataformaRepository plataformaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<PlataformaDTO> getAllPlataformas() throws Exception {
        try{
            return plataformaRepository.findAllActivos().stream().map(PlataformaDTO::new).collect(Collectors.toList());

        } catch (Exception ex){
            throw  new Exception();
        }
    }

    @Override
    public ResponseEntity<ExitoResponse> registrarNuevaPlataforma(PlataformaDTO plataformaDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ExitoResponse> editarPlataforma(PlataformaDTO plataformaDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ExitoResponse> eliminarPlataforma(Long idPlataforma) {
        return null;
    }
}
