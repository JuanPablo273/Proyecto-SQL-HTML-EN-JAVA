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
                var cedula = document.getElementById("txtCedula").value;
                var nombre = document.getElementById("txtNombre").value;
                var apellido = document.getElementById("txtApellido").value;
                var especialidad = document.getElementById("txtEspecialidad").value;
                var salario = document.getElementById("txtSalario").value;
                var direccion = document.getElementById("txtDireccion").value;
                var telefono = document.getElementById("txtTelefono").value;


                if (isNaN(cedula)) {
                    alert("No es un numeo de cedula válido");
                    vd = 0;
                } else {
                    if (cedula === "") {
                        alert("Debe ingresar un numero de cedula");
                        vd = 0;
                    }
                }

                if (nombre === "") {
                    alert("Debe ingresar un nombre");
                    vd = 0;
                }

                if (apellido === "") {
                    alert("Debe ingresar un apellido");
                    vd = 0;
                }

                if (especialidad === "") {
                    alert("Debe ingresar una especialidad");
                    vd = 0;
                }

                if (salario === "") {
                    alert("Debe ingresar un salario");
                    vd = 0;
                }

                if (direccion === "") {
                    alert("Debe ingresar una direccion");
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


            function elegirFila(cedula) {
                alert("selecion");
                document.getElementById("accion").value = "SELECCIONAR";
                document.getElementById("seleccionar").value = cedula;
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
                var cedula = document.getElementById("txtCedula").value;
                if (isNaN(cedula)) {
                    alert("No es una cédula válida");
                    return false;
                } else {
                    if (cedula === "") {
                        alert("Debe ingresar una cédula a eliminar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "ELIMINAR";
                        return true;
                    }
                }
            }

            function consultaCedula() {
                var cedula = document.getElementById("txtCedula").value;
                if (isNaN(cedula)) {
                    alert("No es una cédula válida");
                    return false;
                } else {
                    if (cedula === "") {
                        alert("Debe ingresar una cédula a consultar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "CONS-CEDULA";
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
            if (request.getAttribute("nombre") == null) {
                request.setAttribute("nombre", "");
            }

            if (request.getAttribute("cedula") == null) {
                request.setAttribute("cedula", "");
            }
            if (request.getAttribute("apellido") == null) {
                request.setAttribute("apellido", "");
            }

            if (request.getAttribute("especialidad") == null) {
                request.setAttribute("especialidad", "");
            }

            if (request.getAttribute("salario") == null) {
                request.setAttribute("salario", "");
            }
            if (request.getAttribute("direccion") == null) {
                request.setAttribute("direccion", "");
            }
            if (request.getAttribute("telefono") == null) {
                request.setAttribute("telefono", "");
            }

        %>

        <form method="get" action="ServletMantDoctor">
            <h1>Datos del Doctor</h1>
            <table border="1">
                <tr>
                    <td>Cédula</td>
                    <td>
                        <input type="integer" id="txtCedula" name="cedula" value="<%= request.getAttribute("cedula")%>"
                               placeholder="Ingrese la cédula del doctor" style="width:400px;"/>
                    </td>
                </tr>

                <tr>
                    <td>Nombre</td>
                    <td>
                        <input type="text" id="txtNombre" name="nombre" value="<%= request.getAttribute("nombre")%>"
                               maxlength="30" style="width:400px;" placeholder="Ingrese el nombre"/>
                    </td>
                </tr>

                <tr>
                    <td>Apellido</td>
                    <td>
                        <input type="text" id="txtApellido" name="apellido" value="<%= request.getAttribute("apellido")%>"
                               placeholder="Ingrese el apellido" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Especialidad</td>
                    <td>
                        <input type="text" id="txtEspecialidad" name="especialidad" value="<%= request.getAttribute("especialidad")%>"
                               placeholder="Ingrese la especialidad del doctor" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Salario</td>
                    <td>
                        <input type="double" id="txtSalario" name="salario"  value="<%= request.getAttribute("salario")%>"
                               placeholder="Ingrese el salario" style="width:400px;"/>
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
                    <td>Telefono</td>
                    <td>
                        <input type="integer" id="txtTelefono" name="telefono"  value="<%= request.getAttribute("telefono")%>"
                               placeholder="Ingrese el telefono" style="width:400px;"/>
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
            <input type="submit" value="Consultar por cédula" onclick="return consultaCedula();"/>
            <input type="submit" value="Consultar por nombre" onclick="return consultaNombre();"/>
            <input type="submit" value="Regresar" onclick="return regresar();"/>


            <hr/>
            <table border="1">
                <tr>
                    <th>Cédula</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Salario</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Seleccionar</th>
                </tr>

                <%!  List<Doctor> listaDoctors = new ArrayList(); %>
                <%!  DoctorBo doctorBo = new DoctorBo();%>

                <%
                    if (request.getParameter("CONSXCEDULA") != null) {
                        Integer ced = Integer.parseInt(request.getParameter("CONSXCEDULA"));
                        Doctor doc = doctorBo.consultarXCedula(ced);

                        if (doc == null) {
                            out.println("<h4>No se encontró el doctor con la cédula dada.</h4>");
                            this.listaDoctors = new ArrayList<Doctor>();
                        } else {
                            this.listaDoctors = new ArrayList<Doctor>();
                            this.listaDoctors.add(doc);
                        }

                    } else {
                        if (request.getParameter("CONSXNOMBRE") != null) {
                            String name = request.getParameter("CONSXNOMBRE");
                            this.listaDoctors = doctorBo.consultarXNombre(name);

                            if (listaDoctors.isEmpty()) {
                                out.println("<h4>No se encontró el doctor con nombre brindado.</h4>");
                            }
                        } else {
                            this.listaDoctors = this.doctorBo.consultarTodos();
                        }
                    }

                    for (int i = 0; i < listaDoctors.size(); i++) {
                        Doctor doctor = listaDoctors.get(i);

                        out.println("<tr>");

                        out.println("<td>");
                        out.println(doctor.getCedula());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getNombre());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getApellido());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getEspecialidad());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getSalario());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getDireccion());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(doctor.getTelefono());
                        out.println("</td>");


                %>

                <td style="text-align: center;">
                    <input type="image" src="img/tocar.png" width="25"
                           onclick="return elegirFila(<%= doctor.getCedula()%>)"      />
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
