/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.automations;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assinatura;
import model.AssinaturaDAO;
import model.Estilo;
import model.EstiloDAO;
import model.Fornecedor;
import model.OrdemCompra;
import model.OrdemCompraDAO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Usuario
 */
public class JobOrdemCompra implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        AssinaturaDAO assinaturadao = new AssinaturaDAO();

        List<Assinatura> assinaturasMensais = new ArrayList();

        try {
            //Ideal seria ter status em assinatura e trazer so as ativas aqui
            assinaturasMensais = assinaturadao.CarregarAssinaturas();
            EstiloDAO estilodao = new EstiloDAO();
            Estilo estilo_mensal = estilodao.carregarEstilodoMes();

            //Mockando Fornecedor para testes!,pois o mesmo devera 
            //estar linkado com o estilod o mes
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(1);
            fornecedor.setCnpj("122312546");
            fornecedor.setNome("Zara");
            //-------------------------------------------------- 
//Conversao de data atual para logs
            long millis = System.currentTimeMillis();
            java.sql.Date data = new java.sql.Date(millis);
//--------------------------------------------------------------
            OrdemCompra ordem_compra = new OrdemCompra();
            ordem_compra.setEstilo(estilo_mensal);
            ordem_compra.setForncedor(fornecedor);
            ordem_compra.setQuantidade_vendida(assinaturasMensais.size());
            ordem_compra.setStatus("Aguardando Confirmação do Fornecedor");
            ordem_compra.setData(data);

            OrdemCompraDAO ordem_compra_dao = new OrdemCompraDAO();
//            System.out.println("id:" + (java.sql.Date) ordem_compra.getData());
            ordem_compra_dao.cadastraNovoEstilo(ordem_compra);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JobOrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JobOrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
