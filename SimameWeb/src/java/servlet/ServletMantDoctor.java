
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.bo.DoctorBo;
import negocio.clases.Doctor;


@WebServlet(name = "ServletMantDoctor", urlPatterns = {"/ServletMantDoctor"})
public class ServletMantDoctor extends HttpServlet {

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
        Doctor elDoctor = new Doctor();
        DoctorBo doctorBo = new DoctorBo();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletMantDoctor</title>");            
            out.println("</head>");
            out.println("<body>");
            
            switch(accion){
                case "AGREGAR":
                    elDoctor = new Doctor();
                    
                    String cedula = request.getParameter("cedula");
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String especialidad= request.getParameter("especialidad");
                    String salario = request.getParameter("salario");
                    String direccion = request.getParameter("direccion");
                    String telefono = request.getParameter("telefono");
                    elDoctor.setCedula(Integer.parseInt(cedula));
                    elDoctor.setNombre(nombre);
                    elDoctor.setApellido(apellido);
                    elDoctor.setEspecialidad(especialidad);
                    elDoctor.setSalario(Double.parseDouble(salario));
                    elDoctor.setDireccion(direccion);
                    elDoctor.setTelefono(Integer.parseInt(telefono));
                    
                    int res = doctorBo.insertar(elDoctor);
                    switch(res){
                        case 0: 
                                out.println("<h1>Doctor agregado correctamente.</h1>");
                                break;
                        case 1: 
                                out.println("<h1>No se pudo conectar a la BD.</h1>");
                                break;
                        case 2: 
                                out.println("<h1>Ocurrio un error inesperado.</h1>");
                                break;
                        case 3: 
                                out.println("<h1>Ya existe un Doctor con esa cédula.</h1>");
                                break;
                       
                    }
                    break;
                case "SELECCIONAR":
                    Integer ced = Integer.parseInt(request.getParameter("seleccionar"));
                    elDoctor = doctorBo.consultarXCedula(ced);
                    
                    request.setAttribute("cedula", elDoctor.getCedula());
                    request.setAttribute("nombre", elDoctor.getNombre());
                    request.setAttribute("apellido", elDoctor.getApellido());
                    request.setAttribute("especialidad", elDoctor.getEspecialidad());
                    request.setAttribute("salario", elDoctor.getSalario());
                    request.setAttribute("direccion", elDoctor.getDireccion());
                    request.setAttribute("telefono", elDoctor.getTelefono());
                 
                    
                    request.getRequestDispatcher("mantenimientoDoctor.jsp").forward(request, response);    
                    
                    break;
                case "MODIFICAR":
                    elDoctor= new Doctor();
                    
                    cedula = request.getParameter("cedula");
                    nombre = request.getParameter("nombre");
                    apellido = request.getParameter("apellido");
                    especialidad = request.getParameter("especialidad");
                    salario = request.getParameter("salario");
                    direccion = request.getParameter("direccion");
                    telefono = request.getParameter("telefono");
                    
                    elDoctor.setCedula(Integer.parseInt(cedula));
                    elDoctor.setNombre(nombre);
                    elDoctor.setApellido(apellido);
                    elDoctor.setEspecialidad(especialidad);
                    elDoctor.setSalario(Double.parseDouble(salario));
                    elDoctor.setDireccion(direccion);
                    elDoctor.setTelefono(Integer.parseInt(telefono));
                    
                    res = doctorBo.modificar(elDoctor);
                    switch(res){
                        case 0: 
                                out.println("<h1>Doctor modificado correctamente.</h1>");
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
                    elDoctor = new Doctor();
                    elDoctor.setCedula(Integer.parseInt(request.getParameter("cedula")));
                    res = doctorBo.eliminar(elDoctor);
                    switch(res){
                        case 0:  out.println("<h1>Doctor modificado correctamente.</h1>");
                            break;
                        case 1: 
                                out.println("<h1>Doctor eliminado correctamente.</h1>");
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
                case "CONS-CEDULA":
                    String cedulaConsulta = request.getParameter("cedula");
                    response.sendRedirect("mantenimientoDoctor.jsp?CONSXCEDULA="+cedulaConsulta);
                    break;
                case "LIMPIAR":
                    response.sendRedirect("mantenimientoDoctor.jsp");
                    break;
                case "CONS-NOMBRE":
                    String nombreConsulta = request.getParameter("nombre");
                    response.sendRedirect("mantenimientoDoctor.jsp?CONSXNOMBRE="+nombreConsulta);
                    break;
                case "REGRESAR":
                    response.sendRedirect("inicio.jsp");
                    break;
            }
                      
            
            
            out.println("<br/>");
            out.println("<a href=\"mantenimientoDoctor.jsp\">Regresar</a>"); // ALT 92 para \
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
