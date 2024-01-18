package com.desafiobrq.model.cliente;

import com.desafiobrq.entity.Cliente;
import com.desafiobrq.model.endereco.DadosDetalhamentoEndereco;
import com.desafiobrq.model.conta.DadosDetalhamenteConta;

import java.util.List;


public record DadosDetalhamentoCliente(
        Long idCliente,
        String nome,
        String email,
        String documento,
        String tipoPessoa,
        DadosDetalhamentoEndereco endereco,
        List<DadosDetalhamenteConta> contas
) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getDocumento(),
                cliente.getTipoPessoa(),
                cliente.getEndereco() == null ? null : new DadosDetalhamentoEndereco(cliente.getEndereco()),
                cliente.getContas() == null ? null : cliente.getContas().stream().map(DadosDetalhamenteConta::new).toList()
        );
    }

}
