package br.com.fiap.techchallenge.pagamento.domain.event;

import java.time.LocalDateTime;

public class PagamentoProcessadoEvent {

    private Long pedidoId;
    private String status;
    private LocalDateTime processadoEm;

    public PagamentoProcessadoEvent(Long pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.processadoEm = LocalDateTime.now();
    }

    public Long getPedidoId() { return pedidoId; }
    public String getStatus() { return status; }
    public LocalDateTime getProcessadoEm() { return processadoEm; }
}