/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;

/**
 *
 * @author alunocmc
 */
public class EstiloDAO {

    private static final String CARREGAR_ESTILO_DO_MES = "SELECT * FROM public.estilo where status=\'Ativo\'";

    private static final String CARREGAR_TODOS = "SELECT id, nome, descricao, preco,status,conteudo FROM public.estilo";
    private static final String CARREGAR_POR_ID = "SELECT id, nome, descricao, preco,imagem,status,conteudo,funcionario FROM public.estilo where id=?";
    private static final String EXCLUIR_ESTILO = "UPDATE public.estilo SET status=\'Inativo\' where id=?";
    private static final String ATIVAR_ESTILO = "UPDATE public.estilo SET status=\'Ativo\' where id=? AND status=\'Inativo\'";
    private static final String CADASTRA_NOVO_ESTILO = "INSERT INTO public.estilo(nome, descricao, preco, imagem, funcionario, status,conteudo)VALUES ( ?, ?, ?, 'imagemMockada.png', ?, 'Ativo',?);";
    private static final String ALTERAR_NOVO_ESTILO = "UPDATE public.estilo SET nome=?, descricao=?, preco=?, imagem='ImagemMockada.png', funcionario=?,conteudo=? where id=?";

    public List<Estilo> CarregarEstilos() throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_TODOS);
        ResultSet resultado = comando.executeQuery();

        List<Estilo> estilos = new ArrayList();

        while (resultado.next()) {
            Estilo est = new Estilo();
            est.setId(resultado.getInt("id"));
            est.setNome(resultado.getString("nome"));
            est.setDescricao(resultado.getString("descricao"));
            est.setValor(resultado.getDouble("preco"));
            est.setStatus(resultado.getString("status"));
            est.setConteudo(resultado.getString("conteudo"));
            estilos.add(est);
        }
        con.close();
        return estilos;
    }

    //Pega o estilo ativo do mes somente
    public Estilo carregarEstilodoMes() throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_ESTILO_DO_MES);
        ResultSet resultado = comando.executeQuery();

        Estilo estiloMensal = new Estilo();

        while (resultado.next()) {

            estiloMensal.setId(resultado.getInt("id"));
            estiloMensal.setNome(resultado.getString("nome"));
            estiloMensal.setDescricao(resultado.getString("descricao"));
            estiloMensal.setValor(resultado.getDouble("preco"));
            estiloMensal.setStatus(resultado.getString("status"));
            estiloMensal.setStatus(resultado.getString("conteudo"));
        }
        con.close();
        return estiloMensal;
    }

    public Estilo carregarPorId(Estilo estilo) throws SQLException, ClassNotFoundException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_POR_ID);
        comando.setInt(1, estilo.getId());
        ResultSet resultado = comando.executeQuery();

        Estilo est = new Estilo();

        while (resultado.next()) {
            est.setId(resultado.getInt("id"));
            est.setNome(resultado.getString("nome"));
            est.setImagem(resultado.getString("imagem"));
            est.setDescricao(resultado.getString("descricao"));
            est.setValor(resultado.getDouble("preco"));
            est.setStatus(resultado.getString("status"));
            Funcionario f = new Funcionario();
            f.setId(resultado.getInt("funcionario"));
            est.setFuncionario(f);
            est.setConteudo(resultado.getString("conteudo"));
        }
        con.close();
        return est;
    }

    public void excluirEstilo(Estilo estilo) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(EXCLUIR_ESTILO);
        comando.setInt(1, estilo.getId());
        comando.execute();

        con.close();

    }

    public void ativarBox(Estilo estilo) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(ATIVAR_ESTILO);
        comando.setInt(1, estilo.getId());
        comando.execute();

        con.close();

    }

    public void cadastraNovoEstilo(Estilo estilo, int IdFuncionarioCadastro) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
//        "INSERT INTO public.estilo(nome, descricao, preco, imagem, funcionario, status)VALUES ( ?, ?, ?, ?, ?, 'Ativo');"

        PreparedStatement comando = con.prepareStatement(CADASTRA_NOVO_ESTILO);
        comando.setString(1, estilo.getNome());
        comando.setString(2, estilo.getDescricao());
        comando.setDouble(3, estilo.getValor());
//      comando.setString(4,estilo.getImagem()); Ignorado Inicialmente/16/04/2020
        comando.setInt(4, IdFuncionarioCadastro);
        comando.setString(5, estilo.getConteudo());
        comando.execute();
        con.close();

    }

    public void AlterarNovoEstilo(Estilo estilo, int IdFuncionarioCadastro) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(ALTERAR_NOVO_ESTILO);
        comando.setString(1, estilo.getNome());
        comando.setString(2, estilo.getDescricao());
        comando.setDouble(3, estilo.getValor());
////      comando.setString(4,estilo.getImagem()); Ignorado Inicialmente/16/04/2020
        comando.setInt(4, IdFuncionarioCadastro);
        comando.setString(5, estilo.getConteudo());
        comando.setInt(6, estilo.getId());
        comando.execute();
        con.close();

    }
}
