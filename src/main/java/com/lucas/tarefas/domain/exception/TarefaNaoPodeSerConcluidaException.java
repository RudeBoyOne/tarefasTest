package com.lucas.tarefas.domain.exception;

import java.io.Serial;

public class TarefaNaoPodeSerConcluidaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TarefaNaoPodeSerConcluidaException(String message) {
        super(message);
    }
}
