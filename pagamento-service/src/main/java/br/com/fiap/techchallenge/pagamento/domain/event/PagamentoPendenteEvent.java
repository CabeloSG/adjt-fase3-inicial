package br.com.fiap.techchallenge.pagamento.domain.event;

import java.math.BigDecimal;

public class PagamentoPendenteEvent {

    private Long pedidoId;
    private Long clienteId;
    private BigDecimal valorTotal;

    public PagamentoPendenteEvent() {}

    public PagamentoPendenteEvent(Long pedidoId, Long clienteId,BigDecimal valorTotal) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getClienteId() { return clienteId; }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}