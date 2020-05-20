/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.PerfilDeAcesso;
import model.Pessoa;
import model.Usuario;
import model.UsuarioDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ConectaBanco;
import util.Formatar;

/**
 *
 * @author Usuario
 */
public class UsuarioCrudTest {

    @Test
    public void UsuarioCrudTest() throws SQLException, NoSuchAlgorithmException {
        Connection conexao = null;
        PreparedStatement pstmt = null;



        String CADASTRA_NOVA_PESSOA = "INSERT INTO pessoa(nome) VALUES (?);";

        String CADASTRA_NOVO_USUARIO = "INSERT INTO usuario (email,senha,perfil,pessoa) VALUES (?,?,?,?)";
        conexao = ConectaBanco.getConexao();
        conexao.setAutoCommit(false);
        pstmt = conexao.prepareStatement(CADASTRA_NOVA_PESSOA, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, "UsuarioTeste");
        pstmt.execute();

        ResultSet key = pstmt.getGeneratedKeys();
        key.next();

        pstmt = conexao.prepareStatement(CADASTRA_NOVO_USUARIO);
        pstmt.setString(1, "UsuarioEmail");
        pstmt.setString(2, "UsuarioSenha");
        pstmt.setString(3, PerfilDeAcesso.CLIENTE.toString());
        pstmt.setInt(4, key.getInt(1));
        pstmt.execute();

        String AUTENTICA_USUARIO = "SELECT * FROM usuario WHERE email=? AND senha=?";
        String AUTENTICA_PESSOA = "SELECT * FROM pessoa WHERE nome=?";

        ResultSet rsUsuario = null;

        pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);
        pstmt.setString(1, "UsuarioEmail");
        pstmt.setString(2, "UsuarioSenha");
        rsUsuario = pstmt.executeQuery();
        if (rsUsuario.next()) {
            Usuario usuarioAutenticado = new Usuario();
            usuarioAutenticado.setEmail(rsUsuario.getString("email"));
            usuarioAutenticado.setSenha(rsUsuario.getString("senha"));
            usuarioAutenticado.setId(rsUsuario.getInt("id"));
            usuarioAutenticado.setPerfil(PerfilDeAcesso.valueOf(rsUsuario.getString("perfil")));

            assertEquals("UsuarioEmail", usuarioAutenticado.getEmail());
            assertEquals("UsuarioSenha", usuarioAutenticado.getSenha());

        }

        pstmt = conexao.prepareStatement(AUTENTICA_PESSOA);
        pstmt.setString(1, "UsuarioTeste");
        rsUsuario = pstmt.executeQuery();
        if (rsUsuario.next()) {
            Pessoa pessoaAutenticada = new Usuario();
            pessoaAutenticada.setNome(rsUsuario.getString("nome"));
            
            assertEquals("UsuarioTeste", pessoaAutenticada.getNome());
        }



        conexao.rollback();

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
