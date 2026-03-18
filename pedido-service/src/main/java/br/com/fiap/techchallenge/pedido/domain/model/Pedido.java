package br.com.fiap.techchallenge.pedido.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {

    private Long id;
    private Long userId;
    private Long restauranteId;
    private String descricao;
    private BigDecimal valorTotal;
    private LocalDateTime criadoEm;
    private String status;

    // Criar pedido novo (quando usuário monta pedido)
    public Pedido(Long userId,
                  Long restauranteId,
                  String descricao,
                  BigDecimal valorTotal) {

        this.userId = userId;
        this.restauranteId = restauranteId;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.criadoEm = LocalDateTime.now();
        this.status = "AGUARDANDO_CONFIRMACAO";
    }

    //  Criar pedido calculado (usado pelo serviço)
    public Pedido(Long userId,
                  Long restauranteId,
                  BigDecimal valorTotal,
                  String status) {

        this.userId = userId;
        this.restauranteId = restauranteId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.criadoEm = LocalDateTime.now();
    }

    //  Reconstrução do banco (Repository)
    public Pedido(Long id,
                  Long userId,
                  Long restauranteId,
                  String descricao,
                  BigDecimal valorTotal,
                  LocalDateTime criadoEm,
                  String status) {

        this.id = id;
        this.userId = userId;
        this.restauranteId = restauranteId;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.criadoEm = criadoEm;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRestauranteId() {
        return restauranteId;
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

    public void atualizarStatus(String status) {
        this.status = status;
    }
}