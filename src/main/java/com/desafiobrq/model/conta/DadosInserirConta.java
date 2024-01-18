package com.desafiobrq.model.conta;

import java.math.BigDecimal;

public record DadosInserirConta(
        String agencia,
        String numeroConta,
        String documentoCliente,
        BigDecimal saldo
) {
}
