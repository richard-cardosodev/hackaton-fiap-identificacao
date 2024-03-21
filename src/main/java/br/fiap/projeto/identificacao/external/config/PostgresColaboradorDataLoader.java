package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoColaboradorUsecase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration @RequiredArgsConstructor
public class PostgresColaboradorDataLoader {

    private final IGestaoColaboradorUsecase gestaoColaboradorUsecase;

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Colaborador> colaboradors = Collections.singletonList(
                new Colaborador(UUID.randomUUID().toString(), "Colaborador1", "8999", "colaborador@test.com", "abc12345")
        );
        colaboradors.forEach(c -> {
            try {
                gestaoColaboradorUsecase.insere(c);
            } catch (EntradaInvalidaException e) {
                throw new RuntimeException(e);
            } catch (EntidadeNaoEncontradaException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
