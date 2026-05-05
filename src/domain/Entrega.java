package domain;

public class Entrega {
    private Long id;
    private Remetente remetente;
    private String status;
    private Entregador entregador;
    private Destinatario destinatario;

    public Entrega(Long id, Remetente remetente, String status, Entregador entregador, Destinatario destinatario) {
        this.id = id;
        this.remetente = remetente;
        this.status = status;
        this.entregador = entregador;
        this.destinatario = destinatario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Remetente getRemetente() {
        return remetente;
    }

    public void setRemetente(Remetente remetente) {
        this.remetente = remetente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }
}
