package com.desafiobrq.service;

import com.desafiobrq.entity.Cliente;
import com.desafiobrq.model.cliente.DadosDetalhamentoCliente;
import com.desafiobrq.model.cliente.DadosInserirCliente;
import com.desafiobrq.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public DadosDetalhamentoCliente cadastrarCliente(DadosInserirCliente dadosCliente) {
        var cliente = repository.saveAndFlush(new Cliente(dadosCliente));

        return new DadosDetalhamentoCliente(cliente);
    }

    public DadosDetalhamentoCliente buscaPorDocumento(String documento) {
        return new DadosDetalhamentoCliente(repository.findByDocumento(documento));
    }
}
