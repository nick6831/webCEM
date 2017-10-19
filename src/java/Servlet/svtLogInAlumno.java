/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.Alumno;
import Beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Servicios_Cem.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
            throws ServletException, IOException, JAXBException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            
            /*rescatar parametros de JSP*/
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            
            /*Iniciar Servicios*/
            Servicios ser = new Servicios();
            /*Instancia de usuario*/
            Usuario usuario = new Usuario();
            /*Almacenar datos de usuarios*/
            usuario.NomUsuario = user;
            usuario.Password = pass;
           
            /*Serializar usuarios en JSON*/
            StringWriter string = usuario.Json();
            
            /*Metodo para validar si el usuario existe*/
            boolean b = ser.getBasicHttpBindingIServicios().validarUsuario(string.getBuffer().toString());
            /*Valida si el usuario existe*/
            if(b)
            {
                /*Metodo para leer usuario*/
                String usua = ser.getBasicHttpBindingIServicios().leerUsuario(usuario.Json().getBuffer().toString());

                JsonReader reader2 = Json.createReader(new StringReader(usua));
                JsonObject usuarioObject = reader2.readObject();
                reader2.close();

                Usuario usuario2 = new Usuario(usuarioObject);

                if (pass.equals(usuario2.Password)) {
                    Alumno nalu = new Alumno();
                nalu.IdAlumno = usuarioObject.getInt("IdAlumno");

                String alu = ser.getBasicHttpBindingIServicios().leerAlumno(nalu.Json().getBuffer().toString());

                JsonReader reader = Json.createReader(new StringReader(alu));
                JsonObject alumnoObject = reader.readObject();
                reader.close();
                
                Alumno alumno = new Alumno(alumnoObject);

                request.setAttribute("alumno", alumno);

                sesion.setAttribute("usuario", usuario2);
                request.getRequestDispatcher("Alumno.jsp").forward(request, response);
                }else{
                    sesion.invalidate();
                    response.sendRedirect("logAlumno.jsp");
                }
                
            }else
            {
                sesion.invalidate();
                response.sendRedirect("logAlumno.jsp");
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(svtLogInAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(svtLogInAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
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
