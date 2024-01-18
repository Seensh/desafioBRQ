package com.desafiobrq.controller;

import com.desafiobrq.model.conta.DadosInserirConta;
import com.desafiobrq.service.ContaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    @Transactional
    public ResponseEntity criarConta(@RequestBody @Valid DadosInserirConta dados, UriComponentsBuilder uriComponentsBuilder) {
        var dadosDetalhamentoConta = contaService.criarConta(dados);

        var uri = uriComponentsBuilder.path("/api/contas/{id}").buildAndExpand(dadosDetalhamentoConta.idConta()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoConta);
    }

}
