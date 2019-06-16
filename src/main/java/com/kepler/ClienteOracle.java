package com.kepler;

import javax.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class ClienteOracle {

    @Id
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "local_nascimento")
    private String localNascimento;

    @Column(name = "versao")
    private Long versao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

}
