package br.com.fiap.techchallenge.pagamento.infra.kafka;

import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoPendenteEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPendenteProducer {

    private final KafkaTemplate<String, PagamentoPendenteEvent> kafkaTemplate;

    public PagamentoPendenteProducer(KafkaTemplate<String, PagamentoPendenteEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviar(PagamentoPendenteEvent event) {

        kafkaTemplate.send(
                "pagamento.pendente",
                event
        );

        System.out.println("Evento pagamento.pendente enviado: " + event.getPedidoId());
    }
}