/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploauth.filter;

import br.senac.tads.pi3.exemploauth.usuario.UsuarioSistema;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ftsuda
 */
@WebFilter(filterName = "AutorizacaoFilter",
        urlPatterns = { "/protegido/*" },
        servletNames = { "HomeServlet" })
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        // CAST para objetos tipo HttpServlet*
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1) Verifica se usuario já está logado
        HttpSession sessao = httpRequest.getSession();
        if (sessao.getAttribute("usuario") == null) {
            // Usuário não está logado -> Redireciona para tela de Login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
        // Usuário logado -> Verificar se ele possui papel para acessar funcionalidade
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");
        
        if (verificarAcesso(usuario, httpRequest, httpResponse)) {
            // Requisição segue fluxo normal para o Servlet
            chain.doFilter(request, response);
        } else {
            // Usuário não tem autorização para entrar na funcionalidade
            // Mostrar erro de acesso não autorizado
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/erro-nao-autorizado.jsp");
        }
    }
    
    private boolean verificarAcesso(UsuarioSistema usuario, 
            HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        String urlAcessada = httpRequest.getRequestURI();
//        String queryString = httpRequest.getQueryString();
//        if (queryString.contains("action=incluir")) {
//            
//        }
        if (urlAcessada.endsWith("/home")) {
            return true;
        } else if (urlAcessada.endsWith("/protegido/peao-page") &&
                usuario.verificarPapel("PEAO")) {
            return true;
        } else if (urlAcessada.endsWith("/protegido/fodon-page") &&
                usuario.verificarPapel("FODON")) {
            return true;
        } else if (urlAcessada.endsWith("/protegido/god-page") &&
                usuario.verificarPapel("GOD")) {
            return true;
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NÃO FAZ NADA NESTE EXEMPLO
    }
    
    @Override
    public void destroy() {
        // NÃO FAZ NADA NESTE EXEMPLO
    }
    
}
