package net.kiranatos.e07file;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;

public class Demo01File {
    private static String sourceDirName = "wfiles\\e07";
    static{
        OzoHelper.createFewFiles(sourceDirName, "junkFile%d.txt", 10, 200);
    }    
    private static String strPathTofile = "wfiles\\e07\\junkFile0.txt";
    
    public static void main(String[] args) throws IOException {
        File fileWindows = new File("F:\\test1.txt"); // Windows
        File fileUnix = new File("/user/bin/ls"); // Unix
        
        System.out.println("\n Pазделители в путях для файлов, в зависимости от операционной системы: ");        
        String str_separ = File.separator;
        char ch_separ = File.separatorChar;
        System.out.println("Separators: " + str_separ + " and " + ch_separ);
        
        System.out.println("\n Pазделители между путями в свойствах ситемы \";\" - для Windows \":\" - для Unix");
        str_separ = File.pathSeparator;
        ch_separ = File.pathSeparatorChar;
        System.out.println("Separators: " + str_separ + " and " + ch_separ);
        
        // Mожно также склееить путь и файл непосредственно в самом классе File
        File file01 = new File("H:\\Folder", "filename.txt");
        
        System.out.println("\n\tisAbsolute?");
        System.out.println(new File("H:\\MyFolder\\file.txt").isAbsolute()); // true
        System.out.println(new File("MyFolder\\file.txt").isAbsolute()); // false
                        
        System.out.println(".getAbsolutePath() = " +  
                new File("MyFolder\\file.txt").getAbsolutePath());
        
        File file02 = new File("MyFolder\\file.txt").getAbsoluteFile();        
        System.out.println(".getPath() = " + file02.getPath()); // D:\Java\WorkSpace\netbeansWS\SCore\MyFolder\file.txt
        System.out.println(".getName() = " + file02.getName()); // file.txt
        System.out.println(".getParent() = " + file02.getParent()); // D:\Java\WorkSpace\netbeansWS\SCore\MyFolder
        
        System.out.println("\n Для сравнения путей использовать: ");
        File fileCanon = file02.getCanonicalFile();
        String s_Canon = file02.getCanonicalPath();
        System.out.println(".getCanonicalFile() = " + s_Canon);
        System.out.println(".getCanonicalPath() = " + fileCanon);        
        
        System.out.println("\n Как получить информацию о файле");
        File file03 = new File(strPathTofile);
        System.out.println(".exists() = " + file03.exists());
        System.out.println(".isFile() = " + file03.isFile());
        System.out.println(".isDirectory() = " + file03.isDirectory());
        System.out.println(".length() = " + file03.length());
        System.out.println(".lastModified() = " + file03.lastModified());
        
        System.out.println("\n How to get list of files from folder:");
        file03 = new File(sourceDirName);
        String[] _strArrayOfFiles = file03.list();
        File[] _arrayOfFiles = file03.listFiles();
        for (String _ss : _strArrayOfFiles) {
            System.out.println(_ss);
        }
        
        System.out.println("\nПример создания temp-овского файла:");
        File fileTemp = File.createTempFile("temp",".txt", new File(sourceDirName)); // папка должна существовать изначально, иначе exception
        System.out.printf("File %s has created!%n", fileTemp.getAbsolutePath());
        
        System.out.printf("\nDelete file %s : %b!%n", fileTemp.getName(), fileTemp.delete());
        System.out.printf("\nDelete folder %s : %b!%n", file03.getName(), file03.delete());
        // Невозможно удалить папку, если в ней есть файлы. Нужно удалить сначала все файлы.
    }    
}