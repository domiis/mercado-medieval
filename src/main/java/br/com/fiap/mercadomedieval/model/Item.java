package br.com.fiap.mercadomedieval.model;

import br.com.fiap.mercadomedieval.model.enums.Raridade;
import br.com.fiap.mercadomedieval.model.enums.TipoItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoItem tipo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Raridade raridade;

    @Positive
    private int preco;

    @ManyToOne
    private Personagem dono;

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public TipoItem getTipo() { return tipo; }

    public void setTipo(TipoItem tipo) { this.tipo = tipo; }

    public Raridade getRaridade() { return raridade; }

    public void setRaridade(Raridade raridade) { this.raridade = raridade; }

    public int getPreco() { return preco; }

    public void setPreco(int preco) { this.preco = preco; }

    public Personagem getDono() { return dono; }

    public void setDono(Personagem dono) { this.dono = dono; }
}
