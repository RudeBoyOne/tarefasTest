package com.lucas.tarefas.api.assembler;

import com.lucas.tarefas.api.dto.input.UsuarioInput;
import com.lucas.tarefas.api.dto.output.UsuarioOutput;
import com.lucas.tarefas.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private final ModelMapper modelMapper;

    public UsuarioOutput toOutput(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioOutput.class);
    }

    public List<UsuarioOutput> toCollectionOutput(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toOutput).collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
