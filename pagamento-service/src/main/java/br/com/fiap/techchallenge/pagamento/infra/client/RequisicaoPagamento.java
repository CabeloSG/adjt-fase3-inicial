package br.com.fiap.techchallenge.pagamento.infra.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequisicaoPagamento {

    @JsonProperty("valor")
    private Integer valor;
    @JsonProperty("pagamento_id")
    private String pagamento_id;
    @JsonProperty("cliente_id")
    private String cliente_id;

    public RequisicaoPagamento(Integer valor, String pagamento_id, String cliente_id) {
        this.valor = valor;
        this.pagamento_id = pagamento_id;
        this.cliente_id = cliente_id;
    }

    public Integer getValor() {
        return valor;
    }

    public String getPagamento_id() {
        return pagamento_id;
    }

    public String getCliente_id() {
        return cliente_id;
    }
}