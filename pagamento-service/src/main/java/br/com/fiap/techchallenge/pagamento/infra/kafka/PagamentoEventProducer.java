package br.com.fiap.techchallenge.pagamento.infra.kafka;

import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoAprovadoEvent;
import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoPendenteEvent;
import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoProcessadoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PagamentoEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarPagamentoProcessado(PagamentoProcessadoEvent event) {

        kafkaTemplate.send("pagamento.processado", event);

        System.out.println("Evento pagamento.processado enviado: " + event.getPedidoId());
    }

    public void publicarPagamentoAprovado(PagamentoAprovadoEvent event) {

        kafkaTemplate.send("pagamento.aprovado", event);

        System.out.println("Evento pagamento.aprovado enviado: " + event.getPedidoId());
    }

    public void publicarPagamentoPendente(Long pedidoId, Long clienteId, BigDecimal valorTotal) {

        PagamentoPendenteEvent evento =
                new PagamentoPendenteEvent(
                        pedidoId,
                        clienteId,
                        valorTotal
                );

        kafkaTemplate.send("pagamento.pendente", evento);

        System.out.println("Evento pagamento.pendente enviado: " + pedidoId);
    }
}