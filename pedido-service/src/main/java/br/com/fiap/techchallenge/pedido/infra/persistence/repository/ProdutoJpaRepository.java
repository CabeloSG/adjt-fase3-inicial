package br.com.fiap.techchallenge.pedido.infra.persistence.repository;

import br.com.fiap.techchallenge.pedido.infra.persistence.entity.ProdutoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoJpaEntity, Long> {

    List<ProdutoJpaEntity> findByRestauranteId(Long restauranteId);
}