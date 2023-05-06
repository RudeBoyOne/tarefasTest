package com.lucas.tarefas.api.assembler;

import com.lucas.tarefas.api.dto.input.TarefaInput;
import com.lucas.tarefas.api.dto.output.TarefaOutput;
import com.lucas.tarefas.domain.model.Tarefa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TarefaAssembler {

    private final ModelMapper modelMapper;

    public TarefaOutput toOutput(Tarefa tarefa) {
        return modelMapper.map(tarefa, TarefaOutput.class);
    }

    public List<TarefaOutput> toCollectionOutput(List<Tarefa> tarefas) {
        return tarefas.stream().map(this::toOutput).collect(Collectors.toList());
    }

    public Tarefa toEntity(TarefaInput tarefaInput) {
        return modelMapper.map(tarefaInput, Tarefa.class);
    }
}
