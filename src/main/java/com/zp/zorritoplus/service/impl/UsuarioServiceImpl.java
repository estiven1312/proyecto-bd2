package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Rol;
import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.AuthRequest;
import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.AuthResponse;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.repository.UsuarioRepository;
import com.zp.zorritoplus.service.UsuarioService;
import com.zp.zorritoplus.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) {
        try {
            Usuario usuario = usuarioRepository.findByCorreoAndPassword(authRequest.getUser(), authRequest.getPassword());
            if(usuario == null){
                throw new Exception("Error al autenticar las credenciales");
            }
            String tokenUsuario = this.getJWTToken(usuario);
            AuthResponse response = new AuthResponse();
            response.setCodigo("200");
            response.setToken(tokenUsuario);
            String horaMarcada = DateUtil.convertDateToString(new Date(), DateUtil.FORMAT_DATE_HOUR);
            response.setDate(horaMarcada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){

            AuthResponse response = new AuthResponse();
            response.setCodigo("500");
            response.setToken("");
            String horaMarcada = DateUtil.convertDateToString(new Date(), DateUtil.FORMAT_DATE_HOUR);
            response.setDate(horaMarcada);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<ExitoResponse> editDatosByUser(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ExitoResponse> registrarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ExitoResponse> eliminarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }
    private String getJWTToken(Usuario usuario) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(usuario.getRol().getCodigo());
        String token = Jwts
                .builder()
                .setId("ZORRITO_PLUS")
                .setSubject(usuario.getCorreo())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
