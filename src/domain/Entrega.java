package domain;

public class Entrega {
    private int id;
    private String destino;
    private StatusEntrega status;
    private Entregador entregador;
    private Remetente remetente;
    private Destinatario destinatario;

    public Entrega(int id, String destino, Remetente remetente, Destinatario destinatario) {
        this.id = id;
        this.destino = destino;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.status = StatusEntrega.PENDENTE;
    }

    public void atualizarStatus(StatusEntrega status) {
        this.status = status;
    }

    public void atualizarStatus(StatusEntrega status, String obs) {
        this.status = status;
        System.out.println("Obs: " + obs);
    }

    public void atribuirEntregador(Entregador entregador) {
        this.entregador = entregador;
        entregador.setDisponivel(false);
    }

    public int getId() { return id; }
    public String getDestino() { return destino; }
    public StatusEntrega getStatus() { return status; }
    public Entregador getEntregador() { return entregador; }
    public Remetente getRemetente() { return remetente; }
    public Destinatario getDestinatario() { return destinatario; }

    @Override
    public String toString() {
        return "[" + id + "] Destino: " + destino + " | Status: " + status +
                " | Entregador: " + (entregador != null ? entregador.getNome() : "Não atribuído");
    }
}