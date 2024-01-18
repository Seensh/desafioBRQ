package com.desafiobrq.service;

import com.desafiobrq.model.transferencia.DadosInserirTransferencia;
import com.desafiobrq.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
public class TransferenciaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    //@Autowired
    //private ProducerTemplate producerTemplate;

    public ResponseEntity<String> realizarTransferencia(String agenciaOrigem, String numeroContaOrigem, DadosInserirTransferencia dados) {
        var contaOrigem = contaRepository.findByAgenciaAndNumeroConta(agenciaOrigem, numeroContaOrigem);

        if (!"ativo".equals(contaOrigem.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A conta origem não está ativa.");
        }

        var contaDestino = contaRepository.findByAgenciaAndNumeroConta(dados.agenciaDestino(), dados.contaDestino());

        if (!"ativo".equals(contaDestino.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A conta destino não está ativa.");
        }

        if (Objects.nonNull(contaOrigem.getSaldo()) && dados.valor().compareTo(BigDecimal.ZERO) > 0) {
            if(contaOrigem.getSaldo().compareTo(dados.valor()) >= 0){

                BigDecimal novoSaldoOrigem = contaOrigem.getSaldo().subtract(dados.valor());
                contaOrigem.ajusteSaldo(novoSaldoOrigem);

                BigDecimal novoSaldoDestino = contaDestino.getSaldo().add(dados.valor());
                contaDestino.ajusteSaldo(novoSaldoDestino);

                /*
                    producerTemplate.sendBodyAndHeader("direct:notificarOrigem", "Transferência realizada com sucesso",
                        Map.of("valor", dados.valor(), "nomeClienteDestino", contaDestino.getCliente().getNome()));

                    producerTemplate.sendBodyAndHeader("direct:notificarDestino", "Transferência realizada com sucesso",
                        Map.of("valor", dados.valor(), "nomeClienteOrigem", contaOrigem.getCliente().getNome()));
                 */
                contaRepository.save(contaOrigem);

                String mensagemOrigem = "{\"mensagem\": \"Transferência de R$ " + dados.valor() +
                        " realizada com sucesso para: " + contaDestino.getCliente().getNome() +
                        "\", \"cliente\": \"" + contaOrigem.getCliente().getNome() + "\"}";

                // Enviar a mensagem para notificarOrigem de forma assíncrona
                webClientBuilder.build()
                        .post()
                        .uri("http://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3")
                        .body(BodyInserters.fromValue(mensagemOrigem))
                        .retrieve()
                        .bodyToMono(String.class)
                        .subscribe(); // A chamada é assíncrona, não bloqueia a thread principal

                contaRepository.save(contaDestino);

                // Montar a mensagem para notificarDestino
                String mensagemDestino = "{\"mensagem\": \"Você recebeu: R$ " + dados.valor() +
                        " de " + contaOrigem.getCliente().getNome() +
                        "\", \"cliente\": \"" + contaDestino.getCliente().getNome() + "\"}";

                // Enviar a mensagem para notificarDestino de forma assíncrona
                webClientBuilder.build()
                        .post()
                        .uri("http://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3")
                        .body(BodyInserters.fromValue(mensagemDestino))
                        .retrieve()
                        .bodyToMono(String.class)
                        .subscribe(); // A chamada é assíncrona, não bloqueia a thread principal

            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente para a transferência. Saldo atual: " + contaOrigem.getSaldo());
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato do campo valor inválido ou valor menor que 0");
        }

        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }
}
