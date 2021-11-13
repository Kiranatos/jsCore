package net.kiranatos.e07file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.kiranatos.OzoHelper;

public class Demo03RandomAccessFile {
    private final static String DIR_NAME = "wfiles\\e07";
    static{
        OzoHelper.createFewFiles(DIR_NAME, "demo03rw%d.txt", 1, 50);        
    }    
    private static File file = new File(DIR_NAME, "demo03rw0.txt");
    
    public static void main(String[] args) throws IOException {
        System.out.println("\tReading and Writing file via class RandomAccessFile(File/String, r/rw/rws/rwd)");
        RandomAccessFile fileRandomAccessFile = new RandomAccessFile(file, "rw");
        
        for (int i = 65; i < 75; i++) {
            fileRandomAccessFile.write(i); // пишет побайтово в начале, затирая байты в файле            
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.print((char)fileRandomAccessFile.read()); // читает с той позиции, где остановился курсор
        }
        
        fileRandomAccessFile.seek(file.length()); // переход в конец файла
        
        for (int i = 0; i < 10; i++) {
            fileRandomAccessFile.write(System.getProperty("line.separator").getBytes());
            fileRandomAccessFile.writeBytes("string " + i); // Пишет построчно. Если курсор в начале - затирает байты в файле            
        }
        fileRandomAccessFile.close();
        System.out.println("");
    }
}