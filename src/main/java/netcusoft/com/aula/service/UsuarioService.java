package netcusoft.com.aula.service;

import netcusoft.com.aula.exception.RecursoNaoEncontrado;
import netcusoft.com.aula.model.entity.Usuario;
import netcusoft.com.aula.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public Usuario createUser(Usuario usuario, String password) {
        String senhaEncriptada = passwordEncoder.encode(password);
        // podia ser mas o aconselhavel para senha é criar um Bean proprio
        // Usuario usuario1= new  Usuario(usuario.getUsername(), senhaEncriptada);
        usuario.setPassword(senhaEncriptada);
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuario nao encontrado"));
    }
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new RecursoNaoEncontrado("Usuario nao encontrado!"));
    }

}
