package br.com.fiap.techchallenge.pedido.infra.kafka;

import br.com.fiap.techchallenge.pedido.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PedidoEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarPedidoCriado(PedidoCriadoEvent event) {
        kafkaTemplate.send("pedido.criado", event);
    }
}