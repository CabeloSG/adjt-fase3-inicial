package br.com.fiap.techchallenge.pedido.application.service;

import br.com.fiap.techchallenge.pedido.domain.event.PedidoCriadoEvent;
import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import br.com.fiap.techchallenge.pedido.infra.kafka.PedidoEventProducer;
import org.springframework.stereotype.Service;

@Service
public class ConfirmarPedidoService {

    private final PedidoRepository repository;
    private final PedidoEventProducer producer;

    public ConfirmarPedidoService(
            PedidoRepository repository,
            PedidoEventProducer producer
    ) {
        this.repository = repository;
        this.producer = producer;
    }

    public void confirmar(Long pedidoId) {

        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        repository.atualizarStatus(pedidoId, "CRIADO");

        producer.publicarPedidoCriado(
                new PedidoCriadoEvent(
                        pedido.getId(),
                        pedido.getUserId(),
                        pedido.getValorTotal()
                )
        );
    }
}