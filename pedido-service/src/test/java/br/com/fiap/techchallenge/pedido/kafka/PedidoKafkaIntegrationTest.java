package br.com.fiap.techchallenge.pedido.kafka;

import br.com.fiap.techchallenge.pedido.PedidoApplication;
import br.com.fiap.techchallenge.pedido.domain.event.PedidoCriadoEvent;
import br.com.fiap.techchallenge.pedido.infra.kafka.PedidoEventProducer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(classes = PedidoApplication.class)
@Disabled
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration"
})
class PedidoKafkaIntegrationTest {

    @Autowired
    private PedidoEventProducer producer;

    @Test
    void devePublicarEventoPedidoCriado() {

        PedidoCriadoEvent event =
                new PedidoCriadoEvent(
                        1L,
                        1L,
                        new BigDecimal("50")
                );

        producer.publicarPedidoCriado(event);
    }
}