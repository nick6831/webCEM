/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.Usuario;
import Beans.XMLSerializer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Servicios_Cem.*;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import java.beans.XMLEncoder;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author portafolio
 */
@WebServlet(name = "svtLogInAlumno", urlPatterns = {"/svtLogInAlumno"})
public class svtLogInAlumno extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            
            Usuario usuario = new Usuario(user, pass, "1");
            ValidarUsuario val = new ValidarUsuario();
            ObjectFactory fac = new ObjectFactory();
            JAXBElement<String> str= fac.createValidarUsuarioUserPass(pass);
            val.setUserPass(str);
            ValidarUsuarioResponse vali = new ValidarUsuarioResponse();
            if (vali.isValidarUsuarioResult()==true) 
            {
                 sesion.setAttribute("usuario", usuario);
            
                response.sendRedirect("Alumno.jsp");
                
            }else{
                response.sendRedirect("index.jsp");
            }
            
        
            
            
           
            
            
            
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
