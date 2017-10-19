/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author nickm
 */
public class Common {
    public static String jaxbObjectToXML(Object customer) {
    String xmlString = "";
    try {
        JAXBContext context = JAXBContext.newInstance(Object.class);
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-16");
        
        StringWriter sw = new StringWriter();
        m.marshal(customer, sw);
        xmlString = sw.toString();

    } catch (JAXBException e) {
        e.printStackTrace();
    }

    return xmlString;
}
}
