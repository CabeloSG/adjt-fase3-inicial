package br.com.fiap.techchallenge.pedido.infra.persistence.repository;


import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import br.com.fiap.techchallenge.pedido.infra.persistence.entity.PedidoJpaEntity;
import br.com.fiap.techchallenge.pedido.infra.persistence.mapper.PedidoMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository jpaRepository;

    public PedidoRepositoryImpl(PedidoJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {

        PedidoJpaEntity entity = new PedidoJpaEntity();

        entity.setUserId(pedido.getUserId());
        entity.setRestauranteId(pedido.getRestauranteId());
        entity.setDescricao(pedido.getDescricao());
        entity.setValorTotal(pedido.getValorTotal());

        // evita erro de null no banco
        if (pedido.getCriadoEm() == null) {
            entity.setCriadoEm(java.time.LocalDateTime.now());
        } else {
            entity.setCriadoEm(pedido.getCriadoEm());
        }

        entity.setStatus(pedido.getStatus());

        entity = jpaRepository.save(entity);

        return new Pedido(
                entity.getId(),
                entity.getUserId(),
                entity.getRestauranteId(),
                entity.getDescricao(),
                entity.getValorTotal(),
                entity.getCriadoEm(),
                entity.getStatus()
        );
    }

    @Override
    @Transactional
    public void atualizarStatus(Long pedidoId, String status) {

        PedidoJpaEntity entity =
                jpaRepository.findById(pedidoId)
                        .orElseThrow();

        entity.setStatus(status);

        jpaRepository.save(entity);
    }

    @Override
    public List<Pedido> findByUserId(Long clienteId) {

        return jpaRepository
                .findByUserId(clienteId)
                .stream()
                .map(PedidoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaRepository.findById(id)
                .map(PedidoMapper::toDomain);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(PedidoMapper::toDomain);
    }

}