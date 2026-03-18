package br.com.fiap.techchallenge.pedido.application.service;

import br.com.fiap.techchallenge.pedido.application.dto.CriarPedidoRequest;
import br.com.fiap.techchallenge.pedido.application.dto.ItemPedidoRequest;
import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import br.com.fiap.techchallenge.pedido.infra.kafka.PedidoEventProducer;
import br.com.fiap.techchallenge.pedido.infra.persistence.entity.ProdutoJpaEntity;
import br.com.fiap.techchallenge.pedido.infra.persistence.repository.ProdutoJpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CriarPedidoService {

    private final PedidoRepository repository;
    private final PedidoEventProducer producer;
    private final ProdutoJpaRepository produtoJpaRepository;

    public CriarPedidoService(PedidoRepository repository,
                              PedidoEventProducer producer,
                              ProdutoJpaRepository produtoJpaRepository) {
        this.repository = repository;
        this.producer = producer;
        this.produtoJpaRepository = produtoJpaRepository;
    }

    public Pedido buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido executar(Long userId, CriarPedidoRequest request) {

        BigDecimal valorTotal = BigDecimal.ZERO;
        StringBuilder descricaoBuilder = new StringBuilder();

        for (ItemPedidoRequest item : request.getItens()) {

            ProdutoJpaEntity produto = produtoJpaRepository
                    .findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal subtotal =
                    produto.getPreco()
                            .multiply(BigDecimal.valueOf(item.getQuantidade()));

            valorTotal = valorTotal.add(subtotal);

            // monta a descrição do pedido
            if (descricaoBuilder.length() > 0) {
                descricaoBuilder.append(", ");
            }

            descricaoBuilder.append(produto.getNome());
        }

        String descricao = descricaoBuilder.toString();

        Pedido pedido = new Pedido(
                userId,
                request.getRestauranteId(),
                descricao,
                valorTotal
        );

        return repository.salvar(pedido);
    }
}