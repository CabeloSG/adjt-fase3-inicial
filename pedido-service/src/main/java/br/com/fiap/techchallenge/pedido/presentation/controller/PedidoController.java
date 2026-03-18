package br.com.fiap.techchallenge.pedido.presentation.controller;

import br.com.fiap.techchallenge.pedido.application.dto.CriarPedidoRequest;
import br.com.fiap.techchallenge.pedido.application.service.ConfirmarPedidoService;
import br.com.fiap.techchallenge.pedido.application.service.CriarPedidoService;
import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.sql.SQLOutput;
import java.util.List;

@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoService service;
    private final PedidoRepository pedidoRepository;
    private final ConfirmarPedidoService confirmarPedidoService;

    public PedidoController(CriarPedidoService service, PedidoRepository pedidoRepository, ConfirmarPedidoService confirmarPedidoService) {
        this.service = service;
        this.pedidoRepository = pedidoRepository;
        this.confirmarPedidoService = confirmarPedidoService;
    }

    @Operation(summary = "Criar pedido")
    @PostMapping
    public Pedido criar(
            @RequestBody CriarPedidoRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {

        System.out.println("========= REQUISIÇÂO CHEGOU NO PEDIDO SERVICE ==========");

        Long userId = jwt.getClaim("userId");

        System.out.println("USER ID: " + userId);

        return service.executar(
                userId,
                request
        );
    }

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {

        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Operation(summary = "Buscar pedidos por cliente")
    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> buscarPorCliente(@PathVariable Long clienteId) {

        return pedidoRepository.findByUserId(clienteId);
    }

    @Operation(summary = "Confirma o pedido")
    @PostMapping("/{id}/confirmar")
    public void confirmar(@PathVariable Long id) {
        confirmarPedidoService.confirmar(id);
    }

}