package controller;

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

import jakarta.validation.Valid;
import model.Item;
import model.enums.Raridade;
import model.enums.TipoItem;
import repository.ItemRepository;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Item cadastrar(@RequestBody @Valid Item item) {
        return repository.save(item);
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody @Valid Item atualizado) {
        Item item = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setNome(atualizado.getNome());
        item.setTipo(atualizado.getTipo());
        item.setRaridade(atualizado.getRaridade());
        item.setPreco(atualizado.getPreco());
        item.setDono(atualizado.getDono());

        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Filtros
    @GetMapping("/buscar")
    public List<Item> buscarPorFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) TipoItem tipo,
            @RequestParam(required = false) Integer precoMin,
            @RequestParam(required = false) Integer precoMax,
            @RequestParam(required = false) Raridade raridade) {

        if (nome != null) return repository.findByNomeContainingIgnoreCase(nome);
        if (tipo != null) return repository.findByTipo(tipo);
        if (raridade != null) return repository.findByRaridade(raridade);
        if (precoMin != null && precoMax != null) return repository.findByPrecoBetween(precoMin, precoMax);

        return repository.findAll();
    }
}
