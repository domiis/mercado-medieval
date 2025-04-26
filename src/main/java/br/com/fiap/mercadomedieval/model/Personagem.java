package br.com.fiap.mercadomedieval.model;

import br.com.fiap.mercadomedieval.model.enums.Classe;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Classe classe;

    @Min(1)
    @Max(99)
    private int nivel;

    @PositiveOrZero
    private int moedas;

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Classe getClasse() { return classe; }

    public void setClasse(Classe classe) { this.classe = classe; }

    public int getNivel() { return nivel; }

    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getMoedas() { return moedas; }

    public void setMoedas(int moedas) { this.moedas = moedas; }
}
