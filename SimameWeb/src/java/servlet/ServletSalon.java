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
@WebServlet(name = "ServletSalon", urlPatterns = {"/ServletSalon"})
public class ServletSalon extends HttpServlet {

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
        
        Integer num_salon = Integer.parseInt(request.getParameter("num_salon"));
        Integer cant_camas = Integer.parseInt(request.getParameter("cant_camas"));
        String area = (request.getParameter("area"));
        Integer cedula_doctor = Integer.parseInt(request.getParameter("cedula_doctor"));
        
        
        HttpSession laSesion = request.getSession();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSalon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletSalon at " + request.getContextPath() + "</h1>");
            
            if(laSesion.getAttribute("nombreSalon") == null){
                out.println("<p>Sesión invalida, vuelva a loguearse</p>");
                out.println("<a href=\"login.html\">Ir al Login</a>");
            }else{
                out.println("<p>Cedula: "+ num_salon + "</p>");
                out.println("<p>Cant_Camas: "+ cant_camas + "</p>");
                out.println("<p>Area: "+ area + "</p>");
                out.println("<p>Cedula_Doctor: "+ cedula_doctor + "</p>");
               
                
                out.println("<p>El numero del salon conectado: "+ laSesion.getAttribute("num_salonDoctor") + "</p>");
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
