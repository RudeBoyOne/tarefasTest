package com.lucas.tarefas.service;

import com.lucas.tarefas.domain.model.Usuario;
import com.lucas.tarefas.domain.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = OrderAnnotation.class)
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @BeforeAll
    void instanciaUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("Larissa");
        usuario.setEmail("larissa@gmail.com");
        usuario.setSenha("mininaDoida");

        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);
        assertEquals("Larissa", usuarioSalvo.getNome());
        assertEquals("larissa@gmail.com", usuarioSalvo.getEmail());
        assertEquals("mininaDoida", usuarioSalvo.getSenha());
    }

    @Test
    @Order(1)
    void criarUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucas");
        usuario.setEmail("lucas@gmail.com");
        usuario.setSenha("mlQdoido");

        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);

        assertEquals("Lucas", usuarioSalvo.getNome());
        assertEquals("lucas@gmail.com", usuarioSalvo.getEmail());
        assertEquals("mlQdoido", usuarioSalvo.getSenha());
    }

    @Test
    @Order(2)
    void listarUsuariosTest() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertEquals(2, usuarios.size());
    }

    @Test
    @Order(3)
    void buscarUsuarioTest() {
        Usuario usuario = usuarioService.buscarUsuario(2L);

        assertEquals("Lucas", usuario.getNome());
        assertEquals("lucas@gmail.com", usuario.getEmail());
        assertEquals("mlQdoido", usuario.getSenha());
    }

    @Test
    @Order(4)
    void atualizarUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("Camila");
        usuario.setEmail("camila@gmail.com");
        usuario.setSenha("hoje");

        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(1L, usuario);

        assertEquals("Camila", usuario.getNome());
        assertEquals("camila@gmail.com", usuario.getEmail());
        assertEquals("hoje", usuario.getSenha());
    }

    @Test
    @Order(5)
    void excluirUsuarioTest() {
        usuarioService.excluirUsuario(2L);
    }

}
