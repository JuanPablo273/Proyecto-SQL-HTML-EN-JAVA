/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LuBarr3
 */
@WebServlet(name = "ServletDoctor", urlPatterns = {"/ServletDoctor"})
public class ServletDoctor extends HttpServlet {

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
        
        Integer cedula = Integer.parseInt(request.getParameter("cedula"));
        String nombre = (request.getParameter("nombre"));
        String apellido = (request.getParameter("apellido"));
        String especialidad = request.getParameter("especialidad");
        Double salario = Double.parseDouble(request.getParameter("salario"));
        String direccion = (request.getParameter("direccion"));
        Integer telefono = Integer.parseInt(request.getParameter("telefono"));
        
        HttpSession laSesion = request.getSession();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletDoctor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletDoctor at " + request.getContextPath() + "</h1>");
            
            if(laSesion.getAttribute("nombreUsuario") == null){
                out.println("<p>Sesión invalida, vuelva a loguearse</p>");
                out.println("<a href=\"login.html\">Ir al Login</a>");
            }else{
                out.println("<p>Cedula: "+ cedula + "</p>");
                out.println("<p>Nombre: "+ nombre + "</p>");
                out.println("<p>Apellido: "+ apellido + "</p>");
                out.println("<p>Especialidad: "+ especialidad + "</p>");
                out.println("<p>Salario: "+ salario + "</p>");
                out.println("<p>Direccion: "+ direccion + "</p>");
                out.println("<p>Telefono: "+ telefono + "</p>");
                
                out.println("<p>La Cedula del doctor conectado: "+ laSesion.getAttribute("cedulaDoctor") + "</p>");
            }
            
            
            
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
