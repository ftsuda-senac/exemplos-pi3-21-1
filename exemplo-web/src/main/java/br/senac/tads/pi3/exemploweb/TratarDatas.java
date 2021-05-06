/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ftsuda
 */
/*
-- SCRIPT PARA CRIAR TABELA NO MYSQL
CREATE DATABASE exemplodatas;
use exemplodatas;

CREATE TABLE DATAS (
  ID                     INT  NOT NULL AUTO_INCREMENT,
  DATA_TEXT              DATE NOT NULL,
  DATA_TEXT_JQUERY_UI    DATE NOT NULL,
  DATA_DATE              DATE NOT NULL,
  DATA_DATETIME          TIMESTAMP     NOT NULL,
  CONSTRAINT PK_DATAS PRIMARY KEY (ID)
);

# CONTEÚDO DO ARQUIVO conexao-bd-datas.properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/exemplodatas?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=America/Sao_Paulo
user=[USUARIO-BANCO]
password=[SENHA-BANCO]
*/
@WebServlet(name = "TratarDatas", urlPatterns = {"/tratar-datas"})
public class TratarDatas extends HttpServlet {
    
    public Connection obterConexaoBD() throws SQLException {
        // 1) Abrir o arquivo de propriedades com informações de conexão
        // ALTERAR O CAMINHO E O NOME DO ARQUIVO CASO NECESSÁRIO
        try  (FileReader propReader = new FileReader("C:/senac/conexao-bd-datas.properties")) {
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
            throw new SQLException("Arquivo conexao-bd-datas.properties não encontrado", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/tratar-datas.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // RECUPERA AS DATAS INFORMADAS NO FORMATO DE STRINGS
        String dataText = request.getParameter("dataText");
        String dataTextJqueryUI = request.getParameter("dataTextJqueryUI");
        String dataDate = request.getParameter("dataDate");
        String dataDateTime = request.getParameter("dataDateTime");
        
        System.out.println("******** TEXTOS RECEBIDOS NO SERVLET");
        System.out.println("dataText:         " + dataText);
        System.out.println("dataTextJqueryUI: " + dataTextJqueryUI);
        System.out.println("dataDate:         " + dataDate);
        System.out.println("dataDateTime:     " + dataDateTime);
        
        
        // CONVERSAO DAS STRINGS PARA OBJETOS LocalDate e LocalDateTime
        LocalDate dataTextConvertido = LocalDate.parse(dataText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataTextJqueryUIConvertido = LocalDate.parse(dataTextJqueryUI, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataDateConvertido = LocalDate.parse(dataDate);
        LocalDateTime dataDateTimeConvertido = LocalDateTime.parse(dataDateTime);
        
        System.out.println("******** DATAS APÓS CONVERSÃO");
        System.out.println("dataText:         " + dataTextConvertido.toString());
        System.out.println("dataTextJqueryUI: " + dataTextJqueryUIConvertido.toString());
        System.out.println("dataDate:         " + dataDateConvertido.toString());
        System.out.println("dataDateTime:     " + dataDateTimeConvertido.toString());
        

        // SALVA NO BANCO DE DADOS E RECUPERA ID GERADO
        // LEMBRAR QUE AS DATAS DEVEM SER SALVAS ATRAVÉS DOS OBJETOS DATE/TIME/TIMESTAMP
        // QUE ESTÃO NO PACOTE java.sql (NÃO CONFUNDIR COM O DATE DO java.util)
        int idGerado = 0;
        try (Connection conn = obterConexaoBD();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO DATAS (DATA_TEXT,DATA_TEXT_JQUERY_UI, DATA_DATE, DATA_DATETIME) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(dataTextConvertido));
            stmt.setDate(2, java.sql.Date.valueOf(dataTextJqueryUIConvertido));
            stmt.setDate(3, java.sql.Date.valueOf(dataDateConvertido));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataDateTimeConvertido));
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        // CONSULTA DATAS GRAVADAS NO BANCO PARA MOSTRAR NA TELA
        HttpSession sessao = request.getSession();
        try (Connection conn = obterConexaoBD();
            PreparedStatement stmt = conn.prepareStatement("SELECT ID, DATA_TEXT, DATA_TEXT_JQUERY_UI, DATA_DATE, DATA_DATETIME FROM DATAS WHERE ID = ?")) {
            stmt.setInt(1, idGerado);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate dataTextBd = rs.getDate("DATA_TEXT").toLocalDate();
                    LocalDate dataTextJqueryUIBd = rs.getDate("DATA_TEXT_JQUERY_UI").toLocalDate();
                    LocalDate dataDateBd = rs.getDate("DATA_DATE").toLocalDate();
                    LocalDateTime dataDateTimeBd = rs.getTimestamp("DATA_DATETIME").toLocalDateTime();
                    
                    sessao.setAttribute("msg", "Datas incluidas com sucesso");
                    sessao.setAttribute("idGerado", idGerado);
                    sessao.setAttribute("dataText", dataTextBd);
                    sessao.setAttribute("dataTextJqueryUI", dataTextJqueryUIBd);
                    sessao.setAttribute("dataDate", dataDateBd);
                    sessao.setAttribute("dataDateTime", dataDateTimeBd);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/tratar-datas");
    }


}
