package br.com.fiap.mercadomedieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mercadomedieval.model.Personagem;
import br.com.fiap.mercadomedieval.model.enums.Classe;
import br.com.fiap.mercadomedieval.repository.PersonagemRepository;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    
    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Personagem cadastrar(@RequestBody @Valid Personagem personagem) {
        return repository.save(personagem);
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody @Valid Personagem atualizado) {
        Personagem personagem = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setNome(atualizado.getNome());
        personagem.setClasse(atualizado.getClasse());
        personagem.setNivel(atualizado.getNivel());
        personagem.setMoedas(atualizado.getMoedas());

        return repository.save(personagem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Filtros
    @GetMapping("/buscar")
    public List<Personagem> buscarPorNomeOuClasse(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) Classe classe) {

    if (nome == null && classe == null) {
        return repository.findAll();
    }

    // Por nome e classe
    if (nome != null && classe != null) {
        return repository.findByNomeContainingIgnoreCaseAndClasse(nome, classe);
    }

    // Por nome
    if (nome != null) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    // Por classe
    return repository.findByClasse(classe);
}

}
