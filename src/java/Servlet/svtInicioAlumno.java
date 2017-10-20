/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.Alumno;
import Beans.Usuario;
import Servicios_Cem.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickm
 */
@WebServlet(name = "svtInicioAlumno", urlPatterns = {"/svtInicioAlumno"})
public class svtInicioAlumno extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            Usuario user = (Usuario)request.getSession().getAttribute("usuario");
            Servicios ser = new Servicios();
            
            Alumno alumnos = new Alumno();
            alumnos.IdAlumno = user.IdAlumno;
            alumnos.Dv = "0";
            alumnos.ApeMaterno = "0";
            alumnos.ApePaterno = "0";
            alumnos.Correo = "0";
            alumnos.EstadoMora = "0";
            alumnos.Nombres = "0";
            alumnos.Reserva = 0;
            alumnos.Telefono = 0;
            
            String alu = ser.getBasicHttpBindingIServicios().leerAlumno(alumnos.Json());
            
            JsonReader reader = Json.createReader(new StringReader(alu));
            JsonObject alumnoObject = reader.readObject();
            reader.close();
            
            Alumno nalu = new Alumno();
            nalu.IdAlumno =  alumnoObject.getInt("IdAlumno");
            nalu.Dv = alumnoObject.getString("Dv");
            nalu.Nombres = alumnoObject.getString("Nombres");
            nalu.ApePaterno = alumnoObject.getString("ApePaterno");
            nalu.ApeMaterno = alumnoObject.getString("ApeMaterno");
            nalu.ApeMaterno = alumnoObject.getString("ApeMaterno");
            nalu.Correo = alumnoObject.getString("Correo");
            nalu.Reserva =  alumnoObject.getInt("Reserva");
            nalu.Telefono =  alumnoObject.getInt("Telefono");
            nalu.EstadoMora = alumnoObject.getString("EstadoMora");
            
            request.setAttribute("alumno", nalu);
            
            request.getRequestDispatcher("Alumno.jsp").forward(request, response);
            
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
