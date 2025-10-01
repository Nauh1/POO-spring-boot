package br.edu.ifpr.todo.api.controller;

import br.edu.ifpr.todo.api.dto.TarefaRequest;
import br.edu.ifpr.todo.api.dto.TarefaResponse;
import br.edu.ifpr.todo.domain.model.Tarefa;
import br.edu.ifpr.todo.domain.model.Status;
import br.edu.ifpr.todo.domain.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
// @CrossOrigin(origins = "*") // Libere se for consumir de um front rodando em
// outra origem
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }
    // Exemplo: GET
    // http://localhost:8080/tarefas/add?nome=Teste&descricao=Primeira%20tarefa&impor
    // tante=true&status=FAZENDO&dataEntrega=2025-09-20

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponse criar(@RequestBody TarefaRequest dto) {
        Tarefa salvo = service.criar(dto);
        return new TarefaResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getStatus(),
                salvo.getDataCriacao(),
                salvo.getDataEntrega(),
                salvo.getImportante());
    }

    @GetMapping
    public List<TarefaResponse> listar(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Boolean importante) {
        List<Tarefa> tarefas = service.listar(null, status, importante);
        return tarefas.stream()
                .map(t -> new TarefaResponse(
                        t.getId(),
                        t.getNome(),
                        t.getDescricao(),
                        t.getStatus(),
                        t.getDataCriacao(),
                        t.getDataEntrega(),
                        t.getImportante()))
                .toList();
    }

    @GetMapping("/{id}")
    public TarefaResponse burcarPorId(@PathVariable Long id) {
        Tarefa tarefa = service.buscarPorId(id);
        return new TarefaResponse(
                tarefa.getId(),
                tarefa.getNome(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getDataCriacao(),
                tarefa.getDataEntrega(),
                tarefa.getImportante());
    }

    @PatchMapping("/{id}")
    public TarefaResponse atualizarParcial(@PathVariable Long id,@RequestBody TarefaRequest dto) {
        Tarefa atualizada = service.atualizarParcial(id, dto);
        return new TarefaResponse(
                atualizada.getId(),
                atualizada.getNome(),
                atualizada.getDescricao(),
                atualizada.getStatus(),
                atualizada.getDataCriacao(),
                atualizada.getDataEntrega(),
                atualizada.getImportante());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

}
