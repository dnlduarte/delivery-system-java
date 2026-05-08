package domain;

public abstract class Entregador implements Entregavel {
    private int id;
    private String nome;
    private boolean disponivel;

    public Entregador(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.disponivel = true;
    }

    public abstract double calcularTempo();

    public int getId() { return id; }
    public String getNome() { return nome; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - " + (disponivel ? "Disponível" : "Ocupado");
    }
}