/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ftsuda
 */
public class PessoaDao {
    
    private final ConnectionUtils connectionUtils = new ConnectionUtils();
    
    public List<Pessoa> listar() {
        String sql = "SELECT id, nome, data_nascimento, data_cadastro FROM pessoa";
        List<Pessoa> resultados = new ArrayList<>();
        try (
            Connection conn = connectionUtils.obterConexaoBD();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                Pessoa p = new Pessoa();
                p.setId(rst.getInt("id"));
                p.setNome(rst.getString("nome"));
                p.setDataNascimento(rst.getDate("data_nascimento").toLocalDate());
                p.setDataCadastro(rst.getTimestamp("data_cadastro").toLocalDateTime());
                resultados.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro na execução");
            ex.printStackTrace();
        }
        return resultados;
    }
    
    // USA STATEMENT => FACILITA SQL INJECTION
    public void incluirErrado(Pessoa p) {
        String sql = "INSERT INTO pessoa (data_nascimento, data_cadastro, nome) VALUES " +
                "('" + p.getDataNascimento().toString() + 
                "', '" + p.getDataCadastro().toString() + 
                "', '" + p.getNome() + "')";
        try (Connection conn = connectionUtils.obterConexaoBD();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Erro na execução");
            ex.printStackTrace();
        }
    }
    
    public void incluir(Pessoa p) {
        String sql = "INSERT INTO pessoa (nome, data_nascimento, data_cadastro) VALUES (?, ?, ?)";
        try (Connection conn = connectionUtils.obterConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(p.getDataNascimento()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(p.getDataCadastro()));
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro na execução");
            ex.printStackTrace();
        }
    }
    
    public void incluirComTransacao(Pessoa p) {
        String sql = "INSERT INTO pessoa (nome, data_nascimento, data_cadastro) VALUES (?, ?, ?)";
        
        try (Connection conn = connectionUtils.obterConexaoBD()){
            // DESLIGAR AUTO-COMMIT
            conn.setAutoCommit(false);
            
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS NA CHAMADA DO prepareStatement
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, p.getNome());
                stmt.setDate(2, java.sql.Date.valueOf(p.getDataNascimento()));
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(p.getDataCadastro()));
                
                stmt.executeUpdate();
                
                try (ResultSet rst = stmt.getGeneratedKeys()) {
                    if (rst.next()) {
                        int idGerado = rst.getInt(1);
                        // USAR ID GERADO PARA SALVAR OUTRAS INFORMAÇÕES EM OUTRAS TABELAS
                        System.out.println("ID gerado: " + idGerado);
                    }
                }
                // O commit() EFETIVA TODAS AS OPERAÇÕES REALIZADAS NO BD
                conn.commit();
            } catch(SQLException ex) {
                // CASO OCORRA ERRO, O rollback() DESFAZ AS OPERAÇÕES ANTERIORES
                conn.rollback();
                throw ex;
            }  
        } catch (SQLException ex) {
            System.err.println("Erro na execução");
            ex.printStackTrace();
        }
        
    }
    
}
