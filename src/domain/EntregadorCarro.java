package domain;

public class EntregadorCarro extends Entregador {
    private String placa;
    private double capacidadeKg;

    public EntregadorCarro(int id, String nome, String placa, double capacidadeKg) {
        super(id, nome);
        this.placa = placa;
        this.capacidadeKg = capacidadeKg;
    }

    @Override
    public double calcularTempo() {
        return 45.0;
    }

    @Override
    public void realizarEntrega() {
        System.out.println("Entrega realizada de carro! Placa: " + placa);
    }

    // sobrecarga
    public void realizarEntrega(String obs) {
        System.out.println("Entrega realizada de carro! Placa: " + placa + " | Obs: " + obs);
    }

    public String getPlaca() { return placa; }
    public double getCapacidadeKg() { return capacidadeKg; }

    @Override
    public String toString() {
        return super.toString() + " | Carro | Placa: " + placa;
    }
}