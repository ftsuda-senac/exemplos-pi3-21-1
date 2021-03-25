/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExemploServlet", urlPatterns = "/exemplo-servlet")
public class ExemploServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
        String valorRecebido = request.getParameter("nome");
        String dataRecebida = request.getParameter("data");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exemplo Servlet</title>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exemplo Servlet</h1>");
            out.println("<h2>Nome: " + valorRecebido + "</h2>");
            if (dataRecebida != null) {
                out.println("<h2>Data nascimento: " + dataRecebida + "</h2>");
                long idade = ChronoUnit.YEARS.between(LocalDate.parse(dataRecebida), LocalDate.now());
                out.println("<h2>Idade: " + idade + "</h2>");
            }
            out.println("<p>Data e hora atual: " + LocalDateTime.now() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
