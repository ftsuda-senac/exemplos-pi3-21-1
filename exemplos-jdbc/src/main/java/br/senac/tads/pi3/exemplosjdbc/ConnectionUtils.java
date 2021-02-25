/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosjdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author ftsuda
 */
public class ConnectionUtils {

    public Connection obterConexaoBD() throws SQLException {
        // 1) Abrir o arquivo de propriedades com informações de conexão
        try  (FileReader propReader = new FileReader("C:/senac/conexao-bd.properties")) {
            Properties bdProps = new Properties();
            bdProps.load(propReader);
            
            // 2) Declarar o driver JDBC
            try {
                Class.forName(bdProps.getProperty("driver"));
            } catch (ClassNotFoundException ex) {
                throw new SQLException("Driver do banco de dados não encontrado", ex);
            }
            
            // 3) Abrir conexão usando as propriedades configuradas no arquivo
            Connection conn = DriverManager.getConnection(bdProps.getProperty("url"), bdProps);
            return conn;

        } catch (IOException ex) {
            throw new SQLException("Arquivo conexao-bd.properties não encontrado", ex);
        }
    }

}
