package com.lucas.tarefas.domain.exception;

import java.io.Serial;

public class RecursoJaExistenteException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecursoJaExistenteException(String message) {
        super(message);
    }
}
