<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculo Idade</title>
    </head>
    <body>
        <h1>Calculo Idade - JSTL + EL</h1>
        <%
            String nome = request.getParameter("nome");
            String dataNascimento = request.getParameter("data");
            long idade = -1;
            if (dataNascimento != null) {
                idade = ChronoUnit.YEARS.between(LocalDate.parse(dataNascimento), LocalDate.now());
            }
            request.setAttribute("nomeAttr", nome);
            request.setAttribute("dtNascAttr", dataNascimento);
            request.setAttribute("idadeAttr", idade);
        %>
        <h1>Nome: <c:out value="${nomeAttr}" /></h1>
        <h2>Data de nascimento: <c:out value="${dtNascAttr}" /></h2>
        <c:if test="${idadeAttr > 0}">
            <h2>Idade: <c:out value="${idadeAttr}" /></h2>
        </c:if>
    </body>
</html>
