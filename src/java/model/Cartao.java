/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joaov
 */
public class Cartao {
    private int id;
    private String numero;
    private String vencimento;
    private int cnn;
    private String nomeCartao;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public int getCnn() {
        return cnn;
    }

    public void setCnn(int cnn) {
        this.cnn = cnn;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
