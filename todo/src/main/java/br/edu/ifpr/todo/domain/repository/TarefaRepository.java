package br.edu.ifpr.todo.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.todo.domain.model.Status;
import br.edu.ifpr.todo.domain.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(Status status);

    List<Tarefa> findByImportanteTrue();

    List<Tarefa> findByStatusAndImportanteTrue(Status status);

    List<Tarefa> findByDataEntregaBefore(LocalDate limite);

    List<Tarefa> findByNomeContainingIgnoreCase(String q);
}