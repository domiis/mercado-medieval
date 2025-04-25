package model;

public class Item {
    private String nome;
    private String tipo;
    private String raridade;
    private double valor;
    private String dono;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public Item(String nome, String tipo, String raridade, double valor, String dono) {
        this.nome = nome;
        this.tipo = tipo;
        this.raridade = raridade;
        this.valor = valor;
        this.dono = dono;
    }
}
