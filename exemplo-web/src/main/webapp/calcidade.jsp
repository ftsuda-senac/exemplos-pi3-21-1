<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculo Idade</title>
    </head>
    <body>
        <h1>Calculo Idade</h1>
        <%
            String nome = request.getParameter("nome");
            String dataNascimento = request.getParameter("data");
            long idade = -1;
            if (dataNascimento != null) {
                idade = ChronoUnit.YEARS.between(LocalDate.parse(dataNascimento), LocalDate.now());
            }
        %>
        <h1>Nome: <%= nome %></h1>
        <h2>Data de nascimento: <%= dataNascimento %></h2>
        <% if (idade > 0) { %>
        <h2>Idade: <%= idade %>
        <% } %>
    </body>
</html>
