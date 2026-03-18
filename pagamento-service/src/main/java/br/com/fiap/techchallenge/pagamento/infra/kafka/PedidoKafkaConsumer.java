package br.com.fiap.techchallenge.pagamento.infra.kafka;

import br.com.fiap.techchallenge.pagamento.application.service.ProcessarPagamentoService;
import br.com.fiap.techchallenge.pedido.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaConsumer {

    private final ProcessarPagamentoService service;

    public PedidoKafkaConsumer(ProcessarPagamentoService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "pedido.criado",
            groupId = "pagamento-service"
    )
    public void consumir(PedidoCriadoEvent event) {

        System.out.println("Evento pedido.criado recebido: " + event.getPedidoId());

        System.out.println("========== EVENTO RECEBIDO ==========");
        System.out.println("PedidoId: " + event.getPedidoId());
        System.out.println("ClienteId: " + event.getClienteId());
        System.out.println("Valor: " + event.getValorTotal());
        System.out.println("=====================================");

        service.processar(
                event.getPedidoId(),
                event.getClienteId(),
                event.getValorTotal()
        );
    }
}