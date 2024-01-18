package com.desafiobrq.service;

import com.desafiobrq.entity.Conta;
import com.desafiobrq.model.cliente.DadosDetalhamentoCliente;

import java.math.BigDecimal;

public record DadosDetalhamenteConta(
        Long idConta,
        String agencia,
        String numeroConta,
        BigDecimal saldo,
        String status,
        Long idCliente
) {
    public DadosDetalhamenteConta(Conta conta) {
        this(
                conta.getIdConta(),
                conta.getAgencia(),
                conta.getNumeroConta(),
                conta.getSaldo(),
                conta.getStatus(),
                conta.getCliente() != null ? conta.getCliente().getIdCliente() : null
        );
    }
}
