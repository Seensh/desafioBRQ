package com.desafiobrq.controller;

import com.desafiobrq.entity.Cliente;
import com.desafiobrq.model.cliente.DadosInserirCliente;
import com.desafiobrq.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCliente(@RequestBody @Valid DadosInserirCliente dados, UriComponentsBuilder uriComponentsBuilder) {
        var dadosDetalhamentoCliente = clienteService.cadastrarCliente(dados);

        var uri = uriComponentsBuilder.path("/api/cliente/{id}").buildAndExpand(dadosDetalhamentoCliente.idCliente()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoCliente);
    }

    @GetMapping("/documento/{documentoCliente}")
    public ResponseEntity buscarPorDocumentoCliente(@PathVariable String documentoCliente){
        return ResponseEntity.ok(clienteService.buscaPorDocumento(documentoCliente));
    }

}
