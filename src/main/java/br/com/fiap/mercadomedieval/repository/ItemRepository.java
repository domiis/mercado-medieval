package br.com.fiap.mercadomedieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.mercadomedieval.model.Item;
import br.com.fiap.mercadomedieval.model.enums.Raridade;
import br.com.fiap.mercadomedieval.model.enums.TipoItem;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(TipoItem tipo);
    List<Item> findByRaridade(Raridade raridade);
    List<Item> findByPrecoBetween(int min, int max);
}

