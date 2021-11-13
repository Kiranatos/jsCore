package net.kiranatos.e07file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;
import net.kiranatos.OzoHelper;

public class Demo02Read {
    private final static String DIR_NAME = "wfiles\\e07";
    static{
        OzoHelper.createFewFiles(DIR_NAME, "demo02read%d.txt", 9, 10);        
    }    
    private static File file1 = new File(DIR_NAME, "demo02read1.txt");
    private static File file2 = new File(DIR_NAME, "demo02read2.txt");
    private static File file3 = new File(DIR_NAME, "demo02read3.txt");
    
    public static void main(String[] args) throws IOException {
        System.out.printf("\t 01) Reading by line in file %s :%n", file1.getName());
        BufferedReader readerFile = new BufferedReader(new FileReader(file1));
        String s = readerFile.readLine();
        while (s != null) { // while ((s = readerFile.readLine()) != null) {
            System.out.println(s);
            s = readerFile.readLine();
        }
        readerFile.close();
        
        System.out.printf("\n\t 02) Reading by byte in file %s :%n", file2.getName());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream fileInputStream = new FileInputStream(file2);
        /* other variants:
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        Reader reader = new InputStreamReader(fileInputStream, "UTF-8"); */
        while ( fileInputStream.available() > 0 ) { 
            int a = fileInputStream.read();
            char c = (char)a; // сделать приведение типов, чтобы получить char
            byteArrayOutputStream.write(a);
            System.out.print(c);
        }
        fileInputStream.close();
        
        System.out.printf("\n\n\t 03) Reading by line in file %s :%n", file3.getName());        
        Scanner file = new Scanner(new File(file3.getAbsolutePath())); 
        while ( file.hasNextLine() ) { 
            String str = file.nextLine();
            System.out.println(str);
        }
        file.close();
    }
}
