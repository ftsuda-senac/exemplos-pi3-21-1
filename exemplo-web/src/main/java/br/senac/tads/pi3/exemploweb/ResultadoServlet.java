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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ftsuda
 */
@WebServlet(name = "ResultadoServlet", urlPatterns = {"/resultado"})
public class ResultadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("dados") != null) {
            DadosPessoais dados = (DadosPessoais) sessao.getAttribute("dados");
            sessao.removeAttribute("dados");
            
            request.setAttribute("dados", dados);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
            dispatcher.forward(request, response);
        } else {
            // TODO: IMPLEMENTAR LÓGICA DE ERRO
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
