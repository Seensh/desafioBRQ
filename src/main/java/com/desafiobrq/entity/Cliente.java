package com.desafiobrq.entity;

import com.desafiobrq.model.cliente.DadosInserirCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String documento;

    @Column(nullable = false)
    private String tipoPessoa;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Date dataInclusao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Conta> contas;

    public Cliente(DadosInserirCliente dadosCliente) {
        this.nome = dadosCliente.nome();
        this.email = dadosCliente.email();
        this.documento = dadosCliente.documento();
        this.tipoPessoa = dadosCliente.documento().length() > 11 ? "PJ" : "PF";
        this.senha = dadosCliente.senha();
        this.dataInclusao = new Date();
        if(dadosCliente.endereco() != null) {
            this.endereco = new Endereco(dadosCliente.endereco());
        }
    }
}
