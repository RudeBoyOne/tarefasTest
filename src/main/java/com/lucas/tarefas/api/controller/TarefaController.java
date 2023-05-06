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
}
