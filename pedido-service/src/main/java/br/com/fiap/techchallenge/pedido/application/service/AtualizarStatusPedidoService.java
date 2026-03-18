package br.com.fiap.techchallenge.pedido.application.service;

import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoService {

    private final PedidoRepository repository;

    public AtualizarStatusPedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public void atualizarParaPago(Long pedidoId) {

        repository.atualizarStatus(pedidoId, "PAGO");

        System.out.println("Pedido atualizado para PAGO: " + pedidoId);
    }
}