package com.lucas.tarefas.api.security;

import com.lucas.tarefas.domain.exception.RecursoNaoEncontradoException;
import com.lucas.tarefas.domain.model.Usuario;
import com.lucas.tarefas.domain.repository.UsuarioRepository;
import com.lucas.tarefas.domain.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Component
public class FilterToken extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.replace("Bearer ", "");
            String subject = tokenService.getSubject(token);

            Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(subject);

            if (usuarioOptional.isEmpty()) {
                throw new RecursoNaoEncontradoException("Usuario não exite!");
            }
            Usuario usuario = usuarioOptional.get();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

    }
}
