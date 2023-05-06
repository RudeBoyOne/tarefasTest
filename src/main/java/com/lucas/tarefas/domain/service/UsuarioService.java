package com.lucas.tarefas.domain.service;

import com.lucas.tarefas.common.PasswordEnconder;
import com.lucas.tarefas.domain.exception.RecursoJaExistenteException;
import com.lucas.tarefas.domain.exception.RecursoNaoEncontradoException;
import com.lucas.tarefas.domain.model.Role;
import com.lucas.tarefas.domain.model.Usuario;
import com.lucas.tarefas.domain.repository.RoleRepository;
import com.lucas.tarefas.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEnconder passwordEnconder;

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public Usuario criarUsuario(Usuario usuario) {
        Boolean usuarioJaExiste = usuarioRepository.findByEmail(usuario.getEmail())
                .stream().anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));

        if (usuarioJaExiste) {
            throw new RecursoJaExistenteException("Usuário com e-mail: " + usuario.getEmail() + ", já existe!");
        }

        Role role = roleRepository.findByNome("USER");
        BCryptPasswordEncoder encoder = passwordEnconder.bCryptPasswordEncoder();
        String senhaEncode = encoder.encode(usuario.getSenha());

        usuario.setRoles(List.of(role));
        usuario.setSenha(senhaEncode);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioSalvo;
    }

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public Usuario atualizarUsuario(Long id, Usuario usuario) {

        if (!existeUsuario(id)) {
            throw new RecursoNaoEncontradoException("Usuário de id: " + id + ", não existe!");
        }

        usuario.setId(id);

        Usuario usuarioSalvo = criarUsuario(usuario);

        return usuarioSalvo;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new
                RecursoNaoEncontradoException("Usuário de id: " + id + ", não existe!"));
    }

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public void excluirUsuario(Long id) {
        Usuario usuario = buscarUsuario(id);
        usuarioRepository.delete(usuario);
    }

    public Boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }
}



