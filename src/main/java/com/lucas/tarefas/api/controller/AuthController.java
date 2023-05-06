package com.lucas.tarefas.api.controller;

import com.lucas.tarefas.api.dto.input.Login;
import com.lucas.tarefas.api.dto.output.LoginOutput;
import com.lucas.tarefas.domain.model.Usuario;
import com.lucas.tarefas.domain.service.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
@Tag(name = "Auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    /*@Operation( description = "Logar usuários válidos na aplicação",
        responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema =
            @Schema(implementation = LoginOutput.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema =
            @Schema(implementation = Problema.class)))
    })*/
    @PostMapping
    public ResponseEntity<LoginOutput> login(@RequestBody @Valid Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = tokenService.genarateToken(usuario);

        LoginOutput loginOutput = new LoginOutput(
                usuario.getNome(),
                usuario.getEmail(),
                token
        );

        return ResponseEntity.ok(loginOutput);
    }
}
