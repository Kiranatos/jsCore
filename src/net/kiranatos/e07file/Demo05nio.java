package net.kiranatos.e07file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.kiranatos.OzoHelper;

public class Demo05nio {
    private final static String DIR_NAME = "wfiles\\e07";
    static{ OzoHelper.createFewFiles(DIR_NAME, "demo05nio%d.txt", 1, 50); }    
    private static File file = new File(DIR_NAME, "demo05nio0.txt");

    public static void main(String[] args) {    
        System.out.println("\n**** ***** Working with files << Java.NIO >> **** ****\n");
        Path pathF = Paths.get(file.getAbsolutePath());
        
        System.out.println(".getFileName() = " + pathF.getFileName());                
        System.out.println(".getParent() = " + pathF.getParent());        
        System.out.println(".getRoot() = " + pathF.getRoot());
        System.out.println(".isAbsolute() = " + pathF.isAbsolute());        
        
        System.out.println(".startsWith() & .endsWith() methods, chech pathes, not symbols:");        
        System.out.println("\t" + pathF.endsWith("emo05nio0.txt")); //false
        System.out.println("\t" + pathF.endsWith("demo05nio0.txt")); //true
        System.out.println("\t" + pathF.endsWith("\\demo05nio0.txt")); //false
        System.out.println("\t" + pathF.endsWith(DIR_NAME + "\\demo05nio0.txt")); //true
        System.out.println("\t" + pathF.startsWith(DIR_NAME)); //false
        System.out.println("\t" + pathF.startsWith(pathF.getRoot()) + " for " + pathF.getRoot()); //true        
               
        System.out.println("«Hормализует» текущий путь, “.”(текущая директория) и “..” родительская директория): ");
        Path path5 = Paths.get("C:\\Users\\Java\\.\\examples");
        System.out.println(path5.normalize());
        Path path6 = Paths.get("C:\\Users\\Java\\..\\examples");
        System.out.println(path6.normalize());
        
        System.out.println("Path relativize() вычисляет относительный путь между текущим и переданным путем");
        Path testFilePath1 = Paths.get("C:\\Users\\Users\\Users\\Users");
        Path testFilePath2 = Paths.get("C:\\Users\\Users\\Users\\Users\\Username\\Desktop\\testFile.txt");
        System.out.println(testFilePath1.relativize(testFilePath2));
    }    
}
        /*
        https://javarush.ru/groups/posts/2275-files-path
        https://javarush.ru/quests/lectures/questcollections.level01.lecture01
        
        InputStream iS1 = new FileInputStream(path.toFile());
        InputStream iS2 = Files.newInputStream(path);
        
        InputStream iS = Files.newInputStream(Paths.get("in.txt"));
        OutputStream oS = Files.newOutputStream(Paths.get("out.txt"));
        */