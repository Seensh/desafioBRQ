package com.desafiobrq.model.transferencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosInserirTransferencia(
        @NotBlank(message = "A agenciaDestino não pode estar em branco") String agenciaDestino,
        @NotBlank(message = "A contaDestino não pode estar em branco") String contaDestino,
        @NotNull(message = "o campo valor não pode ser nulo") BigDecimal valor
) {
}
