<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculo Idade</title>
        <style>
            body {
                background-color: aqua;
            }
        </style>
    </head>
    <body>
        <h1>Calculo Idade - Servlet + JSP (MVC)</h1>
        <h1>Nome: <c:out value="${nomeAttr}" /></h1>
        <h2>Data de nascimento: <c:out value="${dtNascAttr}" /></h2>
        <c:if test="${idadeAttr > 0}">
            <h2>Idade: <c:out value="${idadeAttr}" /></h2>
        </c:if>
        <p>Processado método HTTP <c:out value="${metodo}"/></p>
    </body>
</html>
