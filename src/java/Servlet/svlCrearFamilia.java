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
import Beans.Familia;
import Beans.Usuario;
import Servicios_Cem.*;
import javax.xml.bind.JAXBElement;
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
@WebServlet(name = "svlCrearFamilia", urlPatterns = {"/svlCrearFamilia"})
public class svlCrearFamilia extends HttpServlet {

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
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String apellidoPaterno = request.getParameter("apellidoPaterno");
            String apellidoMaterno = request.getParameter("apellidoMaterno");
            String identificacion = request.getParameter("identificacion");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String pais = request.getParameter("pais");
            String ciudad = request.getParameter("ciudad");
            String estado = request.getParameter("estado");
            String pass = request.getParameter("id_pass");
            
            Familia fam = new Familia();
            fam.setId(id);
            fam.setNombre(nombre);
            fam.setApellidoPaterno(apellidoPaterno);
            fam.setApellidoMaterno(apellidoMaterno);
            fam.setIdentificacion(identificacion);
            fam.setCorreo(correo);
            fam.setTelefono(telefono);
            fam.setDireccion(direccion);
            fam.setPais(pais);
            fam.setCiudad(ciudad);
            fam.setEstado(estado);
            
            Usuario user = new Usuario();
            
            user.IdFamilia = id;
            user.Password = pass;
            
            createUser(user);
            createfam(fam);
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

        private void createfam(Familia fam) throws JAXBException {
            StringWriter writer= new StringWriter();
            JAXBContext con = JAXBContext.newInstance(Familia.class);
            Marshaller m = con.createMarshaller();
            m.marshal(new JAXBElement(new QName(Familia.class.getSimpleName()),Familia.class,fam), writer);
            ObjectFactory fac = new ObjectFactory();
            JAXBElement<String> alum = fac.createCrearAlumnoXml(writer.toString());
            CrearFamiliaAnfitriona crear = new CrearFamiliaAnfitriona();
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
