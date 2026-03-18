package br.com.fiap.techchallenge.pedido.domain.event;

public class PagamentoAprovadoEvent {

    private Long pedidoId;

    public PagamentoAprovadoEvent() {}

    public PagamentoAprovadoEvent(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}