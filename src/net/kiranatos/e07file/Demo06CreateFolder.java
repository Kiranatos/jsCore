package net.kiranatos.e07file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo06CreateFolder {
    private static final String folderPath = "wfiles/e07/demo06";

    public static void main(String[] args) {       
        /*     Java NIO
1.1 We can use Files.createDirectory to create a directory.

    If the parent directories not exist, throws NoSuchFileException.
    If the directory exists, throws FileAlreadyExistsException.
    If IO errors, throws IOException.

  Path path = Paths.get("/home/mkyong/test2/");
  Files.createDirectory(path);

1.2 We can use Files.createDirectories creates a directory including all nonexistent parent directories.

    If the parent directories not exist, create it first.
    If the directory exists, no exception thrown.
    If IO errors, throws IOException

  Path path = Paths.get("/home/mkyong/test2/test3/test4/");
  Files.createDirectories(path);        */
        try {
            Path path = Paths.get(folderPath);
            Files.createDirectories(path);
            System.out.println("Directory is created!");
            //Files.createDirectory(path);
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }
        
        /* Legacy IO
For legacy IO java.io.File, the similar methods are file.mkdir() to create a 
        directory, and file.mkdirs() to create a directory including all 
        nonexistent parent directories.
Both file.mkdir() and file.mkdirs() returns a boolean, true if success to 
        create the directory, fail otherwise, no exception thrown.
In legacy IO, the lack of exception thrown in creating directory makes 
        developers very hard to debug or understand why we cannot create a 
        directory, and this is one of the reasons Java releases a new java.nio.Files 
        to throw a proper exception.          */
        File file = new File(folderPath);
        // true if the directory was created, false otherwise
        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }
    }
}
