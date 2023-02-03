<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% if(session.getAttribute("nombreUsuario") == null){ %>
            <p>Sesión invalida, vuelva a loguearse</p>
            <a href="login.html">Ir al Login</a>
        <% }else{ %>
            <div style="text-align: right; margin: 40px;">
                Hola <%= session.getAttribute("nombreUsuario") %>
                <br/>
                <%= new Date() %>
            </div>
        
        
            <h1>Hola Mundo JSP</h1>
        <% } %>
        
    </body>
</html>
