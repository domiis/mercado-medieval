package br.com.fiap.mercadomedieval.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.mercadomedieval.model.Item;
import br.com.fiap.mercadomedieval.model.Personagem;
import br.com.fiap.mercadomedieval.model.enums.Classe;
import br.com.fiap.mercadomedieval.model.enums.Raridade;
import br.com.fiap.mercadomedieval.model.enums.TipoItem;
import br.com.fiap.mercadomedieval.repository.ItemRepository;
import br.com.fiap.mercadomedieval.repository.PersonagemRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        // Personagens de teste
        Personagem p1 = new Personagem();
        p1.setNome("Cardan");
        p1.setClasse(Classe.MAGO);
        p1.setNivel(15);
        p1.setMoedas(450);

        Personagem p2 = new Personagem();
        p2.setNome("Madoc");
        p2.setClasse(Classe.GUERREIRO);
        p2.setNivel(18);
        p2.setMoedas(300);

        Personagem p3 = new Personagem();
        p3.setNome("Jude");
        p3.setClasse(Classe.ARQUEIRO);
        p3.setNivel(15);
        p3.setMoedas(100);

        personagemRepository.saveAll(List.of(p1, p2, p3));


        // Itens de teste
        Item espadaEncantada = new Item();
        espadaEncantada.setNome("Espada Encantada");
        espadaEncantada.setTipo(TipoItem.ARMA);
        espadaEncantada.setRaridade(Raridade.LENDARIO);
        espadaEncantada.setPreco(500);
        espadaEncantada.setDono(p2); 

        Item pocaoCura = new Item();
        pocaoCura.setNome("Poção de Cura");
        pocaoCura.setTipo(TipoItem.POCAO);
        pocaoCura.setRaridade(Raridade.COMUM);
        pocaoCura.setPreco(50);
        pocaoCura.setDono(p1); 

        Item mantoInvisibilidade = new Item();
        mantoInvisibilidade.setNome("Manto de Invisibilidade");
        mantoInvisibilidade.setTipo(TipoItem.ACESSORIO);
        mantoInvisibilidade.setRaridade(Raridade.RARO);
        mantoInvisibilidade.setPreco(200);
        mantoInvisibilidade.setDono(p1); 

        itemRepository.saveAll(List.of(espadaEncantada, pocaoCura, mantoInvisibilidade));

    }
}
