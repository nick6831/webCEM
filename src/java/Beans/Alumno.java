/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Servicios_Cem.ObjectFactory;
import com.sun.istack.Nullable;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 *
 * @author nickm
 */
public class Alumno {
        @Nullable
        public String IdAlumno;
        @Nullable
        public String Dv;
        @Nullable
        public String Nombres;
        @Nullable
        public String ApePaterno; 
        @Nullable
        public String ApeMaterno; 
        @Nullable
        public String Correo;
        @Nullable
        public String Reserva;
        @Nullable
        public String Telefono;
        @Nullable
        public String EstadoMora;

    public Alumno() {
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
