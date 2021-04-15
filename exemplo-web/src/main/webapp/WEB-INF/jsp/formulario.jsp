<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Exemplo Formulario</h1>
        <form action="${pageContext.request.contextPath}/formulario-salvar" method="post">
            <input type="hidden" name="id" value="123" />
            <div>
                <label>Nome</label>
                <input type="text" name="nome" placeholder="Preencha o nome completo" />
            </div>
            <div>
                <label>Descrição</label>
                <textarea name="descricao" placeholder="Preencha um texto descritivo sobre sua pessoa"></textarea>
            </div>
            <div>
                <label>Data de nascimento</label>
                <input type="date" name="dataNascimento" />
            </div>
            <div>
                <label>E-mail</label>
                <input type="email" name="email" />
            </div>
            <div>
                <label>Telefone</label>
                <input type="text" name="telefone" />
            </div>
            <div>
                <label>Senha</label>
                <input type="password" name="senha" />
            </div>
            <div>
                <label>Repetir senha</label>
                <input type="password" name="senhaRepetida" />
            </div>
            <div>
                <label>Número de 1 a 99</label>
                <input type="number" name="numero" />
            </div>
            <div>
                <label>Altura</label>
                <input type="number" name="altura" step="0.01" />
            </div>
            <div>
                <label>Peso</label>
                <input type="number" name="peso" step="0.1" />
            </div>
            <fieldset>
                <legend>Gênero</legend>
                <div>
                    <input type="radio" name="genero" value="0" id="generoF" />
                    <label for="generoF">Feminino</label>
                </div>
                <div>
                    <input type="radio" name="genero" value="1" id="generoM" />
                    <label for="generoM">Masculino</label>
                </div>
            </fieldset>
            <fieldset>
                <legend>Interesses</legend>
                <c:forEach items="${interesses}" var="interesse">
                    <div>
                        <input type="checkbox" name="interesses" value="${interesse}" id="interesse${interesse}" />
                        <label for="interesse${interesse}"><c:out value="${interesse}" /></label>
                    </div>
                </c:forEach>
            </fieldset>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
