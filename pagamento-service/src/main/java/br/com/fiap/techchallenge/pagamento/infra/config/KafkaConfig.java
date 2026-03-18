package br.com.fiap.techchallenge.pagamento.infra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic pedidoCriadoTopic() {
        return new NewTopic("pedido.criado", 1, (short) 1);
    }

    @Bean
    public NewTopic pagamentoAprovadoTopic() {
        return new NewTopic("pagamento.aprovado", 1, (short) 1);
    }

    @Bean
    public NewTopic pagamentoPendenteTopic() {
        return new NewTopic("pagamento.pendente", 1, (short) 1);
    }

}