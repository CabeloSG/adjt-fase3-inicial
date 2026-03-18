package br.com.fiap.techchallenge.pedido.infra.persistence.mapper;

import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.infra.persistence.entity.PedidoJpaEntity;

public class PedidoMapper {

    public static PedidoJpaEntity toEntity(Pedido pedido) {
        return new PedidoJpaEntity(
                pedido.getUserId(),
                pedido.getDescricao(),
                pedido.getValorTotal(),
                pedido.getCriadoEm(),
                pedido.getStatus(),
                pedido.getRestauranteId()
        );
    }

    public static Pedido toDomain(PedidoJpaEntity entity) {

        if(entity == null) {
            return null;
        }

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
}