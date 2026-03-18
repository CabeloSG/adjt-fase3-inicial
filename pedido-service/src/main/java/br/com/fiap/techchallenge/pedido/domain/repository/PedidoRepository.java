package br.com.fiap.techchallenge.pedido.domain.repository;

import br.com.fiap.techchallenge.pedido.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Pedido salvar(Pedido pedido);
    void atualizarStatus(Long pedidoId, String status);
    List<Pedido> findByUserId(Long clienteId);
    Optional<Pedido> findById(Long id);
    Optional<Pedido> buscarPorId(Long id);

}
