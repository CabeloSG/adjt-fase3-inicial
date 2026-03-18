package br.com.fiap.techchallenge.pagamento.application.service;

import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoAprovadoEvent;
import br.com.fiap.techchallenge.pagamento.domain.event.PagamentoPendenteEvent;
import br.com.fiap.techchallenge.pagamento.domain.model.Pagamento;
import br.com.fiap.techchallenge.pagamento.domain.repository.PagamentoRepository;
import br.com.fiap.techchallenge.pagamento.infra.client.PagamentoExternoClient;
import br.com.fiap.techchallenge.pagamento.infra.kafka.PagamentoEventProducer;
import br.com.fiap.techchallenge.pagamento.infra.kafka.PagamentoPendenteProducer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProcessarPagamentoService {

    private final PagamentoRepository repository;
    private final PagamentoExternoClient client;
    private final PagamentoEventProducer eventProducer;
    private final PagamentoPendenteProducer pagamentoPendenteProducer;
    private static final int MAX_TENTATIVAS = 3;

    public ProcessarPagamentoService(
            PagamentoRepository repository,
            PagamentoExternoClient client,
            PagamentoEventProducer eventProducer,
            PagamentoPendenteProducer pagamentoPendenteProducer // NOVO
    ) {
        this.repository = repository;
        this.client = client;
        this.eventProducer = eventProducer;
        this.pagamentoPendenteProducer = pagamentoPendenteProducer; // NOVO
    }

    public Pagamento processar(Long pedidoId, Long clienteId, BigDecimal valorTotal) {

        Optional<Pagamento> pagamentoExistente = repository.buscarPorPedidoId(pedidoId);

        Pagamento pagamento;

        if (pagamentoExistente.isPresent()) {

            pagamento = pagamentoExistente.get();

            // IDEMPOTÊNCIA
            if ("APROVADO".equals(pagamento.getStatus())) {
                System.out.println("Pagamento já aprovado para pedido: " + pedidoId);
                return pagamento;
            }

            // CONTROLE DE TENTATIVAS
            if (pagamento.getTentativas() >= MAX_TENTATIVAS) {

                System.out.println("Pagamento excedeu número máximo de tentativas: " + pedidoId);

                pagamento.setStatus("FALHOU");

                return repository.salvar(pagamento);
            }

            pagamento.incrementarTentativas();

        } else {

            pagamento = new Pagamento(
                    pedidoId,
                    "PROCESSANDO"
            );

        }

        boolean aprovado = client
                .processarPagamento(pedidoId, clienteId, valorTotal)
                .join();

        if (aprovado) {

            pagamento.setStatus("APROVADO");

            System.out.println("Pagamento aprovado para pedido: " + pedidoId);

            eventProducer.publicarPagamentoAprovado(
                    new PagamentoAprovadoEvent(pedidoId)
            );

        } else {

            pagamento.setStatus("PENDENTE");

            System.out.println("Pagamento externo indisponível para pedido: " + pedidoId);

            if(pagamento.getTentativas() < MAX_TENTATIVAS) {

                pagamentoPendenteProducer.enviar(
                        new PagamentoPendenteEvent(
                                pedidoId,
                                clienteId,
                                valorTotal
                        )
                );
            }
        }

        return repository.salvar(pagamento);
    }

}