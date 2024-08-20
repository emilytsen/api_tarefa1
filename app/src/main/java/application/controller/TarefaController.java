package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    //LISTAR TODAS AS TAREFAS 
    @GetMapping
    public Iterable<Tarefa> list() {
        return tarefaRepo.findAll();
    }

    //ADICIONA UMA NOVA TAREFA
    @PostMapping
    public Tarefa insert(@RequestBody Tarefa tarefa) {
        return tarefaRepo.save(tarefa);
    }

    //LISTAR UMA TAREFA ESPECÍFICA
    @GetMapping("/{id}")
    public Tarefa details(@PathVariable long id) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrado"
            );
        }
        return resultado.get();
    }

    //ATUALIZA UMA TAREFA
    @PutMapping("/{id}")
    public Tarefa put(
        @PathVariable long id,
        @RequestBody Tarefa novosDados) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrado"
            );
        }

        if(novosDados.getDescricao().isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Descricao de Tarefa Inválido"
            );
        }

        resultado.get().setDescricao(novosDados.getDescricao());

        return tarefaRepo.save(resultado.get());
    }

    //DELETA UMA TAREFA
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!tarefaRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrado"
            );
        }

        tarefaRepo.deleteById(id);
    }
}
