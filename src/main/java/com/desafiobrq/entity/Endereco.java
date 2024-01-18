package com.desafiobrq.entity;

import com.desafiobrq.model.endereco.DadosDetalhamentoEndereco;
import com.desafiobrq.model.endereco.DadosInserirEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "endereco")
@Table(name = "endereco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEndereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    private String logradouro;

    private String numero;

    private String cidade;

    private String estado;

    private String pais;

    public Endereco(DadosInserirEndereco dadosInserirEndereco){

        this.logradouro = dadosInserirEndereco.logradouro();
        this.numero = dadosInserirEndereco.numero();
        this.cidade = dadosInserirEndereco.cidade();
        this.estado = dadosInserirEndereco.estado();
        this.pais = dadosInserirEndereco.pais();

    }

    public Endereco(DadosDetalhamentoEndereco dadosDetalhamentoEndereco){

        this.logradouro = dadosDetalhamentoEndereco.logradouro();
        this.numero = dadosDetalhamentoEndereco.numero();
        this.cidade = dadosDetalhamentoEndereco.cidade();
        this.estado = dadosDetalhamentoEndereco.estado();
        this.pais = dadosDetalhamentoEndereco.pais();

    }
}