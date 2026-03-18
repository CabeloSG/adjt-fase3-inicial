package br.com.fiap.techchallenge.pedido.controller;

import br.com.fiap.techchallenge.pedido.application.service.ConfirmarPedidoService;
import br.com.fiap.techchallenge.pedido.domain.model.Pedido;
import br.com.fiap.techchallenge.pedido.domain.repository.PedidoRepository;
import br.com.fiap.techchallenge.pedido.presentation.controller.PedidoController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
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
class PedidoControllerTest {

    @Test
    void deveBuscarPedidoPorId() throws Exception {

        PedidoRepository repository = mock(PedidoRepository.class);
        ConfirmarPedidoService confirmarService = mock(ConfirmarPedidoService.class);

        Pedido pedido = new Pedido(
                1L,
                1L,
                "Pizza",
                new BigDecimal("50")
        );

        when(repository.findById(1L)).thenReturn(Optional.of(pedido));

        PedidoController controller =
                new PedidoController(null, repository, confirmarService);

        MockMvc mockMvc =
                MockMvcBuilders
                        .standaloneSetup(controller)
                        .build();

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk());
    }
}