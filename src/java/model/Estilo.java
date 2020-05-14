/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alunocmc
 */
public class Estilo {

    private int id;
    private String nome;
    private String imagem;
    private String descricao;
    private String status;
    private double valor;
    private String tamanhoCamiseta;
    private int tamanhoCalca;
    private int tamanhoCalcado;
//    private Fornecedor fornecedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTamanhoCamiseta() {
        return tamanhoCamiseta;
    }

    public void setTamanhoCamiseta(String tamanhoCamiseta) {
        this.tamanhoCamiseta = tamanhoCamiseta;
    }

    public int getTamanhoCalca() {
        return tamanhoCalca;
    }

    public void setTamanhoCalca(int tamanhoCalca) {
        this.tamanhoCalca = tamanhoCalca;
    }

    public int getTamanhoCalcado() {
        return tamanhoCalcado;
    }

    public void setTamanhoCalcado(int tamanhoCalcado) {
        this.tamanhoCalcado = tamanhoCalcado;
    }
    

}
