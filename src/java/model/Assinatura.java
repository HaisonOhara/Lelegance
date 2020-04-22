package model;

import java.util.Date;

public class Assinatura {

    private int numeroMeses;

    private String descricao;

    private Estilo estilo;
    
    private double total;
    
    private Date data_assinatura;
    
    private Usuario usuario;

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

}
