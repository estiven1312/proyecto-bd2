package com.zp.zorritoplus.service.impl;

import com.zp.zorritoplus.model.domain.Rol;
import com.zp.zorritoplus.model.domain.Usuario;
import com.zp.zorritoplus.model.dto.AuthRequest;
import com.zp.zorritoplus.model.dto.UsuarioDTO;
import com.zp.zorritoplus.model.response.AuthResponse;
import com.zp.zorritoplus.model.response.ExitoResponse;
import com.zp.zorritoplus.repository.RolRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Override
    @Transactional
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) {
        try {
            Usuario usuario = usuarioRepository.findByCorreoAndPassword(authRequest.getCorreo(), authRequest.getContrasenia());
            if(usuario == null){
                throw new Exception("Error al autenticar las credenciales");
            }
            String tokenUsuario = this.getJWTToken(usuario);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            AuthResponse response = new AuthResponse();
            response.setCodigo("200");
            response.setToken(tokenUsuario);
            response.setUsuarioDTO(usuarioDTO);
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
    @Transactional
    public ResponseEntity<ExitoResponse> editDatosByUser(UsuarioDTO usuarioDTO) {
        try{
            Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setCelular(usuarioDTO.getCelular());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setDni(usuarioDTO.getDni());
            usuario.setContrasenia(usuarioDTO.getContrasenia());
            usuarioRepository.save(usuario);
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("200");
            exitoResponse.setMensaje("Se guardaron los cambios correctamente");
            return new ResponseEntity<>(exitoResponse, HttpStatus.OK);

        }catch (Exception ex){
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("500");
            exitoResponse.setMensaje("No se pudo realizar la operacion, lo sentimos");
            return new ResponseEntity<>(exitoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> registrarUsuario(UsuarioDTO usuarioDTO) {
        try{
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setCelular(usuarioDTO.getCelular());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setDni(usuarioDTO.getDni());
            usuario.setContrasenia(usuarioDTO.getContrasenia());
            usuario.setActivo(true);
            Rol rol = rolRepository.findByAbreviatura("CLI");
            usuario.setRol(rol);
            usuarioRepository.save(usuario);
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("200");
            exitoResponse.setMensaje("Se registro correctamente");
            return new ResponseEntity<>(exitoResponse, HttpStatus.OK);

        }catch (Exception ex){
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("500");
            exitoResponse.setMensaje("No se pudo registrar lo sentimos");
            return new ResponseEntity<>(exitoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ExitoResponse> eliminarUsuario(Long idUsuario) {
        try{
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("200");
            exitoResponse.setMensaje("Se elimino correctamente");
            return new ResponseEntity<>(exitoResponse, HttpStatus.OK);
        }catch (Exception ex){
            ExitoResponse exitoResponse = new ExitoResponse();
            exitoResponse.setCodigo("500");
            exitoResponse.setMensaje("No se pudo registrar lo sentimos");
            return new ResponseEntity<>(exitoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> infoUsuario(Long idUsuario) {
        try {
            Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.OK);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioDTO>> listaUsuario() {
        try{
            List<UsuarioDTO> usuarios = usuarioRepository.findUsuariosActivos().stream().map(UsuarioDTO::new).toList();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public Usuario findUsuario(String correo) {
        return this.usuarioRepository.findUsuarioByCorreo(correo);
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
