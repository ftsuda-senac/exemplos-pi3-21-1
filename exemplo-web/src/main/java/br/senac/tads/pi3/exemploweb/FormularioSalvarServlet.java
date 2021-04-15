/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FormularioSalvarServlet", urlPatterns = {"/formulario-salvar"})
public class FormularioSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");
        String senhaRepetida = request.getParameter("senhaRepetida");
        String numeroStr = request.getParameter("numero");
        String alturaStr = request.getParameter("altura");
        String pesoStr = request.getParameter("peso");
        String generoStr = request.getParameter("genero");
        String[] interesses = request.getParameterValues("interesses");
        
        DadosPessoais dados = new DadosPessoais(Integer.parseInt(idStr), nome, descricao,
            LocalDate.parse(dataNascimentoStr), email, telefone,
            senha, senhaRepetida, Integer.parseInt(numeroStr),
            new BigDecimal(alturaStr), new BigDecimal(pesoStr), Integer.parseInt(generoStr),
            Arrays.asList(interesses));
        
        request.setAttribute("dados", dados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
        dispatcher.forward(request, response);
        
        // DESCOMENTAR CÓDIGO ABAIXO PARA VER EXEMPLO DE TABELA/LISTA
        /*
        DadosPessoais dados2 = new DadosPessoais(333, "Ciclano", "teste",
            LocalDate.parse("2000-01-20"), "ciclano@teste.com.br", "(11) 99991-1122",
            "abcd1234", "abcd1234", 25,
            new BigDecimal("1.81"), new BigDecimal("90.2"), 1,
            Arrays.asList("Tecnologia"));
        DadosPessoais dados3 = new DadosPessoais(987, "Beltrana", "teste",
            LocalDate.parse("1999-04-29"), "beltrana@teste.com.br", "(11) 99992-2233",
            "abcd1234", "abcd1234", 54,
            new BigDecimal("1.75"), new BigDecimal("87.2"), 0,
            Arrays.asList("Viagens"));
        
        List<DadosPessoais> listaPessoas = Arrays.asList(dados, dados2, dados3);

        request.setAttribute("lista", listaPessoas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado-tabela.jsp");
        dispatcher.forward(request, response);
    */
    }

}
