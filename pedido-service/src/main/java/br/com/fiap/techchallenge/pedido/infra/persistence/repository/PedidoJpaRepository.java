package br.com.fiap.techchallenge.pedido.infra.persistence.repository;

import br.com.fiap.techchallenge.pedido.infra.persistence.entity.PedidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PedidoJpaRepository extends JpaRepository<PedidoJpaEntity, Long> {

    List<PedidoJpaEntity> findByUserId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE PedidoJpaEntity p SET p.status = :status WHERE p.id = :pedidoId")
    void atualizarStatus(@Param("pedidoId") Long pedidoId,
                         @Param("status") String status);

}