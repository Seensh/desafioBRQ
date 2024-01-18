package com.desafiobrq.model.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosInserirEndereco(
        @NotBlank(message = "O logradouro não pode estar em branco") String logradouro,
        @NotBlank(message = "O numero não pode estar em branco") String numero,
        @NotBlank(message = "A cidade não pode estar em branco") String cidade,
        @NotBlank(message = "O estado não pode estar em branco") String estado,
        @NotBlank(message = "O pais não pode estar em branco") String pais
) { }
