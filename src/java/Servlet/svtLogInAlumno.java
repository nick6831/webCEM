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
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Response;

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
            
            Usuario usuario = new Usuario();

            usuario.setIdAlumno("");
            usuario.setNombreUsuario(user);
            usuario.setPassword(pass);
            usuario.setIdAdministrativo("");
            usuario.setIdRol("");
            usuario.setIdEncargadoCel("");
            usuario.setIdFamilia("");
            
//        JAXBContext contex = JAXBContext.newInstance(Usuario.class);
//        Marshaller m = contex.createMarshaller();
//        m.marshal(new JAXBElement(new QName(Usuario.class.getSimpleName()),Usuario.class,usuario), writer);
//        ObjectFactory fac = new ObjectFactory();
//        JAXBElement<String> str= fac.createCrearUsuarioXml(writer.toString());

//            Generar XML
            StringWriter writer = new StringWriter();

            JAXBContext jc = JAXBContext.newInstance(Usuario.class);
            QName QName = new QName(Usuario.class.getSimpleName());
            
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            marshaller.marshal(new JAXBElement(QName,Usuario.class,usuario),writer);
            ObjectFactory factory = new ObjectFactory();
            JAXBElement<String> str= factory.createCrearUsuarioXml(writer.toString());
            
            
            ValidarUsuario vali = new ValidarUsuario();
            ValidarUsuarioResponse resp = new ValidarUsuarioResponse();
           
            vali.setUserPass(str);
            
            if(resp.isValidarUsuarioResult())
            {
                response.sendRedirect("Alumno.jsp");
            }else
            {
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
