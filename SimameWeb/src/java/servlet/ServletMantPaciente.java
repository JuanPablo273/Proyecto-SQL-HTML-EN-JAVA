package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.bo.DoctorBo;
import negocio.bo.PacienteBo;
import negocio.clases.Doctor;
import negocio.clases.Paciente;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletMantPaciente", urlPatterns = {"/ServletMantPaciente"})
public class ServletMantPaciente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        Paciente elPaciente = new Paciente();
        PacienteBo pacienteBo = new PacienteBo();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletMantPaciente</title>");
            out.println("</head>");
            out.println("<body>");

            switch (accion) {
                case "AGREGAR":
                    elPaciente = new Paciente();

                    String num_asegurado = request.getParameter("num_asegurado");
                    String nombre = request.getParameter("nombre");
                    String direccion = request.getParameter("direccion");
                    String edad = request.getParameter("edad");
                    //Date fecha_nacimiento = request.getParameter("fecha_nacimiento");
                    String fecha_nacimiento = request.getParameter("fecha_nacimiento");
                    String email = request.getParameter("email");
                    String telefono = request.getParameter("telefono");
                    String profesion = request.getParameter("profesion");
// AQUI PROBAMOS FORMATEAR LA FECHA A DATE 
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nacimiento);
// AQUI PROBAMOS FORMATEAR LA FECHA A DATE 
                    elPaciente.setNum_asegurado(Integer.parseInt(num_asegurado));
                    elPaciente.setNombre(nombre);
                    elPaciente.setDireccion(direccion);
                    elPaciente.setEdad(Integer.parseInt(edad));
                     {

                        elPaciente.setFecha_nacimiento(date1); // ERROR DE DATE
                    }
                    elPaciente.setEmail(email);
                    elPaciente.setTelefono(Integer.parseInt(telefono));
                    elPaciente.setProfesion(profesion);

                    int res = pacienteBo.insertar(elPaciente);
                    switch (res) {
                        case 0:
                            out.println("<h1>Paciente agregado correctamente.</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar a la BD.</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ocurrio un error inesperado.</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ya existe un Paciente con ese num_asegurado.</h1>");
                            break;

                    }
                    break;

                case "SELECCIONAR":
                    Integer num = Integer.parseInt(request.getParameter("seleccionar"));
                    elPaciente = pacienteBo.consultarXNum_asegurado(num);

                    request.setAttribute("num_asegurado", elPaciente.getNum_asegurado());
                    request.setAttribute("nombre", elPaciente.getNombre());
                    request.setAttribute("direccion", elPaciente.getDireccion());
                    request.setAttribute("edad", elPaciente.getEdad());
                    request.setAttribute("fecha_nacimiento", elPaciente.getFecha_nacimiento());
                    request.setAttribute("email", elPaciente.getEmail());
                    request.setAttribute("telefono", elPaciente.getTelefono());
                    request.setAttribute("profesion", elPaciente.getProfesion());

                    request.getRequestDispatcher("mantenimientoPaciente.jsp").forward(request, response);

                    break;
                case "MODIFICAR":
                    elPaciente = new Paciente();

                    num_asegurado = request.getParameter("num_asegurado");
                    nombre = request.getParameter("nombre");
                    direccion = request.getParameter("direccion");
                    edad = request.getParameter("edad");
                    fecha_nacimiento = request.getParameter("fecha_nacimiento");
                    email = request.getParameter("email");
                    telefono = request.getParameter("telefono");
                    profesion = request.getParameter("profesion");
// AQUI PROBAMOS FORMATEAR LA FECHA A DATE 
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nacimiento);
// AQUI PROBAMOS FORMATEAR LA FECHA A DATE 
                    elPaciente.setNum_asegurado(Integer.parseInt(num_asegurado));
                    elPaciente.setNombre(nombre);
                    elPaciente.setDireccion(direccion);
                    elPaciente.setEdad(Integer.parseInt(edad));
                    elPaciente.setFecha_nacimiento(date2); /// ERRORE DE DATE
                    elPaciente.setEmail(email);
                    elPaciente.setTelefono(Integer.parseInt(telefono));
                    elPaciente.setProfesion(profesion);

                    res = pacienteBo.modificar(elPaciente);
                    switch (res) {
                        case 0:
                            out.println("<h1>Paciente modificado correctamente.</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar a la BD.</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ocurrio un error inesperado.</h1>");
                            break;

                    }
                    break;
                case "ELIMINAR":
                    elPaciente = new Paciente();
                    elPaciente.setNum_asegurado(Integer.parseInt(request.getParameter("num_asegurado")));
                    res = pacienteBo.eliminar(elPaciente);
                    switch (res) {
                        case 0:
                            out.println("<h1>Paciente modificado correctamente.</h1>");
                            break;
                        case 1:
                            out.println("<h1>Paciente eliminado correctamente.</h1>");
                            break;
                        case 2:
                            out.println("<h1>No se pudo conectar a la BD.</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ocurrio un error inesperado.</h1>");
                            break;
                        case 4:
                            out.println("<h1>No se pudo eliminar porque tiene registros asociados!</h1>");
                            break;

                    }
                    break;
                case "CONS-NUM_ASEGURADO":
                    String num_aseguradoConsulta = request.getParameter("num_asegurado");
                    response.sendRedirect("mantenimientoPaciente.jsp?CONSXNUM_ASEGURADO=" + num_aseguradoConsulta);
                    break;
                case "LIMPIAR":
                    response.sendRedirect("mantenimientoPaciente.jsp");
                    break;
                case "CONS-NOMBRE":
                    String nombreConsulta = request.getParameter("nombre");
                    response.sendRedirect("mantenimientoPaciente.jsp?CONSXNOMBRE=" + nombreConsulta);
                    break;
                case "REGRESAR":
                    response.sendRedirect("inicio.jsp");
                    break;
            }

            out.println("<br/>");
            out.println("<a href=\"mantenimientoPaciente.jsp\">Regresar</a>"); // ALT 92 para \
            out.println("</body>");
            out.println("</html>");
        } catch (ParseException ex) {
            Logger.getLogger(ServletMantPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
