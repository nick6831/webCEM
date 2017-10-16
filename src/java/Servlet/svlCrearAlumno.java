/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Beans.Alumno;
import Beans.Usuario;
import Beans.XMLSerializer;
import Servicio_de_Servicio.*;
import javax.xml.bind.JAXBElement;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
/**
 *
 * @author nickm
 */
@WebServlet(name = "svlCrearAlumno", urlPatterns = {"/svlCrearAlumno"})
public class svlCrearAlumno extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String id_alumno = request.getParameter("id_alumno");
            String nombre = request.getParameter("id_nombre");
            String apellido_pa = request.getParameter("id_apellidop");
            String apellido_ma = request.getParameter("id_apellidom");
            String correo= request.getParameter("id_correo");
            int telefono= Integer.parseInt(request.getParameter("id_telefono"));
            String pass=request.getParameter("id_pass");
            
            
            Alumno alu = new Alumno();
            alu.setIdAlumno(alu.ObtenerRut(id_alumno));
            alu.setDv(alu.ObtenerDv(id_alumno));
            alu.setNombres(nombre);
            alu.setApePaterno(apellido_pa);
            alu.setCorreo(correo);
            alu.setTelefono(telefono);
            Usuario user = new Usuario();
            user.setCorreo(correo);
            user.setContrasenia(pass);
            user.setId(id_alumno);
            createalum(alu);
            createUser(user);
            
           
       
           
            
        }
        
    }

    private void createUser(Usuario user) throws JAXBException {
        StringWriter writer= new StringWriter();
        JAXBContext con = JAXBContext.newInstance(Usuario.class);
        Marshaller m = con.createMarshaller();
        m.marshal(new JAXBElement(new QName(Usuario.class.getSimpleName()),Usuario.class,user), writer);
        ObjectFactory fac = new ObjectFactory();
        JAXBElement<String> us = fac.createCrearAlumnoXml(writer.toString());
        CrearUsuario crear = new CrearUsuario();
        crear.setXml(us);
    }

    private void createalum(Alumno alu) throws JAXBException {
        StringWriter writer= new StringWriter();
        JAXBContext con = JAXBContext.newInstance(Alumno.class);
        Marshaller m = con.createMarshaller();
        m.marshal(new JAXBElement(new QName(Alumno.class.getSimpleName()),Alumno.class,alu), writer);
        ObjectFactory fac = new ObjectFactory();
        JAXBElement<String> alum = fac.createCrearAlumnoXml(writer.toString());
        CrearAlumno crear = new CrearAlumno();
        crear.setXml(alum);
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
            Logger.getLogger(svlCrearAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(svlCrearAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
