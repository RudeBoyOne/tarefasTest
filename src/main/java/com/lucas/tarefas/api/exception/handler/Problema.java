package com.lucas.tarefas.api.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Schema(name = "Problema")
public class Problema {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "2022-07-15T11:21:50.902245498Z")
    private OffsetDateTime dataHora;

    @Schema(example = "Dados inválidos")
    private String titulo;

    @Schema(description = "Lista de objetos ou campos que geraram o erro")
    private List<Campo> campos;

    @Getter
    @AllArgsConstructor
    @Schema(name = "CampoProblema")
    public static class Campo {

        @Schema(example = "email")
        private String nome;

        @Schema(example = "O email é inválido")
        private String mensagem;
    }
}
