package com.lucas.tarefas.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @Schema(type = "string", example = "Lucas")
    @NotBlank
    @Size(min = 1, max = 60)
    private String nome;

    @Schema(type = "string", example = "lucas@gmail.com",  requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    @Size(min = 1, max = 100)
    private String email;

    @NotBlank
    @Schema(type = "string", example = "s3nh4Forte*10?")
    private String senha;

}
