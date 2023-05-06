package com.lucas.tarefas.domain.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lucas.tarefas.domain.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String genarateToken(Usuario usuario) {
        return JWT.create()
                    .withIssuer("tarefas")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withClaim("nome", usuario.getNome())
                    .withExpiresAt(
                            Date.from(  LocalDateTime.now().
                                        plusMinutes(30).
                                        toInstant(ZoneOffset.of("-03:00"))))
                    .sign(Algorithm.HMAC256("assinaturaDoTokenQueSoOBackendTemEUsaParaDescripitografarOToken"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("assinaturaDoTokenQueSoOBackendTemEUsaParaDescripitografarOToken"))
                    .withIssuer("tarefas")
                    .build()
                        .verify(token)
                        .getSubject();
    }
}
