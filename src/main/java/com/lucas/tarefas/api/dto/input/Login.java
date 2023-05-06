package com.lucas.tarefas.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    @Schema(type = "string", example = "lucas@gmail.com",  requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
    private String email;

    @Schema(type = "string", example = "s3nh4Forte*10?")
    @NotBlank
    private String senha;
}
