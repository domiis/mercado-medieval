package br.com.fiap.mercadomedieval.repository;

import br.com.fiap.mercadomedieval.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRepository extends JpaRepository<Personagem, Long> {
    Personagem findByNome(String nome);
}
