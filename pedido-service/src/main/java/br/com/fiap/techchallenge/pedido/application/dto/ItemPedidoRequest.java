package br.com.fiap.techchallenge.pedido.application.dto;

import java.math.BigDecimal;

public class ItemPedidoRequest {

    private Long produtoId;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedidoRequest(Long produtoId, String nome, Integer quantidade, BigDecimal preco) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
