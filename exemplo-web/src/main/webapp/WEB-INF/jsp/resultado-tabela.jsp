<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Resultado em tabela</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Data nascimento</th>
                        <th>E-mail</th>
                        <th>Telefone</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="d">
                        <tr>
                            <td><c:out value="${d.id}" /></td>
                            <td><c:out value="${d.nome}" /></td>
                            <td><c:out value="${d.dataNascimento}" /></td>
                            <td><c:out value="${d.email}" /></td>
                            <td><c:out value="${d.telefone}" /></td>
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <hr />
        <div>
            <h1>Resultado em lista</h1>
            <ul>
                <c:forEach items="${lista}" var="d">
                    <li>
                        <h2><c:out value="${d.nome}" /></h2>
                        <p><c:out value="${d.dataNascimento}" /></p>
                        <p><c:out value="${d.email}" /></p>
                        <p><c:out value="${d.telefone}" /></p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
