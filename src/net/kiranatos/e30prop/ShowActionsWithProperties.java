package net.kiranatos.e30prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/* Homework: ClassLoader cl = ClassLoader.getSystemClassLoader();
https://www.tutorialspoint.com/java/util/resourcebundle_getbundle_control.htm
https://javarush.ru/groups/posts/646-kak-proiskhodit-zagruzka-klassov-v-jvm
https://habr.com/ru/post/103830/
https://habr.com/ru/post/104229/
http://www.quizful.net/post/Java
http://java-online.ru/java-classloader.xhtml
https://www.baeldung.com/java-classloaders
https://www.codeflow.site/ru/article/java-classloaders
*/

public class ShowActionsWithProperties {
    
    public static void main(String[] args) throws IOException {     
        String currentDir = System.getProperty("user.dir") + File.separator;
        System.out.println("\nWorking Directory = " + currentDir);  //D:\Java\WorkSpace\netbeansWS\ExamplesWithoutPlugInLibs
        
        System.out.println("1) Creating properties file:");
        createEmailProperties();
        
        System.out.println("2.1) Read:");
        load1EmailProperties();
        System.out.println("2.2) Read:");
        load2FromClasspath(currentDir, "email.properties"); 
        System.out.println("2.3) Read: NOT WORK");
        //load3ViaResourceBundle(); NOT WORKING
        System.out.println("2.4) Read:");
        ResourceBundle rb = load4FromClassLoader(currentDir, "email");
        System.out.println("\nFrom bundle: key host = " + rb.getString("host"));
        System.out.println("2.5) Read:");
        ResourceBundle rb2 = load5FromFile(currentDir, "email.properties");
        System.out.println("\nFrom file: key password = " + rb2.getString("password"));
    }
    
    /***************** HOW TO CREATE PROPERTY *************************/
    
    public static void createEmailProperties() throws IOException {
        Properties p = new Properties();        
        p.setProperty("host", "smtp.mail.yahoo.com");
        p.setProperty("port", "465");
        p.setProperty("username","testuser");
        p.setProperty("password", "testpassword");
        FileWriter writer = new FileWriter("email.properties");
        p.store(writer, "email server configuration (Этот файл можно удалить - демонстрирующий класс их генерирует автоматически)");
        writer.close();
    }
    
    /***************** HOW TO GET PROPERTY *************************/
    
    // Read a properties file in Java
    public static Map<String,String> load1EmailProperties() throws IOException {
        Map<String,String> propertyMap = new HashMap();
        FileReader reader = new FileReader("email.properties");
        Properties p = new Properties();
        p.load(reader);
        Enumeration keys = p.propertyNames();
        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            propertyMap.put(key, p.getProperty(key));
            System.out.println(key + " : " + p.getProperty(key));
        }
        
        reader.close();
        return propertyMap;
    }
    
    /*How to Read Properties File from Classpath: If you want to read properties 
    file from the classpath, use the following function. This requirement is common in web applications.*/
    private static Map load2FromClasspath(String path, String file) throws IOException {
        System.out.println(path + file);
        Map<String, String> propertyMap = new HashMap();       
        InputStream stream = new FileInputStream(path + file);
        //InputStream stream = ShowActionsWithProperties.class.getResourceAsStream(path + file);
        Properties p = new Properties();
        p.load(stream);
        
        Enumeration keys = p.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            propertyMap.put(key, p.getProperty(key));
        }
        
        stream.close();
        return propertyMap;
    }
    
    private static void load3ViaResourceBundle() {
        ResourceBundle rb0 = ResourceBundle.getBundle("NoFile.properties");
        ResourceBundle rb1 = ResourceBundle.getBundle("test.res");
        ResourceBundle rb2 = ResourceBundle.getBundle("test.res", Locale.forLanguageTag("locale en_US"));
        System.out.println(rb0.getString("key"));        
    }
    
    private static ResourceBundle load4FromClassLoader(String dir, String bundleName) throws MalformedURLException {
        File file = new File(dir);
        URL[] urls = {file.toURI().toURL()};
        System.out.println("\nurls[0]: " + urls[0]);
        ClassLoader loader = new URLClassLoader(urls);
        // Properties file name = test.properties. The .properties extension is appended to bundle name
        return ResourceBundle.getBundle(bundleName, Locale.getDefault(), loader);
    }

    //This method doesn't use localization
    private static ResourceBundle load5FromFile(String dir, String fileName) throws IOException {        
        FileInputStream fis = new FileInputStream(dir + fileName);
        try {
            return new PropertyResourceBundle(fis);
        } finally {
            fis.close();
        }
    }
    
    
        
                
        
    
    /***************** OTHER METHODS *************************/
    
    
}
