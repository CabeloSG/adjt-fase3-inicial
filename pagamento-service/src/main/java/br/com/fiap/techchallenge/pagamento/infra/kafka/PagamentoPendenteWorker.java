package br.com.fiap.techchallenge.pagamento.infra.kafka;

import br.com.fiap.techchallenge.pagamento.application.service.ProcessarPagamentoService;
import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoPendenteEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPendenteWorker {

    private final ProcessarPagamentoService service;

    public PagamentoPendenteWorker(ProcessarPagamentoService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "pagamento.pendente",
            groupId = "pagamento-worker"
    )
    public void reprocessar(PagamentoPendenteEvent event) {

        try {

            System.out.println("Reprocessamento de pagamento pendente: " + event.getPedidoId());

            service.processar(
                    event.getPedidoId(),
                    event.getClienteId(),
                    event.getValorTotal()

            );

            System.out.println("Reprocessamento concluído para pedido: " + event.getPedidoId());

        } catch (Exception e) {

            System.out.println("Erro ao reprocessar pagamento do pedido "
                    + event.getPedidoId() + " : " + e.getMessage());

        }
    }
}