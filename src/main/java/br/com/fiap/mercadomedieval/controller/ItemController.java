package br.com.fiap.mercadomedieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import br.com.fiap.mercadomedieval.model.Item;
import br.com.fiap.mercadomedieval.model.enums.Raridade;
import br.com.fiap.mercadomedieval.model.enums.TipoItem;
import br.com.fiap.mercadomedieval.repository.DonoRepository;
import br.com.fiap.mercadomedieval.repository.ItemRepository;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DonoRepository donoRepository;  

    @GetMapping
    public List<Item> listar() {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item cadastrar(@RequestBody @Valid Item item) {
        // Para buscar e salvar a associação do dono pelo ID
        Personagem dono = donoRepository.findById(item.getDono().getId())
            .orElseThrow(() -> new RuntimeException("Dono não encontrado"));
        item.setDono(dono);
        return itemRepository.save(item);
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody @Valid Item atualizado) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Personagem dono = donoRepository.findById(atualizado.getDono().getId())
            .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        item.setNome(atualizado.getNome());
        item.setTipo(atualizado.getTipo());
        item.setRaridade(atualizado.getRaridade());
        item.setPreco(atualizado.getPreco());
        item.setDono(dono);  

        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    // Filtros
    @GetMapping("/buscar")
    public List<Item> buscarPorFiltros(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) TipoItem tipo,
        @RequestParam(required = false) Integer precoMin,
        @RequestParam(required = false) Integer precoMax,
        @RequestParam(required = false) Raridade raridade) {

        Specification<Item> spec = Specification.where(null);

        // Por nome parcial
        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        // Por tipo
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }

        // Por raridade
        if (raridade != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("raridade"), raridade));
        }

        // Por preço mínimo e máximo
        if (precoMin != null && precoMax != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("preco"), precoMin, precoMax));
        } else if (precoMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("preco"), precoMin));
        } else if (precoMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("preco"), precoMax));
        }

        return itemRepository.findAll(spec);
    }
}
