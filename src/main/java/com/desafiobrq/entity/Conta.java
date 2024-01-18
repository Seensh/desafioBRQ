package com.desafiobrq.entity;

import com.desafiobrq.model.conta.DadosInserirConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "conta")
@Table(name = "conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idConta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @Column(unique = true, nullable = false)
    private String agencia;

    @Column(unique = true, nullable = false)
    private String numeroConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(length = 10)
    private String status;

    @Column(nullable = false)
    private Date dataInclusao;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Conta(DadosInserirConta dadosConta, Cliente cliente) {
        this.agencia = dadosConta.agencia();
        this.numeroConta = dadosConta.numeroConta();
        this.status = "ativo";
        this.saldo = dadosConta.saldo();
        this.dataInclusao = new Date();
        this.cliente = cliente;
    }
}