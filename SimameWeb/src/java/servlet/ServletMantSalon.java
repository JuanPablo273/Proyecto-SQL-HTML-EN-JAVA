package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.bo.DoctorBo;
import negocio.bo.SalonBo;
import negocio.clases.Doctor;
import negocio.clases.Salon;

@WebServlet(name = "ServletMantSalon", urlPatterns = {"/ServletMantSalon"})
public class ServletMantSalon extends HttpServlet {

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
        Salon elSalon = new Salon();
        SalonBo salonBo = new SalonBo();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletMantSalon</title>");
            out.println("</head>");
            out.println("<body>");

            switch (accion) {
                case "AGREGAR":
                    elSalon = new Salon();

                    String num_salon = request.getParameter("num_salon");
                    String cant_camas = request.getParameter("cant_camas");
                    String area = request.getParameter("area");
                    String cedula_doctor = request.getParameter("cedula_doctor");

                    elSalon.setNum_salon(Integer.parseInt(num_salon));
                    elSalon.setCant_camas(Integer.parseInt(cant_camas));
                    elSalon.setArea(area);
                    elSalon.setCedula_doctor(Integer.parseInt(cedula_doctor));

                    int res = salonBo.insertar(elSalon);
                    switch (res) {
                        case 0:
                            out.println("<h1>Salon agregado correctamente.</h1>");
                            break;
                        case 1:
                            out.println("<h1>No se pudo conectar a la BD.</h1>");
                            break;
                        case 2:
                            out.println("<h1>Ocurrio un error inesperado.</h1>");
                            break;
                        case 3:
                            out.println("<h1>Ya existe un Salon con ese num_salon.</h1>");
                            break;

                    }
                    break;
                case "SELECCIONAR":
                    Integer numsal = Integer.parseInt(request.getParameter("seleccionar"));
                    elSalon = salonBo.consultarXNum_salon(numsal);

                    request.setAttribute("num_salon", elSalon.getNum_salon());
                    request.setAttribute("cant_camas", elSalon.getCant_camas());
                    request.setAttribute("area", elSalon.getArea());
                    request.setAttribute("cedula_doctor", elSalon.getCedula_doctor());

                    request.getRequestDispatcher("mantenimientoSalon.jsp").forward(request, response);

                    break;
                case "MODIFICAR":
                    elSalon = new Salon();
                    num_salon = request.getParameter("num_salon");
                    cant_camas = request.getParameter("cant_camas");
                    area = request.getParameter("area");
                    cedula_doctor = request.getParameter("cedula_doctor");

                    elSalon.setNum_salon(Integer.parseInt(num_salon));
                    elSalon.setCant_camas(Integer.parseInt(cant_camas));
                    elSalon.setArea(area);
                    elSalon.setCedula_doctor(Integer.parseInt(cedula_doctor));

                    res = salonBo.modificar(elSalon);
                    switch (res) {
                        case 0:
                            out.println("<h1>Salon modificado correctamente.</h1>");
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
                    elSalon = new Salon();
                    elSalon.setNum_salon(Integer.parseInt(request.getParameter("num_salon")));
                    res = salonBo.eliminar(elSalon);
                    switch (res) {
                        case 0:
                            out.println("<h1>Salon modificado correctamente.</h1>");
                            break;
                        case 1:
                            out.println("<h1>Salon eliminado correctamente.</h1>");
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
                case "CONS-NUM_SALON":
                    String num_salonConsulta = request.getParameter("num_salon");
                    response.sendRedirect("mantenimientoSalon.jsp?CONSXNUM_SALON=" + num_salonConsulta);
                    break;
                case "LIMPIAR":
                    response.sendRedirect("mantenimientoSalon.jsp");
                    break;
                case "CONS-AREA":
                    String areaConsulta = request.getParameter("area");
                    response.sendRedirect("mantenimientoSalon.jsp?CONSXAREA=" + areaConsulta);
                    break;
                case "REGRESAR":
                    response.sendRedirect("inicio.jsp");
                    break;
            }

            out.println("<br/>");
            out.println("<a href=\"mantenimientoSalon.jsp\">Regresar</a>"); // ALT 92 para \
            out.println("</body>");
            out.println("</html>");
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
