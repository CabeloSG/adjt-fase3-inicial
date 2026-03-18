package br.com.fiap.techchallenge.pedido.infra.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "status")
    private String status;

    @Column(nullable = false)
    private Long restauranteId;

    public PedidoJpaEntity() {
    }

    // Construtor usado ao salvar
    public PedidoJpaEntity(Long userId,
                           String descricao,
                           BigDecimal valorTotal,
                           LocalDateTime criadoEm,
                           String status,
                           Long restauranteId) {
        this.userId = userId;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.criadoEm = criadoEm;
        this.status = status;
        this.restauranteId = restauranteId;
    }

    // Construtor completo (opcional, mas profissional)
    public PedidoJpaEntity(Long id,
                           Long userId,
                           String descricao,
                           BigDecimal valorTotal,
                           LocalDateTime criadoEm,
                           String status,
                           Long restauranteId) {
        this.id = id;
        this.userId = userId;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.criadoEm = criadoEm;
        this.status = status;
        this.restauranteId = restauranteId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }
}