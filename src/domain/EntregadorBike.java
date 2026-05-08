package domain;

public class EntregadorBike extends Entregador {
    private double raioMaxKm;
    private boolean ecologico;

    public EntregadorBike(int id, String nome, double raioMaxKm, boolean ecologico) {
        super(id, nome);
        this.raioMaxKm = raioMaxKm;
        this.ecologico = ecologico;
    }

    @Override
    public double calcularTempo() {
        return 60.0;
    }

    @Override
    public void realizarEntrega() {
        System.out.println("Entrega realizada de bicicleta! Raio máx: " + raioMaxKm + "km");
    }

    // sobrecarga
    public void realizarEntrega(String obs) {
        System.out.println("Entrega realizada de bicicleta! | Obs: " + obs);
    }

    public double getRaioMaxKm() { return raioMaxKm; }
    public boolean isEcologico() { return ecologico; }

    @Override
    public String toString() {
        return super.toString() + " | Bike | Raio: " + raioMaxKm + "km";
    }
}