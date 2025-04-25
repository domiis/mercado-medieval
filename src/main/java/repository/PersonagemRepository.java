package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import model.Personagem;
import model.enums.Classe;

public interface PersonagemRepository extends JpaRepository<Personagem, Long>, JpaSpecificationExecutor<Personagem> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(Classe classe);
}

