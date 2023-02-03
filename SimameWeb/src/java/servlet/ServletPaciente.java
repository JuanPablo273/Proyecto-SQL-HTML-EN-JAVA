/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.PacienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
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
@WebServlet(name = "ServletPaciente", urlPatterns = {"/ServletPaciente"})
public class ServletPaciente extends HttpServlet {
    
    
   

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
        
        Integer num_asegurado = Integer.parseInt(request.getParameter("num_asegurado"));
        String nombre = (request.getParameter("nombre"));
        String direccion = (request.getParameter("direccion"));
        String edad = (request.getParameter("edad"));
        String fecha_nacimiento = (request.getParameter("fecha_nacimiento"));
        String email = (request.getParameter("email"));
        Integer telefono = Integer.parseInt(request.getParameter("telefono"));
        String profesion = (request.getParameter("profesion"));
        
        HttpSession laSesion = request.getSession();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletPaciente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletPaciente at " + request.getContextPath() + "</h1>");
            
            if(laSesion.getAttribute("nombreUsuario") == null){
                out.println("<p>Sesión invalida, vuelva a loguearse</p>");
                out.println("<a href=\"login.html\">Ir al Login</a>");
            }else{
                out.println("<p>Num_Asegurado: "+ num_asegurado + "</p>");
                out.println("<p>Nombre: "+ nombre + "</p>");
                out.println("<p>Direccion: "+ direccion + "</p>");
                out.println("<p>Edad: "+ edad + "</p>");
                out.println("<p>Fecha_nacimiento: "+ fecha_nacimiento + "</p>");
                out.println("<p>email: "+ email + "</p>");
                out.println("<p>Telefono: "+ telefono + "</p>");
                out.println("<p>Profesion: "+ profesion + "</p>");
                
                out.println("<p>El num asegurado del paciente conectado: "+ laSesion.getAttribute("num_aseguradoPaciente") + "</p>");
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
