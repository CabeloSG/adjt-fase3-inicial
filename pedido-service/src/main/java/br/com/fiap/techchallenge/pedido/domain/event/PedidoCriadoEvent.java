package br.com.fiap.techchallenge.pedido.domain.event;

import java.math.BigDecimal;

public class PedidoCriadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private BigDecimal valorTotal;

    public PedidoCriadoEvent() {}

    public PedidoCriadoEvent(Long pedidoId, Long clienteId, BigDecimal valorTotal) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}