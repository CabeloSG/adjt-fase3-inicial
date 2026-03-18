package br.com.fiap.techchallenge.pedido.application.dto;

import java.util.List;

public class CriarPedidoRequest {

    private Long restauranteId;
    private List<ItemPedidoRequest> itens;

    public CriarPedidoRequest(Long restauranteId, List<ItemPedidoRequest> itens) {
        this.restauranteId = restauranteId;
        this.itens = itens;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }
}
