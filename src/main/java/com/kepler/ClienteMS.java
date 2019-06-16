package com.kepler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "tb_cliente")
public class ClienteMS {

    @Id
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "padrao_consumo")
    private BigDecimal padraoConsumo;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

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

    public BigDecimal getPadraoConsumo() {
        return padraoConsumo;
    }

    public void setPadraoConsumo(BigDecimal padraoConsumo) {
        this.padraoConsumo = padraoConsumo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
}
