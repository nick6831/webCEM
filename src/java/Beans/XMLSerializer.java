/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

/**
 *
 * @author portafolio
 */
public class XMLSerializer {
    public static void write(Object f, String filename) throws Exception{
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
        encoder.writeObject(f);
        encoder.close();
    }

    public static Object read(String filename) throws Exception {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
        Object o = (Object)decoder.readObject();
        decoder.close();
        return o;
    }
}
