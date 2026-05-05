package infrastructure.persistence;

import domain.Entrega;

import java.util.ArrayList;
import java.util.List;

public interface EntregaRepository {

    void salvar(Entrega entrega);

    List<Entrega> buscarTodos();

    Entrega update(Long id, Entrega novaEntrega);
}
