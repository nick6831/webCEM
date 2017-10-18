/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.Alumno;
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
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Response;
import org.w3c.dom.NodeList;

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

            usuario.IdUsuario = "0";
            usuario.NomUsuario = user;
            usuario.Password = pass;
            usuario.IdAlumno = "0";
            usuario.IdAdministrativo = "0";
            usuario.IdRol = "0";
            usuario.IdFamilia = "0";
            usuario.IdEncargadoCel = "0";
           
//            Generar XML
            StringWriter writer = new StringWriter();

            JAXBContext jc = JAXBContext.newInstance(Usuario.class);
            QName QName = new QName(Usuario.class.getSimpleName());
            
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16");
            
            marshaller.marshal(new JAXBElement(QName,Usuario.class,usuario),writer);
            ObjectFactory factory = new ObjectFactory();
            JAXBElement<String> str = factory.createCrearUsuarioXml(writer.toString());
            
            Servicios ser = new Servicios();
            
            boolean b = ser.getBasicHttpBindingIServicios().validarUsuario(str.getValue());
//            String str2 = ser.getBasicHttpBindingIServicios().leerUsuario(str.getValue());

            Alumno alumnos = new Alumno();
            alumnos.IdAlumno = "17880166";
            alumnos.Dv = "0";
            alumnos.ApeMaterno = "0";
            alumnos.ApePaterno = "0";
            alumnos.Correo = "0";
            alumnos.EstadoMora = "0";
            alumnos.Nombres = "0";
            alumnos.Reserva = "0";
            alumnos.Telefono = "0";
            
            StringWriter writer2 = new StringWriter();

            JAXBContext jc2 = JAXBContext.newInstance(Alumno.class);
            QName QName2 = new QName(Alumno.class.getSimpleName());
            
            Marshaller marshaller2 = jc2.createMarshaller();
            marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller2.setProperty(Marshaller.JAXB_ENCODING, "UTF-16");
            
            marshaller2.marshal(new JAXBElement(QName2,Alumno.class,alumnos),writer2);
            ObjectFactory factory2 = new ObjectFactory();
            JAXBElement<String> str2 = factory2.createCrearUsuarioXml(writer2.toString());

            Servicios ser2 = new Servicios();
            
            String alu = ser2.getBasicHttpBindingIServicios().leerAlumno(str2.getValue());
            

            
//            Forma 2 
//            JAXBContext jaxbContext = JAXBContext.newInstance(Alumno.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            StringReader reader = new StringReader(alu);
//            Alumno alum = (Alumno)unmarshaller.unmarshal(reader);

            if(b)
            {
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("Alumno.jsp");
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
