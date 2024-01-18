package com.desafiobrq.model.cliente;

import com.desafiobrq.model.endereco.DadosInserirEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record DadosInserirCliente(
        @NotBlank(message = "O nome não pode estar em branco") String nome,
        @NotBlank(message = "O e-mail não pode estar em branco") @Email(message = "O e-mail deve ser válido") String email,
        @NotBlank(message = "A senha não pode estar em branco") String senha,
        @NotBlank(message = "O documento não pode estar em branco") String documento,
        DadosInserirEndereco endereco
) {


}
