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
import model.FornecedorDAO;
import model.OrdemCompra;
import model.OrdemCompraDAO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import util.EmailSender;

public class JobOrdemCompra implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        AssinaturaDAO assinaturadao = new AssinaturaDAO();

        List<Assinatura> assinaturasMensais = new ArrayList();

        try {
            assinaturasMensais = assinaturadao.CarregarAssinaturasAtivas();
            EstiloDAO estilodao = new EstiloDAO();
            Estilo estilo_mensal = estilodao.carregarEstilodoMes();
            
            System.out.print("ESTILO: "+estilo_mensal.getId());

//          Recuperando fornecedor Ativo
            FornecedorDAO forn_dao = new FornecedorDAO();
            Fornecedor fornecedor = forn_dao.carregarFornecedorAtivo();
//            System.out.print("FORNECEDOR");
//            System.out.print("EMAIL: "+fornecedor.getEmail());
//            System.out.print("NOME: "+fornecedor.getNome());
//            System.out.print("STATUS: "+fornecedor.getStatus());



//Conversao de data atual para logs
            long millis = System.currentTimeMillis();
            java.sql.Date data = new java.sql.Date(millis);
//--------------------------------------------------------------
            OrdemCompra ordem_compra = new OrdemCompra();
            ordem_compra.setEstilo(estilo_mensal);
            ordem_compra.setFornecedor(fornecedor);
            ordem_compra.setQuantidade_vendida(assinaturasMensais.size());
            ordem_compra.setStatus("Aguardando Confirmação do Fornecedor");
            ordem_compra.setData(data);

            OrdemCompraDAO ordem_compra_dao = new OrdemCompraDAO();
            System.out.println("id:" + (java.sql.Date) ordem_compra.getData());
//Descomentar para habilitar ordemm de compra
//           ordem_compra_dao.cadastraOrdemCompra(ordem_compra);
            EmailSender emailsender = new EmailSender();

//           ======== Envio de Email comentado para testes================================
        String assunto= "Relatorio de Ordem de Compra";
         String mensagem = "Olá "+fornecedor.getNome()+ ", segue definição do relatório de compras:  \n\n"
                 + "Numéro de Caixas: "+ordem_compra.getQuantidade_vendida()+"\n"
                 + "Itens de Cada caixa:"+ordem_compra.getEstilo().getConteudo()+"\n"
                 + "Data do Pedido: "+ordem_compra.getData()+"\n"
                 + " \n\n Att, \n\nEquipe Lelegance. ";
         
//          emailsender.EnviarEmail(fornecedor.getEmail(),mensagem,assunto);
//===========================================================================================
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JobOrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JobOrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}