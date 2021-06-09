/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Josip
 */
public class JAXBUtils {
    public static void save(Object obj, String fileName) throws JAXBException{
        JAXBContext content = JAXBContext.newInstance(obj.getClass());
        Marshaller  marshaler = content.createMarshaller();
        marshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaler.marshal(obj, new File(fileName));
    }

    public static Object load(Class clazz, String fileName) throws JAXBException{
        JAXBContext content = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = content.createUnmarshaller();
        return unmarshaller.unmarshal(new File(fileName));
    }
}
