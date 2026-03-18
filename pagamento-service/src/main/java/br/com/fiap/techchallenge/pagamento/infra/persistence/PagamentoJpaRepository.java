package br.com.fiap.techchallenge.pagamento.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, Long> {

    Optional<PagamentoEntity> findByPedidoId(Long pedidoId);

}