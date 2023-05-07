package com.lucas.tarefas.api.controller;

import com.lucas.tarefas.api.assembler.TarefaAssembler;
import com.lucas.tarefas.api.dto.input.TarefaInput;
import com.lucas.tarefas.api.dto.output.TarefaOutput;
import com.lucas.tarefas.domain.model.Tarefa;
import com.lucas.tarefas.domain.service.TarefaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    private final TarefaAssembler tarefaAssembler;

    @PostMapping
    public ResponseEntity<TarefaOutput> criar(@RequestBody @Valid TarefaInput tarefaInput) {
        Tarefa tarefaEntity = tarefaAssembler.toEntity(tarefaInput);

        Tarefa tarefaSalva = tarefaService.criarTarefa(tarefaEntity);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaOutput);
    }

    @PutMapping("/{idTarefa}")
    public ResponseEntity<TarefaOutput> atualizar(@PathVariable Long idTarefa,
                                                  @RequestBody @Valid TarefaInput tarefaInput) {

        Tarefa tarefaSalva = tarefaService.atualizarTarefa(idTarefa, tarefaInput);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaSalva);

        return ResponseEntity.ok(tarefaOutput);
    }

    @GetMapping
    public ResponseEntity<List<TarefaOutput>> listar() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();

        List<TarefaOutput> tarefaOutputs = tarefaAssembler.toCollectionOutput(tarefas);

        return ResponseEntity.ok(tarefaOutputs);
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<TarefaOutput> buscarTarefa(@PathVariable Long idTarefa) {
        Tarefa tarefa = tarefaService.buscarTarefa(idTarefa);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefa);

        return ResponseEntity.ok(tarefaOutput);
    }

    @DeleteMapping("/{idTarefa}")
    public ResponseEntity<Void> excluir(@PathVariable Long idTarefa) {
        tarefaService.excluirTarefa(idTarefa);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idTarefa}/concluir")
    public ResponseEntity<TarefaOutput> concluirTarefa(@PathVariable Long idTarefa) {
        Tarefa tarefaConcluida = tarefaService.concluirTarefa(idTarefa);

        TarefaOutput tarefaOutput = tarefaAssembler.toOutput(tarefaConcluida);

        return ResponseEntity.ok(tarefaOutput);
    }

}
