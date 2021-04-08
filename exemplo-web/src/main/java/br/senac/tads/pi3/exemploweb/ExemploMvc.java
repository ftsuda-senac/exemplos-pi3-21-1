/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ExemploMvc", urlPatterns = {"/exemplo-mvc"})
public class ExemploMvc extends HttpServlet {
    
    private CalcularIdade calcIdade = new CalcularIdade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String nomeRecebido = request.getParameter("nome");
        String dataRecebida = request.getParameter("data");
        long idade = calcIdade.calcular(dataRecebida);
        
        request.setAttribute("nomeAttr", nomeRecebido);
        request.setAttribute("dtNascAttr", dataRecebida);
        request.setAttribute("idadeAttr", idade);
        request.setAttribute("metodo", "GET");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mvc-resultado.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String nomeRecebido = request.getParameter("nome");
        String dataRecebida = request.getParameter("data");
        long idade = calcIdade.calcular(dataRecebida);
        
        request.setAttribute("nomeAttr", nomeRecebido);
        request.setAttribute("dtNascAttr", dataRecebida);
        request.setAttribute("idadeAttr", idade);
        request.setAttribute("metodo", "POST");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mvc-resultado.jsp");
        dispatcher.forward(request, response);
    }

}
