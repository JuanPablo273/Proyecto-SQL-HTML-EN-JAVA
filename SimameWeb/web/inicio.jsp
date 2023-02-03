<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script type="text/javascript">
            function accion(valor){
                
                document.getElementById("oculto").value = valor;
            }
            
        </script>
        
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
        
        
            <h1>Bienvenido al Sistema de Simame Proyecto !!!</h1>
            
            <form action="ServletMenu" method="get">
                
                <input type="submit" value="Mantenimiento de Doctores" onclick="accion(1)"/>
                <input type="submit" value="Mantenimiento de Pacientes" onclick="accion(2)"/>
                <input type="submit" value="Mantenimiento de Salon" onclick="accion(3)"/>
                <input type="submit" value="Cerrar sesion" onclick="accion(4)"/>
                
                <input type="hidden" id="oculto" name="boton"/>
                
            </form>
            
            
        <% } %>
        
    </body>
</html>
