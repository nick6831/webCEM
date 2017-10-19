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
            
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            
            
            Servicios ser = new Servicios();
            Usuario usuario = new Usuario();

            usuario.IdUsuario = "0";
            usuario.NomUsuario = user;
            usuario.Password = pass;
            usuario.IdAlumno = "0";
            usuario.IdAdministrativo = "0";
            usuario.IdRol = "0";
            usuario.IdFamilia = "0";
            usuario.IdEncargadoCel = "0";
           
            boolean b = ser.getBasicHttpBindingIServicios().validarUsuario(usuario.jaxbObjectToXML().getValue());
            if(b)
            {
                String usua = ser.getBasicHttpBindingIServicios().leerUsuario(usuario.jaxbObjectToXML().getValue());

                JsonReader reader2 = Json.createReader(new StringReader(usua));
                JsonObject usuarioObject = reader2.readObject();
                reader2.close();

                usuario.IdUsuario = String.valueOf(usuarioObject.getInt("IdUsuario"));
                usuario.IdAlumno = String.valueOf(usuarioObject.getInt("IdAlumno"));


                Alumno alumnos = new Alumno();
                alumnos.IdAlumno = String.valueOf(usuarioObject.getInt("IdAlumno"));
                alumnos.Dv = "0";
                alumnos.ApeMaterno = "0";
                alumnos.ApePaterno = "0";
                alumnos.Correo = "0";
                alumnos.EstadoMora = "0";
                alumnos.Nombres = "0";
                alumnos.Reserva = "0";
                alumnos.Telefono = "0";

                String alu = ser.getBasicHttpBindingIServicios().leerAlumno(alumnos.jaxbObjectToXML().getValue());

                JsonReader reader = Json.createReader(new StringReader(alu));
                JsonObject alumnoObject = reader.readObject();
                reader.close();

                Alumno nalu = new Alumno();
                nalu.IdAlumno =  String.valueOf(alumnoObject.getInt("IdAlumno"));
                nalu.Dv = alumnoObject.getString("Dv");
                nalu.Nombres = alumnoObject.getString("Nombres");
                nalu.ApePaterno = alumnoObject.getString("ApePaterno");
                nalu.ApeMaterno = alumnoObject.getString("ApeMaterno");
                nalu.ApeMaterno = alumnoObject.getString("ApeMaterno");
                nalu.Correo = alumnoObject.getString("Correo");
                nalu.Reserva =  String.valueOf(alumnoObject.getInt("Reserva"));
                nalu.Telefono =  String.valueOf(alumnoObject.getInt("Telefono"));
                nalu.EstadoMora = alumnoObject.getString("EstadoMora");

                request.setAttribute("alumno", nalu);

                sesion.setAttribute("usuario", usuario);
                request.getRequestDispatcher("Alumno.jsp").forward(request, response);
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
