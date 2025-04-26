package br.com.fiap.mercadomedieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.mercadomedieval.model.Personagem;
import br.com.fiap.mercadomedieval.model.enums.Classe;

public interface PersonagemRepository extends JpaRepository<Personagem, Long>, JpaSpecificationExecutor<Personagem> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByNomeContainingIgnoreCaseAndClasse(String nome, Classe classe);
    List<Personagem> findByClasse(Classe classe);
}

