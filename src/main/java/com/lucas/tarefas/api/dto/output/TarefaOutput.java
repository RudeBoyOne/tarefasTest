package com.lucas.tarefas.api.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucas.tarefas.domain.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarefaOutput {

    private Long id;
    @Schema(type = "string", example = "Um título bem legal")
    private String titulo;

    @Schema(type = "string", example = "Uma descrição bem legal")
    private String descricao;

    @Schema(type = "string", example = "14/01/1994 - 14:30")
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataCriacao;

    @Schema(type = "string", example = "14/01/1994 - 14:30")
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataConclusao;

    private Status status;
}
