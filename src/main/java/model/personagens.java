package model;

public class personagens {
    private String nome;
    private String classe;
    private int nivel;
    private int moedas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public personagens(String nome, String classe, int nivel, int moedas) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.moedas = moedas;
    }
}
