package com.desafiobrq.model.endereco;

import com.desafiobrq.entity.Endereco;

public record DadosDetalhamentoEndereco(
        Long idEndereco,
        String logradouro,
        String numero,
        String cidade,
        String estado,
        String pais
) {
    public DadosDetalhamentoEndereco(Endereco endereco){
        this(
                endereco.getIdEndereco(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais()
        );
    }

}
