package net.kiranatos.e13pojo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import net.kiranatos.OzoHelper;

public class JavaBeansPOJO {
    private final static String PATH_TO_XML = "wfiles\\e13\\ForPOJOExample.xml";
    public static void main(String[] args) {
        System.out.println("Запись POJO-объекта в XML");
        try (XMLEncoder xmlEncoder = new XMLEncoder(
                        new BufferedOutputStream(new FileOutputStream(PATH_TO_XML)))) {
            PojoExample pojoWrite = new PojoExample(100, "some info");
            xmlEncoder.writeObject(pojoWrite);
            xmlEncoder.flush();            
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        
        OzoHelper.sleep(20, OzoHelper.Time.SECONDS);
        
        System.out.println("Чтение POJO-объекта из XML");        
        try (XMLDecoder xmlDecoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(PATH_TO_XML)))) {
            PojoExample pojoRead = (PojoExample)xmlDecoder.readObject();
            System.out.println(pojoRead);            
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }
}
