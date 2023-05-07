package com.lucas.tarefas.service;

import com.lucas.tarefas.api.dto.input.TarefaInput;
import com.lucas.tarefas.domain.model.Status;
import com.lucas.tarefas.domain.model.Tarefa;
import com.lucas.tarefas.domain.service.TarefaService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(value = OrderAnnotation.class)
public class TarefaServiceTest {

    @Autowired
    TarefaService tarefaService;

    @Test
    @Order(1)
    void criarTarefaTest() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Finalizar teste prático");
        tarefa.setDescricao("Entregar todos requesitos obrigatórios e se possivel os opcionais");
        tarefa.setStatus(Status.ABERTA);
        tarefa.setDataCriacao(LocalDateTime.now());

        Tarefa tarefaSalva = tarefaService.criarTarefa(tarefa);

        assertEquals("Finalizar teste prático", tarefaSalva.getTitulo());
        assertEquals("Entregar todos requesitos obrigatórios e se possivel os opcionais",
                tarefaSalva.getDescricao());
        assertEquals(Status.ABERTA, tarefaSalva.getStatus());
        assertNull(tarefaSalva.getDataConclusao());
    }

    @Test
    @Order(2)
    void listarTarefasTest() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();

        assertEquals(1, tarefas.size());
    }

    @Test
    @Order(3)
    void buscarTarefaTest() {
        Tarefa tarefa = tarefaService.buscarTarefa(1L);

        assertEquals("Finalizar teste prático", tarefa.getTitulo());
        assertEquals("Entregar todos requesitos obrigatórios e se possivel os opcionais",
                tarefa.getDescricao());
        assertEquals(Status.ABERTA, tarefa.getStatus());
        assertNull(tarefa.getDataConclusao());
    }

    @Test
    @Order(4)
    void atualizaTarefa() {
        TarefaInput tarefa = new TarefaInput();
        tarefa.setTitulo("Entrego meu melhor");
        tarefa.setDescricao("O resultado e consequência de dedicação, o que tiver que ser meu será, está escrito " +
                "nas estrelas, vai reclamar com Deus!");

        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(1L, tarefa);

        assertEquals("Entrego meu melhor", tarefaAtualizada.getTitulo());
        assertEquals("O resultado e consequência de dedicação, o que tiver que ser meu será, está escrito " +
                        "nas estrelas, vai reclamar com Deus!",
                tarefaAtualizada.getDescricao());
        assertEquals(Status.ABERTA, tarefaAtualizada.getStatus());
        assertNull(tarefaAtualizada.getDataConclusao());
    }

    @Test
    @Order(5)
    void concluiTarefaTest() {
        Tarefa tarefaConcluida = tarefaService.concluirTarefa(1L);
        assertEquals(Status.CONCLUIDA, tarefaConcluida.getStatus());
        assertNotNull(tarefaConcluida.getDataConclusao());
    }

    @Test
    @Order(6)
    void excluirTarefa() {
        tarefaService.excluirTarefa(1L);
    }
}
