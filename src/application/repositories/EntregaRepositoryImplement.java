package application.repositories;

import domain.Entrega;
import domain.Entregador;
import infrastructure.persistence.EntregaRepository;

import java.util.ArrayList;
import java.util.List;

public class EntregaRepositoryImplement implements EntregaRepository {

    private List<Entrega> dados = new ArrayList<>();
    private List<Entregador> entregadores = new ArrayList<>();

    @Override
    public void salvar(Entrega entrega) {
        dados.add(entrega);
    }

    @Override
    public List<Entrega> buscarTodos() {
        return dados;
    }

    @Override
    public Entrega update(Long id, Entrega novaEntrega) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == id) {
                dados.set(i, novaEntrega);
                return novaEntrega;
            }
        }
        return null;
    }

    @Override
    public void salvarEntregador(Entregador entregador) {
        entregadores.add(entregador);
    }

    @Override
    public List<Entregador> buscarTodosEntregadores() {
        return entregadores;
    }
}