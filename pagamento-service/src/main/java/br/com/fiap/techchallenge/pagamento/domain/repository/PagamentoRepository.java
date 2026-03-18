package br.com.fiap.techchallenge.pagamento.domain.repository;

import br.com.fiap.techchallenge.pagamento.domain.model.Pagamento;

import java.util.Optional;

public interface PagamentoRepository  {

    Pagamento salvar(Pagamento pagamento);

    Optional<Pagamento> findByPedidoId(Long pedidoId);

    Optional<Pagamento> buscarPorPedidoId(Long pedidoId);
}