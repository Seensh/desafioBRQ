package com.desafiobrq.controller;

import com.desafiobrq.model.transferencia.DadosInserirTransferencia;
import com.desafiobrq.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("agencia/{agenciaOrigem}/conta/{contaOrigem}")
    @Transactional
    public ResponseEntity incluirTransferencia(@PathVariable String agenciaOrigem, @PathVariable String contaOrigem, @RequestBody @Valid DadosInserirTransferencia dados){
        return transferenciaService.realizarTransferencia(agenciaOrigem, contaOrigem, dados);
    }

}
