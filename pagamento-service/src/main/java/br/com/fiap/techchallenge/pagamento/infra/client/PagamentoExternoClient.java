package br.com.fiap.techchallenge.pagamento.infra.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class PagamentoExternoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "pagamentoService", fallbackMethod = "fallback")
    @Retry(name = "pagamentoService")
    @TimeLimiter(name = "pagamentoService")
    public CompletableFuture<Boolean> processarPagamento(Long pedidoId, Long clienteId, BigDecimal valor) {

        return CompletableFuture.supplyAsync(() -> {

            try {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> body = new HashMap<>();
                body.put("pagamento_id", String.valueOf(pedidoId));
                body.put("cliente_id", String.valueOf(clienteId));
                body.put("valor", valor.doubleValue());

                System.out.println("=========== PAGAMENTO REQUEST =============");
                System.out.println("PedidoId: " + pedidoId);
                System.out.println("ClienteId: " + clienteId);
                System.out.println("Valor: " + valor);
                System.out.println("Body enviado: " + body);
                System.out.println("===========================================");

                HttpEntity<Map<String, Object>> request =
                        new HttpEntity<>(body, headers);

                ResponseEntity<String> response =
                        restTemplate.exchange(
                                "http://procpag:8089/requisicao",
                                HttpMethod.POST,
                                request,
                                String.class
                        );

                System.out.println("============ RESPOSTA PROCPAG =============");
                System.out.println("Status: " + response.getStatusCode());
                System.out.println("Body: " + response.getBody());
                System.out.println("===========================================");

                int status = response.getStatusCode().value();

                if (status == 200 || status == 201) {

                    System.out.println("Pagamento aprovado para pedido: " + pedidoId);
                    return true;

                }

                return false;

            } catch (HttpClientErrorException e) {

                /*
                 BUG DO PROCPAG
                 Ele retorna HTTP 400 mesmo quando o pagamento foi aprovado
                 */

                if (e.getStatusCode().value() == 400) {

                    System.out.println("Pagamento aprovado (BUG PROCPAG) para pedido: " + pedidoId);
                    return true;

                }

                System.out.println("Erro HTTP ao chamar procpag: " + e.getMessage());
                return false;

            } catch (Exception e) {

                System.out.println("Erro ao chamar procpag: " + e.getMessage());
                throw new RuntimeException("Erro no pagamento externo");

            }

        });
    }

    public CompletableFuture<Boolean> fallback(
            Long pedidoId,
            Long clienteId,
            BigDecimal valor,
            Throwable ex
    ) {

        System.out.println("Fallback do pagamento externo para pedido: " + pedidoId);
        System.out.println("Motivo: " + ex.getMessage());

        return CompletableFuture.completedFuture(false);
    }
}