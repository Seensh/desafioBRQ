package com.desafiobrq.service;

import com.desafiobrq.entity.Cliente;
import com.desafiobrq.entity.Conta;
import com.desafiobrq.model.conta.DadosDetalhamenteConta;
import com.desafiobrq.model.conta.DadosInserirConta;
import com.desafiobrq.repository.ClienteRepository;
import com.desafiobrq.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public DadosDetalhamenteConta criarConta(DadosInserirConta dadosConta){
        Cliente cliente = clienteRepository.findByDocumento(dadosConta.documentoCliente());
        var conta = new Conta(dadosConta, cliente);
        contaRepository.saveAndFlush(conta);
        return new DadosDetalhamenteConta(conta);
    }


}
