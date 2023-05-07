package com.lucas.tarefas.api.controller;

import com.lucas.tarefas.api.assembler.TarefaAssembler;
import com.lucas.tarefas.api.dto.input.TarefaInput;
import com.lucas.tarefas.api.dto.output.TarefaOutput;
import com.lucas.tarefas.api.dto.output.UsuarioOutput;
import com.lucas.tarefas.api.exception.handler.Problema;
import com.lucas.tarefas.domain.model.Tarefa;
import com.lucas.tarefas.domain.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/tarefas")
@Tag(name = "Tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    private final TarefaAssembler tarefaAssembler;

    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = TarefaOutput.class)))
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<TarefaOutput> criar(@RequestBody @Valid TarefaInput tarefaInput) {
        Tarefa tarefaEntity = tarefaAssembler.toEntity(tarefaInput);

        Tarefa tarefaSalva = tarefaService.criarTarefa(tarefaEntity);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaOutput);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{idTarefa}")
    public ResponseEntity<TarefaOutput> atualizar(@PathVariable Long idTarefa,
                                                  @RequestBody @Valid TarefaInput tarefaInput) {

        Tarefa tarefaSalva = tarefaService.atualizarTarefa(idTarefa, tarefaInput);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaSalva);

        return ResponseEntity.ok(tarefaOutput);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<TarefaOutput>> listar() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();

        List<TarefaOutput> tarefaOutputs = tarefaAssembler.toCollectionOutput(tarefas);

        return ResponseEntity.ok(tarefaOutputs);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{idTarefa}")
    public ResponseEntity<TarefaOutput> buscarTarefa(@PathVariable Long idTarefa) {
        Tarefa tarefa = tarefaService.buscarTarefa(idTarefa);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefa);

        return ResponseEntity.ok(tarefaOutput);
    }

    @ApiResponse(responseCode = "204", content = @Content(schema = @Schema))
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{idTarefa}")
    public ResponseEntity<Void> excluir(@PathVariable Long idTarefa) {
        tarefaService.excluirTarefa(idTarefa);

        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{idTarefa}/concluir")
    public ResponseEntity<TarefaOutput> concluirTarefa(@PathVariable Long idTarefa) {
        Tarefa tarefaConcluida = tarefaService.concluirTarefa(idTarefa);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaConcluida);

        return ResponseEntity.ok(tarefaOutput);
    }

}
