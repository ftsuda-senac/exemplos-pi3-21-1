<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="8192kb" autoFlush="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Exemplo</h1>
        <form action="${pageContext.request.contextPath}/receber-dados-array" method="post">
            <input type="number" name="qtdItens" value="10" />
            <table>
                <tbody>
                    <c:forEach begin="0" end="9" var="idx">
                        <tr>
                            <td>
                                <input type="hidden" name="id[${idx}]" value="${idx}" />
                                <input type="text" name="nome[${idx}]" />
                            </td>
                            <td><input type="number" name="numero[${idx}]" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
