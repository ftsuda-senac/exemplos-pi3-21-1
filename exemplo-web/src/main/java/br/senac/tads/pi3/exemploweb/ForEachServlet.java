/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ftsuda
 */
@WebServlet(name = "ForEachServlet", urlPatterns = {"/exemplo-tabela-lista"})
public class ForEachServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DadosPessoais dados1 = new DadosPessoais(123, "Fulano da Silva", "teste",
            LocalDate.parse("1997-10-03"), "fulano@teste.com.br", "(11) 99999-1234",
            "abcd1234", "abcd1234", 87,
            new BigDecimal("1.78"), new BigDecimal("87.4"), 1,
            Arrays.asList("Gastronomia"));
        
        DadosPessoais dados2 = new DadosPessoais(333, "Ciclano de Souza", "teste",
            LocalDate.parse("2000-01-20"), "ciclano@teste.com.br", "(11) 99991-1122",
            "abcd1234", "abcd1234", 25,
            new BigDecimal("1.81"), new BigDecimal("90.2"), 1,
            Arrays.asList("Tecnologia"));
        
        DadosPessoais dados3 = new DadosPessoais(987, "Beltrana dos Santos", "teste",
            LocalDate.parse("1999-04-29"), "beltrana@teste.com.br", "(11) 99992-2233",
            "abcd1234", "abcd1234", 54,
            new BigDecimal("1.75"), new BigDecimal("77.2"), 0,
            Arrays.asList("Viagens"));
        
        List<DadosPessoais> listaPessoas = Arrays.asList(dados1, dados2, dados3);

        request.setAttribute("lista", listaPessoas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado-tabela.jsp");
        dispatcher.forward(request, response);
    }


}
