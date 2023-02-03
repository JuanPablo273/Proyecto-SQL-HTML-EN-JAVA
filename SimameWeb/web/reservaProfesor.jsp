<%@page import="negocio.bo.LibroBo"%>
<%@page import="negocio.clases.Libro"%>
<%@page import="negocio.bo.DoctorBo"%>
<%@page import="java.util.List"%>
<%@page import="negocio.clases.Doctor"%>
<%@page import="java.util.ArrayList"%>
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
                <% 
                 if(request.getAttribute("doctor") == null){
                        request.setAttribute("doctor", 0);
                    }
                 
                   if(request.getAttribute("libro") == null){
                        request.setAttribute("libro", 0);
                    }
                %>
        
            <h1>Reserva de Doctores </h1>
            
            <form>
                <table>
                    <tr>
                        <td>Doctor</td>
                        <td>
                            <select id="cbDoctor" name="doctor">
                                <option value="0">--Seleccione--</option>
                                <% 
                                    Integer teacher = Integer.parseInt(request.getAttribute("doctor")+"");
                                    List<Doctor> listaDoctors = new ArrayList<Doctor>();
                                    DoctorBo doctorBo = new DoctorBo();
                                    listaDoctors = doctorBo.consultarTodos();
                                    for (int i = 0; i < listaDoctors.size(); i++) {
                                            Doctor doc = listaDoctors.get(i);
                                            if(doc.getCedula().equals(teacher)){
                                   %>
                                             
                                              <option value="<%= doc.getCedula() %>" selected>
                                                <%= doc.getCedula()%> - <%= doc.getNombre()%>
                                            </option>
                                 <%
                                            }else{
                                %>
                                
                                        <option value="<%= doc.getCedula() %>">
                                            <%= doc.getCedula()%> - <%= doc.getNombre()%>
                                        </option>
                                
                                <%
                                        }
                                    }
                                %>
                                
                            </select>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>Libro</td>
                        <td>
                            <select id="cbLibro" name="libro">
                                <option value="0">--Seleccione--</option>
                                <% 
                                    Integer book = Integer.parseInt(request.getAttribute("libro")+"");
                                    List<Libro> listaLibros = new ArrayList<Libro>();
                                    LibroBo libroBo = new LibroBo();
                                    listaLibros = libroBo.consultaTodos();
                                    for (int i = 0; i < listaLibros.size(); i++) {
                                            Libro prof = listaLibros.get(i);
                                            if(prof.getCodigo().equals(book)){
                                   %>
                                             
                                              <option value="<%= prof.getCodigo() %>" selected>
                                                <%= prof.getCodigo()%> - <%= prof.getNombre()%>
                                            </option>
                                 <%
                                            }else{
                                %>
                                
                                        <option value="<%= prof.getCodigo() %>">
                                            <%= prof.getCodigo()%> - <%= prof.getNombre()%>
                                        </option>
                                
                                <%
                                        }
                                    }
                                %>
                                
                            </select>
                            
                        </td>
                    </tr>
                </table>
            </form>
            
            
            
            
        <% } %>
        
    </body>
</html>
