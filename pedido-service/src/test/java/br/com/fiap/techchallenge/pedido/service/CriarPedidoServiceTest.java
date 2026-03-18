package br.com.fiap.techchallenge.pedido.service;

import br.com.fiap.techchallenge.pedido.application.dto.CriarPedidoRequest;
import br.com.fiap.techchallenge.pedido.application.dto.ItemPedidoRequest;
import br.com.fiap.techchallenge.pedido.application.service.CriarPedidoService;
import br.com.fiap.techchallenge.pedido.domain.event.PedidoCriadoEvent;
import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import br.com.fiap.techchallenge.pedido.infra.kafka.PedidoEventProducer;
import br.com.fiap.techchallenge.pedido.infra.persistence.entity.ProdutoJpaEntity;
import br.com.fiap.techchallenge.pedido.infra.persistence.repository.ProdutoJpaRepository;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CriarPedidoServiceTest {

    @Test
    void deveCriarPedido() {

        PedidoRepository repository = mock(PedidoRepository.class);
        ProdutoJpaRepository produtoRepository = mock(ProdutoJpaRepository.class);

        PedidoEventProducer producer = new PedidoEventProducer(null) {
            @Override
            public void publicarPedidoCriado(PedidoCriadoEvent event) {
                // não envia para Kafka
            }
        };

        CriarPedidoService service =
                new CriarPedidoService(repository, producer, produtoRepository);

        ProdutoJpaEntity produto = new ProdutoJpaEntity();
        produto.setId(1L);
        produto.setPreco(new BigDecimal("50"));

        when(produtoRepository.findById(1L))
                .thenReturn(java.util.Optional.of(produto));

        Pedido pedido = new Pedido(
                1L,
                1L,
                "Pizza Calabresa",
                new BigDecimal("50")
        );

        when(repository.salvar(any())).thenReturn(pedido);

        ItemPedidoRequest item =
                new ItemPedidoRequest(
                        1L,
                        "Pizza Calabresa",
                        1,
                        new BigDecimal("50")
                );

        CriarPedidoRequest request =
                new CriarPedidoRequest(
                        1L,
                        List.of(item)
                );

        Pedido resultado =
                service.executar(
                        1L,
                        request
                );

        assertNotNull(resultado);
    }
}