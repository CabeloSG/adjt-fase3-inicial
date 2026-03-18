package br.com.fiap.techchallenge.pagamento.infra.controller;

import br.com.fiap.techchallenge.pagamento.domain.model.Pagamento;
import br.com.fiap.techchallenge.pagamento.domain.repository.PagamentoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoRepository repository;

    public PagamentoController(PagamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{pedidoId}")
    public Pagamento buscar(@PathVariable Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }
}