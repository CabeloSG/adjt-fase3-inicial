package br.com.fiap.techchallenge.pagamento.domain.event;

public class PagamentoAprovadoEvent {

    private Long pedidoId;

    public PagamentoAprovadoEvent() {}

    public PagamentoAprovadoEvent(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }
}