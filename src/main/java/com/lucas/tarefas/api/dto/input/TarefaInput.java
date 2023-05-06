package com.lucas.tarefas.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaInput {

    @Schema(type = "string", example = "Um título bem legal para minha próxima tarefa")
    @NotBlank
    private String titulo;

    @Schema(type = "string", example = "Uma descrção bem legal para minha próxima tarefa")
    @NotBlank
    private String descricao;
}
