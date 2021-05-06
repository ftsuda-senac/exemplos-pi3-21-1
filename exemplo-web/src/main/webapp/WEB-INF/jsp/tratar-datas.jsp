<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <style>
            .msg-sucesso {
                border: 3px solid green;
                background-color:  greenyellow;
                padding: 5px 20px;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Exemplo tratamento de datas</h1>
        <c:if test="${not empty sessionScope.msg}">
            <div class="msg-sucesso">
                <h2><c:out value="${sessionScope.msg}" /> - ID: <c:out value="${sessionScope.idGerado}" /></h2>
                <h3>Valores "puros" (ISO 8601)</h3>
                <p>dataText: <c:out value="${sessionScope.dataText}" /></p>
                <p>dataTextJqueryUI: <c:out value="${sessionScope.dataTextJqueryUI}" /></p>
                <p>dataDate: <c:out value="${sessionScope.dataDate}" /></p>
                <p>dataDateTime <c:out value="${sessionScope.dataDateTime}" /></p>
                <hr />
                <h3>Valores formatados</h3>
                
                <fmt:parseDate value="${sessionScope.dataText}" type="date" pattern="yyyy-MM-dd" var="dataTextParsed" />
                <p>dataText: <fmt:formatDate value="${dataTextParsed}" pattern="dd/MM/yyyy" /></p>
                
                <fmt:parseDate value="${sessionScope.dataTextJqueryUI}" type="date" pattern="yyyy-MM-dd" var="dataTextJqueryUIParsed" />
                <p>dataTextJqueryUI: <fmt:formatDate value="${dataTextJqueryUIParsed}" pattern="dd/MM/yyyy" /></p>
                
                <fmt:parseDate value="${sessionScope.dataDate}" type="date" pattern="yyyy-MM-dd" var="dataDateParsed" />
                <p>dataDate: <fmt:formatDate value="${dataDateParsed}" pattern="dd/MM/yyyy" /></p>
                
                <fmt:parseDate value="${sessionScope.dataDateTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="dataDateTimeParsed" />
                <p>dataDateTime <fmt:formatDate value="${dataDateTimeParsed}" pattern="dd/MM/yyyy HH:mm" /></p>
            </div>
            
        </c:if>
        
        
        <form method="post" action="${pageContext.request.contextPath}/tratar-datas">
            <div>
                <label>Data com input type="text"</label>
                <input type="text" name="dataText" placeholder="dd/MM/yyyy" maxlength="10"/>
            </div>
            <div>
                <label>Data com input type="text" + jQuery UI datepicker</label>
                <input type="text" name="dataTextJqueryUI" id="datepicker" placeholder="dd/MM/yyyy" />
            </div>
            <div>
                <label>Data com input type="date"</label>
                <input type="date" name="dataDate" />
            </div>
            <div>
                <label>Data com input type="datetime-local"</label>
                <input type="datetime-local" name="dataDateTime" />
            </div>
            <button type="submit">Enviar</button>
        </form>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        <script>
            $(function () {
                $('#datepicker').datepicker( { "dateFormat" : "dd/mm/yy"} );
            })
        </script>
    </body>
</html>
