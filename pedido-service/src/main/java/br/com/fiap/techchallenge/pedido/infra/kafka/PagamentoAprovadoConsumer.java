package br.com.fiap.techchallenge.pedido.infra.kafka;

import br.com.fiap.techchallenge.pedido.domain.event.PagamentoAprovadoEvent;
import br.com.fiap.techchallenge.pedido.application.service.AtualizarStatusPedidoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoAprovadoConsumer {

    private final AtualizarStatusPedidoService service;

    public PagamentoAprovadoConsumer(AtualizarStatusPedidoService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "pagamento.aprovado",
            groupId = "pedido-service"
    )
    public void consumir(PagamentoAprovadoEvent event) {

        System.out.println("Evento pagamento.aprovado recebido: " + event.getPedidoId());

        service.atualizarParaPago(event.getPedidoId());
    }
}