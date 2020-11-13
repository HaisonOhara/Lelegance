package model;

import java.util.Date;

public class Assinatura {
    private int id;

    private int numeroMeses;

//    private int meses_pagos;
    private String descricao;

    private Estilo estilo;

    private double total;

    private double valorFrete;

    private Date data_assinatura;

    private Usuario usuario;

    private String status;

    private String tamanhoCamiseta;

    private int tamanhoCalca;

    private int tamanhoCalcado;

//    public int getMeses_pagos() {
//        return meses_pagos;
//    }
//
//    public void setMeses_pagos(int meses_pagos) {
//        this.meses_pagos = meses_pagos;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }
    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getData_assinatura() {
        return data_assinatura;
    }

    public void setData_assinatura(Date data_assinatura) {
        this.data_assinatura = data_assinatura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNumeroMeses() {
        return numeroMeses;
    }

    public void setNumeroMeses(int numeroMeses) {
        this.numeroMeses = numeroMeses;
    }

    //Status pode ser util no futuro
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

    public double getvalorFrete() {
        return valorFrete;
    }

    public void setvalorFrete(double frete) {
        this.valorFrete = frete;
    }

}
