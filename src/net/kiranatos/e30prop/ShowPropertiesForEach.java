package net.kiranatos.e30prop;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/* source:
How to iterate Properites in Java
https://www.boraji.com/how-to-iterate-properites-in-java
(законспектированно, но на сайте можно найти много других статей)
*/

public class ShowPropertiesForEach {
    private static Properties props = new Properties();
    static {
        props.setProperty("1", "One");
        props.setProperty("2", "Two");
        props.setProperty("3", "Three");
        props.setProperty("4", "Four");
        props.setProperty("5", "Five");
    }
    
    public static void main(String[] args) {
        System.out.println("\n\n\t**** **** class Properites **** ****\n"
                + "\t Iterating properties using Enumeration:");        
        @SuppressWarnings("unchecked")
        Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + " : " + value);
        }
        
        System.out.println("\t Iterating Properties using For-Each loop + Properties's stringPropertyNames() method:");        
        Set<String> keys = props.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + " : " + props.getProperty(key));
        }
        
        System.out.println("\t Iterating Properties using For-Each loop + entry set:");
        Set<Entry<Object, Object>> entries = props.entrySet();
        for (Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        System.out.println("\t Iterating Properties using forEach() method:");
        props.forEach((key, value) -> System.out.println(key + " : " + value));
        
        // all above working without files
                
        // Homework: correct code below:
//        ResourceBundle rb = ResourceBundle.getBundle("test.res");
//		Enumeration <String> keys = rb.getKeys();
//		while (keys.hasMoreElements()) {
//			String key = keys.nextElement();
//			String value = rb.getString(key);
//			System.out.println(key + ": " + value);
//		}
    }    
}
