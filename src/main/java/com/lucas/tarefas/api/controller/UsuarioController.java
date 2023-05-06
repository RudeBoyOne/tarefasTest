package com.lucas.tarefas.api.controller;

import com.lucas.tarefas.api.assembler.UsuarioAssembler;
import com.lucas.tarefas.api.dto.input.UsuarioInput;
import com.lucas.tarefas.api.dto.output.UsuarioOutput;
import com.lucas.tarefas.domain.model.Usuario;
import com.lucas.tarefas.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioAssembler usuarioAssembler;

    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = UsuarioOutput.class)))
    @PostMapping
    public ResponseEntity<UsuarioOutput> criar(@Valid @RequestBody UsuarioInput usuarioInput) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);
        UsuarioOutput usuarioOutput = usuarioAssembler.toOutput(usuarioSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioOutput);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutput> atualizar(@PathVariable Long idUsuario,
                                                   @Valid @RequestBody UsuarioInput usuarioInput) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(idUsuario, usuario);
        UsuarioOutput usuarioOutput = usuarioAssembler.toOutput(usuarioAtualizado);

        return ResponseEntity.ok(usuarioOutput);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<UsuarioOutput>> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<UsuarioOutput> usuarioOutputs = usuarioAssembler.toCollectionOutput(usuarios);

        return ResponseEntity.ok(usuarioOutputs);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutput> buscarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);
        UsuarioOutput usuarioOutput = usuarioAssembler.toOutput(usuario);

        return ResponseEntity.ok(usuarioOutput);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> excluir(@PathVariable Long idUsuario) {
        usuarioService.excluirUsuario(idUsuario);

        return ResponseEntity.noContent().build();
    }
}
