package com.lucas.tarefas.domain.service;

import com.lucas.tarefas.api.dto.input.TarefaInput;
import com.lucas.tarefas.domain.exception.RecursoNaoEncontradoException;
import com.lucas.tarefas.domain.exception.TarefaNaoPodeSerConcluidaException;
import com.lucas.tarefas.domain.model.Status;
import com.lucas.tarefas.domain.model.Tarefa;
import com.lucas.tarefas.domain.repository.TarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setStatus(Status.ABERTA);
        return tarefaRepository.save(tarefa);
    }

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public Tarefa atualizarTarefa(Long id, TarefaInput tarefaInput) {
        Tarefa tarefaEntity = buscarTarefa(id);

        tarefaEntity.setTitulo(tarefaInput.getTitulo());
        tarefaEntity.setDescricao(tarefaInput.getDescricao());

        return tarefaRepository.save(tarefaEntity);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefa(Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        if (tarefaOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Tarefa de id: " + id + ", não existe!");
        }

        return tarefaOptional.get();
    }

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public void excluirTarefa(Long id) {
        Tarefa tarefa = buscarTarefa(id);
        tarefaRepository.delete(tarefa);
    }

    @Transactional(
            rollbackFor = IllegalArgumentException.class,
            rollbackForClassName = "IllegalArgumentException")
    public Tarefa concluirTarefa(Long id) {
        Tarefa tarefa = buscarTarefa(id);

        if (!tarefa.getStatus().equals(Status.ABERTA)) {
            throw new TarefaNaoPodeSerConcluidaException("Tarefa de id: " + id + ", já está concluída!");
        }

        tarefa.setStatus(Status.CONCLUIDA);
        tarefa.setDataConclusao(LocalDateTime.now());

        Tarefa tarefaConcluida = tarefaRepository.save(tarefa);

        return tarefaConcluida;
    }
}
