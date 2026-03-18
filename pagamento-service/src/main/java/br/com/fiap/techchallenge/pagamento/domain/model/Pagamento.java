package br.com.fiap.techchallenge.pagamento.domain.model;

import java.time.LocalDateTime;

public class Pagamento {

    private Long id;
    private Long pedidoId;
    private String status;
    private LocalDateTime processadoEm;
    private Integer tentativas;

    // construtor para novo pagamento
    public Pagamento(Long pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.processadoEm = LocalDateTime.now();
        this.tentativas = 1;
    }

    // construtor para reconstrução do banco
    public Pagamento(Long id, Long pedidoId, String status, LocalDateTime processadoEm) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.status = status;
        this.processadoEm = processadoEm;
    }

    public void incrementarTentativas() {
        if (tentativas == null) {
            tentativas = 1;
        }
        this.tentativas++;
    }

    public Integer getTentativas() { return tentativas == null ? 0 : tentativas; }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }
}