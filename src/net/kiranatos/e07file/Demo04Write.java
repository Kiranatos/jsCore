package net.kiranatos.e07file;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Demo04Write {
    private final static String DIR_NAME = "wfiles\\e07";    
    private static File file1 = new File(DIR_NAME, "demo04FileWriter1.txt");
    private static File file2 = new File(DIR_NAME, "demo04FileWriter2.txt");
    private static File file3 = new File(DIR_NAME, "demo04os.txt");
    private static File file4 = new File(DIR_NAME, "demo04pw.txt");
    
    public static void main(String[] args) throws IOException {        
        System.out.printf("\t 1) Writing file %s !%n", file1.getName());
        BufferedWriter fileBufferedWriter = new BufferedWriter(new FileWriter(file1)); // new FileWriter(string/file)
        for (int i = 0; i < 10; i++) {
            fileBufferedWriter.write(fileBufferedWriter.getClass().getName() + " string #" + i);
            fileBufferedWriter.newLine();
        }
        fileBufferedWriter.close();
        
        System.out.printf("\n\t 2) Writing file %s by line!%n", file2.getName());
        FileWriter fileWriter = new FileWriter (file2.getAbsolutePath());
        for (int i = 0; i < 10; i++) {
            fileWriter.write(fileWriter.getClass().getName() + " \t " + fileWriter.getEncoding());
            fileWriter.write(System.getProperty( "line.separator" ));
        }
        fileWriter.close();
        
        System.out.printf("\n\t 3) Writing file %s by byte!%n", file3.getName());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 65; i < 91; i++) {
            byteArrayOutputStream.write(i);
        }        
        OutputStream fileOutputStream = new FileOutputStream (file3);        
        fileOutputStream.write(byteArrayOutputStream.toByteArray()); /* <- можно записывать так 
        или вот так -> */  byteArrayOutputStream.writeTo(fileOutputStream); /*в данном примере информация запишется дважды!*/
        fileOutputStream.close();
        /* Еще вариант:
        Charset c = StandardCharsets.UTF_8;
        Writer writer = new OutputStreamWriter(fileOutputStream, c); */
                        
        System.out.printf("\n\t 4) Writing file %s !%n", file4.getName());        
        System.out.println("(инструментов и вариантов записи много, побайтово, построчно, в файл, на панель, в массив)");
        System.out.println("(см. этот же класс в примерах работы с панелью)");
        PrintWriter pw = new PrintWriter(file4); // new PrintWriter(File/OutputStream/String/Writer)
        //PrintWriter pt = new PrintWriter(new FileWriter(new File("file.txt")));
        for (int i = 0; i < 10; i++) {
            pw.write(pw.getClass().getName());
            pw.println();
            pw.printf(" string #%d ", i);
            pw.println(" *** ");
        }
        pw.close(); 
        /*Example of using PrintWriter in project Demo03JSON class JsonHandlerServlet:
        resp.getWriter().write(json);  */
    }
}

