package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import model.Item;
import model.enums.Raridade;
import model.enums.TipoItem;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(TipoItem tipo);
    List<Item> findByRaridade(Raridade raridade);
    List<Item> findByPrecoBetween(int min, int max);
}

