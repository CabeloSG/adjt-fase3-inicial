package br.com.fiap.techchallenge.pedido.domain.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoCriadoEvent {

    private Long pedidoId;
    private Long userId;
    private String descricao;
    private BigDecimal valorTotal;
    private Long clienteId;
    private LocalDateTime criadoEm;

    public PedidoCriadoEvent() {}

    public Long getPedidoId() { return pedidoId; }
    public Long getUserId() { return userId; }
    public Long getClienteId() { return clienteId; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}