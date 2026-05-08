package infrastructure.persistence;

import domain.Entrega;
import domain.Entregador;

import java.util.List;

public interface EntregaRepository {
    void salvar(Entrega entrega);
    List<Entrega> buscarTodos();
    Entrega update(Long id, Entrega novaEntrega);

    void salvarEntregador(Entregador entregador);
    List<Entregador> buscarTodosEntregadores();
}