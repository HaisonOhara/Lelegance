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
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;

/**
 *
 * @author Usuario
 */
public class FornecedorDAO {

    private static final String CARREGAR_FORNECEDORES = "SELECT * FROM public.fornecedor ORDER BY nome";
    private static final String CARREGAR_FORNECEDOR = "SELECT * FROM public.fornecedor WHERE id=?;";
    private static final String CARREGAR_FORNECEDOR_ATIVO = "SELECT id, nome, email, status\n"
            + "  FROM public.fornecedor WHERE status = 'Ativo';";
    private static final String ALTERAR_FORNECEDOR = "UPDATE public.fornecedor SET nome=?, email=? where id=?";
    private static final String CADASTRAR_FORNECEDOR = "INSERT INTO public.fornecedor(\n"
            + "     nome, email, status)\n"
            + "    VALUES (?, ?, ?);";

    private static final String ATIVAR_FORNECEDOR = "UPDATE public.fornecedor\n"
            + " SET status='Ativo'\n"
            + " WHERE id=?;\n"
            + "\n"
            + "\n"
            + "UPDATE public.fornecedor\n"
            + "   SET status='Inativo'\n"
            + " WHERE id!=?;";

    public List<Fornecedor> CarregarFornecedores() throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_FORNECEDORES);
        ResultSet resultado = comando.executeQuery();

        List<Fornecedor> fornecedores = new ArrayList();

        while (resultado.next()) {

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(resultado.getInt("id"));
            fornecedor.setEmail(resultado.getString("email"));
            fornecedor.setNome(resultado.getString("nome"));
            fornecedor.setStatus(resultado.getString("status"));

            fornecedores.add(fornecedor);
        }
        con.close();
        return fornecedores;
    }

    public Fornecedor carregarFornecedor(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_FORNECEDOR);
        comando.setInt(1, fornecedor.getId());
        ResultSet resultado = comando.executeQuery();

        Fornecedor forn = new Fornecedor();

        while (resultado.next()) {
            forn.setId(resultado.getInt("id"));
            forn.setNome(resultado.getString("nome"));
            forn.setEmail(resultado.getString("email"));
        }
        con.close();
        return forn;
    }

    public Fornecedor carregarFornecedorAtivo() throws SQLException, ClassNotFoundException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_FORNECEDOR_ATIVO);
        ResultSet resultado = comando.executeQuery();

        Fornecedor forn = new Fornecedor();

        while (resultado.next()) {
            forn.setId(resultado.getInt("id"));
            forn.setNome(resultado.getString("nome"));
            forn.setEmail(resultado.getString("email"));
            forn.setStatus(resultado.getString("status"));
        }
        con.close();
        return forn;
    }

    public void CadastrarFornecedor(Fornecedor fornecedor) throws SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CADASTRAR_FORNECEDOR);
        comando.setString(1, fornecedor.getNome());
        comando.setString(2, fornecedor.getEmail());
        comando.setString(3, fornecedor.getStatus());

        comando.execute();

    }

    public void AtivarFornecedor(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(ATIVAR_FORNECEDOR);
        comando.setInt(1, fornecedor.getId());
        comando.setInt(2, fornecedor.getId());
        comando.execute();
        con.close();
    }

    public void AlterarFornecedor(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(ALTERAR_FORNECEDOR);
        comando.setString(1, fornecedor.getNome());
        comando.setString(2, fornecedor.getEmail());
        comando.setInt(3, fornecedor.getId());
        comando.execute();
        con.close();

    }

}
