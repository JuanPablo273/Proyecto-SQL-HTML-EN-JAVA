<%@page import="negocio.bo.PacienteBo"%>
<%@page import="negocio.clases.Paciente"%>
<%@page import="negocio.bo.DoctorBo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="negocio.clases.Doctor"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script type="text/javascript">

            function validarDatos() {
                
                var vd = 1;
                var num_asegurado = document.getElementById("txtAsegurado").value;
                var nombre = document.getElementById("txtNombre").value;
                var direccion = document.getElementById("txtDireccion").value;
                var edad = document.getElementById("txtEdad").value;
                var fecha_nacimiento = document.getElementById("txtFecha_nacimiento").value;
                var email = document.getElementById("txtEmail").value;
                var telefono = document.getElementById("txtTelefono").value;
                var profesion = document.getElementById("txtProfesion").value;

                if (isNaN(num_asegurado)) {
                    alert("No es un numero de asegurado válido");
                    vd = 0;
                } else {
                    if (num_asegurado === "") {
                        alert("Debe ingresar un numero de asegurado");
                        vd = 0;
                    }
                }

                if (nombre === "") {
                    alert("Debe ingresar un nombre");
                    vd = 0;
                }

                if (direccion === "") {
                    alert("Debe ingresar una direccion");
                    vd = 0;
                }

                if (isNaN(edad)) {
                    alert("No es una edad válida");
                    vd = 0;
                } else {
                    if (num_asegurado === "") {
                        alert("Debe ingresar una edad");
                        vd = 0;
                    }
                }


                if (fecha_nacimiento === "") {
                    alert("Debe ingresar una fecha de nacimiento");
                    vd = 0;
                }

                if (email === "") {
                    alert("Debe ingresar un email");
                    vd = 0;
                }

                if (isNaN(telefono)) {
                    alert("No es un numero de telefono válido");
                    vd = 0;
                } else {
                    if (telefono === "") {
                        alert("Debe ingresar un numero de telefono");
                        vd = 0;
                    }
                }

                if (profesion === "") {
                    alert("Debe ingresar una profesion");
                    vd = 0;
                }

                if (vd === 0) {
                    return false;
                } else
                    return true;
            }


            function agregar() {
                if (validarDatos()) {
                    document.getElementById("accion").value = "AGREGAR";
                    return true;
                } else {
                    return false;
                }
            }

            function elegirFila(num_asegurado) {
                alert("selecion");
                document.getElementById("accion").value = "SELECCIONAR";
                document.getElementById("seleccionar").value = num_asegurado;
            }

            function modificar() {
                if (validarDatos()) {
                    document.getElementById("accion").value = "MODIFICAR";
                    return true;
                } else {
                    return false;
                }
            }

            function eliminar() {
                var num_asegurado = document.getElementById("txtAsegurado").value;
                if (isNaN(num_asegurado)) {
                    alert("No es un Num_Asegurado válido");
                    return false;
                } else {
                    if (num_asegurado === "") {
                        alert("Debe ingresar un Num_Asegurado a eliminar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "ELIMINAR";
                        return true;
                    }
                }
            }

            function consultaNum_Asegurado() {
                var num_asegurado = document.getElementById("txtAsegurado").value;
                if (isNaN(num_asegurado)) {
                    alert("No es un Num_Aseguradoválido");
                    return false;
                } else {
                    if (num_asegurado === "") {
                        alert("Debe ingresar un Num_Aseguradoválido a consultar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "CONS-NUM_ASEGURADO";
                        return true;
                    }
                }
            }

            function consultaNombre() {
                var nombre = document.getElementById("txtNombre").value;

                if (nombre === "") {
                    alert("Debe ingresar una nombre a consultar");
                    return false;
                } else {
                    document.getElementById("accion").value = "CONS-NOMBRE";
                    return true;
                }
            }

            function limpiar() {
                document.getElementById("accion").value = "LIMPIAR";
                return true;
            }

            function regresar() {
                document.getElementById("accion").value = "REGRESAR";
                return true;
            }

        </script>
    </head>
    <body>

        <% if (session.getAttribute("nombreUsuario") == null) { %>
        <p>Sesión invalida, vuelva a loguearse</p>
        <a href="login.html">Ir al Login</a>
        <% } else {%>
        <div style="text-align: right; margin: 40px;">
            Hola <%= session.getAttribute("nombreUsuario")%>
            <br/>
            <%= new Date()%>
        </div>

        <%
            if (request.getAttribute("num_asegurado") == null) {
                request.setAttribute("num_asegurado", "");
            }

            if (request.getAttribute("nombre") == null) {
                request.setAttribute("nombre", "");
            }
            if (request.getAttribute("direccion") == null) {
                request.setAttribute("direccion", "");
            }

            if (request.getAttribute("edad") == null) {
                request.setAttribute("edad", "");
            }

            if (request.getAttribute("fecha_nacimiento") == null) {
                request.setAttribute("fecha_nacimiento", "");
            }
            if (request.getAttribute("email") == null) {
                request.setAttribute("email", "");
            }
            if (request.getAttribute("telefono") == null) {
                request.setAttribute("telefono", "");
            }
            if (request.getAttribute("profesion") == null) {
                request.setAttribute("profesion", "");
            }

        %>

        <form method="get" action="ServletMantPaciente">
            <h1>Datos del Paciente</h1>
            <table border="1">
                <tr>
                    <td>Num_asegurado</td>
                    <td>
                        <input type="integer" id="txtAsegurado" name="num_asegurado" value="<%= request.getAttribute("num_asegurado")%>"
                               placeholder="Ingrese el numero de asegurado" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td>
                        <input type="text" id="txtNombre" name="nombre" value="<%= request.getAttribute("nombre")%>"
                               maxlength="80" style="width:400px;" placeholder="Ingrese el nombre del paciente"/>
                    </td>
                </tr>
                <tr>
                    <td>Direccion</td>
                    <td>
                        <input type="text" id="txtDireccion" name="direccion" value="<%= request.getAttribute("direccion")%>"
                               placeholder="Ingrese la direccion" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Edad</td>
                    <td>
                        <input type="number" id="txtEdad" name="edad" value="<%= request.getAttribute("edad")%>"
                               min="0"  placeholder="Ingrese la edad" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Fecha_Nacimiento</td>
                    <td>
                        <input type="Date" id="txtFecha_nacimiento" name="fecha_nacimiento" value="<%= request.getAttribute("fecha_nacimiento")%>"
                               placeholder="Ingrese la fecha de nacimiento" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                        <input type="text" id="txtEmail" name="email" value="<%= request.getAttribute("email")%>"
                               placeholder="Ingrese el email" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Telefono</td>
                    <td>
                        <input type="integer" id="txtTelefono" name="telefono" step="0.01" value="<%= request.getAttribute("telefono")%>"
                               placeholder="Ingrese el telefono" style="width:400px;"/>
                    </td>
                </tr>
                </tr>
                <tr>
                    <td>Profesion</td>
                    <td>
                        <input type="text" id="txtProfesion" name="profesion" step="0.01" value="<%= request.getAttribute("profesion")%>"
                               placeholder="Ingrese la profesion" style="width:400px;"/>
                    </td>
                </tr>
            </table>
            <br/><br/>

            <input type="hidden" id="accion" name="accion"/>
            <input type="hidden" id="seleccionar" name="seleccionar"/>
            <input type="submit" value="Limpiar" onclick="return limpiar();"/>
            <input type="submit" value="Agregar" onclick="return agregar();"/>
            <input type="submit" value="Modificar" onclick="return modificar();"/>
            <input type="submit" value="Eliminar" onclick="return eliminar();"/>
            <input type="submit" value="Consultar por num_asegurado" onclick="return consultaNum_Asegurado();"/>
            <input type="submit" value="Consultar por nombre" onclick="return consultaNombre();"/>
            <input type="submit" value="Regresar" onclick="return regresar();"/>


            <hr/>
            <table border="1">
                <tr>
                    <th>Numero de asegurado</th>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Edad</th>
                    <th>Fecha de nacimiento</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Profesion</th>
                    <th>Seleccionar</th>
                </tr>

                <%!  List<Paciente> listaPacientes = new ArrayList(); %>
                <%!  PacienteBo pacienteBo = new PacienteBo();%>

                <%
                    if (request.getParameter("CONSXNUM_ASEGURADO") != null) {
                        Integer num = Integer.parseInt(request.getParameter("CONSXNUM_ASEGURADO"));
                        Paciente pac = pacienteBo.consultarXNum_asegurado(num);

                        if (pac == null) {
                            out.println("<h4>No se encontró el paciente con el num_asegurado dado.</h4>");
                            this.listaPacientes = new ArrayList<Paciente>();
                        } else {
                            this.listaPacientes = new ArrayList<Paciente>();
                            this.listaPacientes.add(pac);
                        }

                    } else {
                        if (request.getParameter("CONSXNOMBRE") != null) {
                            String name = request.getParameter("CONSXNOMBRE");
                            this.listaPacientes = pacienteBo.consultarXNombre(name);

                            if (listaPacientes.isEmpty()) {
                                out.println("<h4>No se encontró el paciente con nombre brindado.</h4>");
                            }
                        } else {
                            this.listaPacientes = this.pacienteBo.consultarTodos();
                        }
                    }

                    for (int i = 0; i < listaPacientes.size(); i++) {
                        Paciente paciente = listaPacientes.get(i);

                        out.println("<tr>");

                        out.println("<td>");
                        out.println(paciente.getNum_asegurado());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getNombre());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getDireccion());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getEdad());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getFecha_nacimiento());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getEmail());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getTelefono());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(paciente.getProfesion());
                        out.println("</td>");


                %>

                <td style="text-align: center;">
                    <input type="image" src="img/tocar.png" width="25"
                           onclick="return elegirFila(<%= paciente.getNum_asegurado()%>)"      />
                </td>


                <%
                        out.println("<tr>");
                    }
                %>


            </table>


        </form>
        <% }%>

    </body>
</html>
