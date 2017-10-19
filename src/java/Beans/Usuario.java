/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Servicios_Cem.ObjectFactory;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 *
 * @author portafolio
 */
public class Usuario {
        public String IdUsuario;
        public String NomUsuario;
        public String Password;
        public String IdAlumno;
        public String IdAdministrativo;
        public String IdRol;
        public String IdFamilia;
        public String IdEncargadoCel;

    public Usuario() {
    }
    
    public JAXBElement<String> jaxbObjectToXML() {
    JAXBElement<String> str = null;
    try {
        StringWriter writer = new StringWriter();
        
        JAXBContext context = JAXBContext.newInstance(this.getClass());
        QName QName = new QName(this.getClass().getSimpleName());
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-16");
        
        m.marshal(new JAXBElement(QName,this.getClass(),this),writer);
        ObjectFactory factory = new ObjectFactory();
        str = factory.createCrearUsuarioXml(writer.toString());
    } catch (JAXBException e) {
        e.printStackTrace();
    }

    return str;
    }
}
