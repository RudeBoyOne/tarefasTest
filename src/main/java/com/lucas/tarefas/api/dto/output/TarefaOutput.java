package com.lucas.tarefas.api.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucas.tarefas.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarefaOutput {

    private Long id;
    private String titulo;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataConclusao;

    private Status status;
}
