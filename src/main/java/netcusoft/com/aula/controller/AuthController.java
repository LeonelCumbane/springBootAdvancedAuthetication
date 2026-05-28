package netcusoft.com.aula.controller;

import jakarta.validation.Valid;
import netcusoft.com.aula.exception.RecursoNaoEncontrado;
import netcusoft.com.aula.model.entity.Usuario;
import netcusoft.com.aula.repository.UsuarioRepository;
import netcusoft.com.aula.security.JwtUtil;
import netcusoft.com.aula.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@Valid @RequestBody Usuario usuario,
                                                                @RequestParam String password) {
        Usuario usuarioSalvo = usuarioService.createUser(usuario, password);
        String token = jwtUtil.generateToken(usuarioSalvo.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("id", usuarioSalvo.getId());
        map.put("usuario", usuarioSalvo.getUsername());
        map.put("message", "Usuario registrado correctamente");
        map.put("status", HttpStatus.CREATED.value());
        return ResponseEntity.ok(map);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioEncontrado = usuarioService.findByUsername(usuario.getUsername());

            if (!passwordEncoder.matches(usuario.getPassword(), usuarioEncontrado.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Credenciais inválidas"));
            }
            String token = jwtUtil.generateToken(usuarioEncontrado.getUsername());
            return ResponseEntity.
                    ok(Map.of(
                            "token", token,
                            "username", usuarioEncontrado.getUsername(),
                            "message", "Login realizado com sucesso!"));


        } catch (RecursoNaoEncontrado ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Usuario nao encontrado"));
        }

    }
}
