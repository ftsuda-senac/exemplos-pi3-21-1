/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ftsuda
 */
@WebServlet(name = "ReceberDadosArray", urlPatterns = {"/receber-dados-array"})
public class ReceberDadosArray extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // String[] ids = request.getParameterValues("id");
        // String[] nomes = request.getParameterValues("nome");
        // String[] numeros = request.getParameterValues("numero");
        String qtdItensStr = request.getParameter("qtdItens");
        int qtdItens = Integer.parseInt(qtdItensStr);
        
        for (int idx = 0; idx < qtdItens; idx++) {
            String id = request.getParameter("id[" + idx + "]" );
            String nome = request.getParameter("nome[" + idx + "]");
            String numero = request.getParameter("numero[" + idx + "]");
            
            System.out.println("Dados recebidos ID " + id + "- Nome: " + nome + ", Número: " + numero);
            
            DadosBasicos dados = new DadosBasicos();
            dados.setId(Integer.parseInt(id));
            dados.setNome(nome);
            dados.setNumero(Integer.parseInt(numero));
        }
        response.sendRedirect("envio-dados-array.jsp");
    }

}
