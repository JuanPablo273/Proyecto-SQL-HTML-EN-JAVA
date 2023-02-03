<%@page import="negocio.bo.SalonBo"%>
<%@page import="negocio.clases.Salon"%>
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
                var numsalon = document.getElementById("txtNum_salon").value;
                var camas = document.getElementById("txtCamas").value;
                var area = document.getElementById("txtArea").value;
                var doctor = document.getElementById("txtDoctor").value;


                if (isNaN(numsalon)) {
                    alert("No es un numeo de salón válido");
                    vd = 0;
                } else {
                    if (numsalon === "") {
                        alert("Debe ingresar un numero de salón");
                        vd = 0;
                    }
                }

                if (isNaN(camas)) {
                    alert("No es una cantidad de camas válida");
                    vd = 0;
                } else {
                    if (camas === "") {
                        alert("Debe ingresar una cantidad de camas");
                        vd = 0;
                    }
                }

                if (isNaN(doctor)) {
                    alert("No es un número de cedula válido");
                    vd = 0;
                } else {
                    if (camas === "") {
                        alert("Debe ingresar un número de cédula de doctor");
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


            function agregar() {
                if (validarDatos()) {
                    document.getElementById("accion").value = "AGREGAR";
                    return true;
                } else {
                    return false;
                }

            }

            function elegirFila(num_salon) {
                alert("selecion");
                document.getElementById("accion").value = "SELECCIONAR";
                document.getElementById("seleccionar").value = num_salon;
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
                var num_salon = document.getElementById("txtNum_salon").value;
                if (isNaN(num_salon)) {
                    alert("No es un Numero_salon válido");
                    return false;
                } else {
                    if (num_salon === "") {
                        alert("Debe ingresar un num_salon  a eliminar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "ELIMINAR";
                        return true;
                    }
                }
            }

            function consultaNum_salon() {
                var num_salon = document.getElementById("txtNum_salon").value;
                if (isNaN(num_salon)) {
                    alert("No es un num_salon válido");
                    return false;
                } else {
                    if (num_salon === "") {
                        alert("Debe ingresar un num_salon  a consultar");
                        return false;
                    } else {
                        document.getElementById("accion").value = "CONS-NUM_SALON";
                        return true;
                    }
                }
            }

            function consultaArea() {
                var area = document.getElementById("txtArea").value;
                if (area === "") {
                    alert("Debe ingresar un area a consultar");
                    return false;
                } else {
                    document.getElementById("accion").value = "CONS-AREA";
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
            if (request.getAttribute("num_salon") == null) {
                request.setAttribute("num_salon", "");
            }

            if (request.getAttribute("cant_camas") == null) {
                request.setAttribute("cant_camas", "");
            }
            if (request.getAttribute("area") == null) {
                request.setAttribute("area", "");
            }

            if (request.getAttribute("cedula_doctor") == null) {
                request.setAttribute("cedula_doctor", "");
            }


        %>

        <form method="get" action="ServletMantSalon">
            <h1>Datos del Salon</h1>
            <table border="1">
                <tr>
                    <td>Salon</td>
                    <td>
                        <input type="integer" id="txtNum_salon" name="num_salon" value="<%= request.getAttribute("num_salon")%>"
                               maxlength="30" style="width:400px;" placeholder="Ingrese el numero de salon"/>
                    </td>
                </tr>
                <tr>
                    <td>Camas</td>
                    <td>
                        <input type="integer" id="txtCamas" name="cant_camas" value="<%= request.getAttribute("cant_camas")%>"
                               placeholder="Ingrese la cantidad de camas" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Area</td>
                    <td>
                        <input type="text" id="txtArea" name="area" value="<%= request.getAttribute("area")%>"
                               placeholder="Ingrese el area" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>Doctor</td>
                    <td>
                        <input type="integer" id="txtDoctor" name="cedula_doctor" value="<%= request.getAttribute("cedula_doctor")%>"
                               placeholder="Ingrese la cedula del doctor" style="width:400px;"/>
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
            <input type="submit" value="Consultar por Num_salon" onclick="return consultaNum_salon();"/>
            <input type="submit" value="Consultar por Area" onclick="return consultaArea();"/>
            <input type="submit" value="Regresar" onclick="return regresar();"/>


            <hr/>
            <table border="1">
                <tr>
                    <th>Numero de salon</th>
                    <th>Cantidad de camas</th>
                    <th>Area</th>
                    <th>Cedula del doctor</th>
                    <th>Seleccionar</th>
                </tr>

                <%!  List<Salon> listaSalons = new ArrayList(); %>
                <%!  SalonBo salonBo = new SalonBo();%>

                <%
                    if (request.getParameter("CONSXNUM_SALON") != null) {
                        Integer numsal = Integer.parseInt(request.getParameter("CONSXNUM_SALON"));
                        Salon sal = salonBo.consultarXNum_salon(numsal);

                        if (sal == null) {
                            out.println("<h4>No se encontró el Salon con el Num_Salon dado.</h4>");
                            this.listaSalons = new ArrayList<Salon>();
                        } else {
                            this.listaSalons = new ArrayList<Salon>();
                            this.listaSalons.add(sal);
                        }

                    } else {
                        if (request.getParameter("CONSXAREA") != null) {
                            String area = request.getParameter("CONSXAREA");
                            this.listaSalons = salonBo.consultarXArea(area);
                            if (listaSalons.isEmpty()) {
                                out.println("<h4>No se encontró el salon con el area dado.</h4>");
                            }
                        } else {
                            this.listaSalons = this.salonBo.consultarTodos();
                        }
                    }

                    for (int i = 0; i < listaSalons.size(); i++) {
                        Salon salon = listaSalons.get(i);

                        out.println("<tr>");

                        out.println("<td>");
                        out.println(salon.getNum_salon());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(salon.getCant_camas());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(salon.getArea());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(salon.getCedula_doctor());
                        out.println("</td>");


                %>

                <td style="text-align: center;">
                    <input type="image" src="img/tocar.png" width="25"
                           onclick="return elegirFila(<%= salon.getNum_salon()%>)"      />
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