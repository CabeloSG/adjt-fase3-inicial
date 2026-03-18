package br.com.fiap.techchallenge.pagamento.infra.persistence;

import br.com.fiap.techchallenge.pagamento.domain.model.Pagamento;
import br.com.fiap.techchallenge.pagamento.domain.repository.PagamentoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PagamentoRepositoryImpl implements PagamentoRepository {

    private final PagamentoJpaRepository jpaRepository;

    public PagamentoRepositoryImpl(PagamentoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {

        Optional<PagamentoEntity> existente =
                jpaRepository.findByPedidoId(pagamento.getPedidoId());

        PagamentoEntity entity;

        if (existente.isPresent()) {

            // UPDATE
            entity = existente.get();

            entity.setStatus(pagamento.getStatus());
            entity.setProcessadoEm(pagamento.getProcessadoEm());
            entity.setTentativas(pagamento.getTentativas());

        } else {

            // INSERT
            entity = new PagamentoEntity(
                    pagamento.getPedidoId(),
                    pagamento.getStatus(),
                    pagamento.getProcessadoEm()
            );

            entity.setTentativas(pagamento.getTentativas());
        }

        entity = jpaRepository.save(entity);

        return new Pagamento(
                entity.getId(),
                entity.getPedidoId(),
                entity.getStatus(),
                entity.getProcessadoEm()
        );
    }

    @Override
    public Optional<Pagamento> findByPedidoId(Long pedidoId) {

        Optional<PagamentoEntity> entity = jpaRepository.findByPedidoId(pedidoId);

        if(entity.isEmpty()) {
            return Optional.empty();
        }

        PagamentoEntity pagamento = entity.get();

        return Optional.of(
                new Pagamento(
                        pagamento.getId(),
                        pagamento.getPedidoId(),
                        pagamento.getStatus(),
                        pagamento.getProcessadoEm()
                )
        );
    }

    @Override
    public Optional<Pagamento> buscarPorPedidoId(Long pedidoId) {
        return findByPedidoId(pedidoId);
    }
}