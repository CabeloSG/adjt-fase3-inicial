package br.com.fiap.techchallenge.pagamento.infra.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long pedidoId;

    private String status;

    private LocalDateTime processadoEm;

    public PagamentoEntity() {}

    @Column(name = "tentativas")
    private Integer tentativas = 1;

    public PagamentoEntity(Long pedidoId, String status, LocalDateTime processadoEm) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.processadoEm = processadoEm;
    }

    public Long getId() { return id; }
    public Long getPedidoId() { return pedidoId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getProcessadoEm() { return processadoEm; }
    public void setProcessadoEm(LocalDateTime processadoEm) { this.processadoEm = processadoEm; }
    public Integer getTentativas() { return tentativas; }
    public void setTentativas(Integer tentativas) { this.tentativas = tentativas; }
}