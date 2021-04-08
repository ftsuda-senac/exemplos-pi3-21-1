<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Exemplo Formulario</h1>
        <form action="exemplo-mvc" method="post">
            <div>
                <label>Nome:</label><input type="text" name="nome" />
            </div>
            <div>
                <label>Data Nascimento</label><input type="date" name="data" />
            </div>
            <div>
                <label>Senha</label><input type="password" name="senha" />
            </div>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
