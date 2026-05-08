package application.usecases;

import domain.Entrega;
import infrastructure.persistence.EntregaRepository;

public class CadastrarEntrega {

    private final EntregaRepository repository;

    public CadastrarEntrega(EntregaRepository repository){
        this.repository = repository;
    }

    public void executar(Entrega entrega){
        if(entrega.getRemetente() == null || entrega.getDestinatario() == null){
            return;
        }
        repository.salvar(entrega);
    }
}
