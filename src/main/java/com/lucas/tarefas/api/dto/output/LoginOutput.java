package com.lucas.tarefas.api.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LoginOutput {

    private String nome;
    private String email;
    private String token;
}
