package net.kiranatos;

import java.io.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;


/**  
 * @author Kiranatos
 * version: 2021-06-16-core
 * 
 * Tasks: 
 *  - will add randoms
 *  - add BREAK_LINE to getRandomText method
 */
public final class OzoHelper {
    
    public static enum Time { 
        MILLISECONDS(1), SECONDS(1000), MINUTE(60*1000);
        int coefficient;
        private Time(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getCoefficient() {
            return coefficient;
        }
    }
    public static enum RandomText { LATIN_LOWERCASE, LATIN_UPPERCASE, SPACE, NUMBERS, BREAK_LINE }
    
    private class OzoHelperException extends Error {
        public OzoHelperException(String message) {            
            super(message);
            System.out.println(message);
        }        
    }
    
    /**
     * Draw specific separator line or rectangle in panel
     * @param x chars in line
     * @param y lines
     * @param symbol 
     */
    public static void lineSeparator(int x, int y, char symbol) {
        if ((x < 1)&&(y < 1)) throw new OzoHelper().new OzoHelperException(" x, y must be > 0 ");
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                System.out.print(symbol);
            }
            System.out.println("");
        }
    }    
    
    /**
     * 
     * @param decoration ставить null, обрамление пока не реализовал
     * @param lines рядки
     */
    @Deprecated
    public static void printMe(String decoration, String... lines){
        System.out.println("");
        if (decoration==null){
            for (String line : lines) {
                System.out.println(line);                
            }
        }
    }
    
    /**
     * Усыпить поток.
     * @param value значение
     * @param type тип
     */
    public static void sleep(long value, Time type){
        try {
            Thread.sleep(value * type.getCoefficient());
            //TimeUnit.SECONDS.sleep(milliseconds);
        } catch (InterruptedException ex) { System.out.println("\n\n Time Errorr im method sleep! \n\n"); }
    }
        
    /**
     * Усыпить поток на @milliseconds
     */
    @Deprecated
    public static void sleepMilliseconds(long milliseconds){
        try {
            Thread.sleep(milliseconds);
            //TimeUnit.SECONDS.sleep(milliseconds);
        } catch (InterruptedException ex) { System.out.println("\n\n Errorr !!! \n\n"); }
    }
    /**
     * Усыпить поток на @seconds
     */
    @Deprecated
    public static void sleepSeconds(long seconds){
        try {
            Thread.sleep(seconds * 1000);
            //TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ex) { System.err.println("\nInterruptedException in class OzoHelper, in method sleepSeconds"); }
    }
    /**
     * Усыпить поток на @minute
     */
    @Deprecated
    public static void sleepMinute(int minute){
        try {
            Thread.sleep(minute*60*1000);
            //TimeUnit.SECONDS.sleep(minute);
        } catch (InterruptedException ex) { System.out.println("\n\n Errorr !!! \n\n"); }
    }
    
    /**
     * Чтение рядка с панели
     */
    public static String getRead() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String theChoice = "empty string";
        
        try {
            theChoice = reader.readLine();
        } catch (IOException ex) {
            System.out.println("__ Reading Error!!! __");
            Logger.getLogger(OzoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return theChoice;
    }
    
    /**
     * Get List of files in folder including subfolders
     * 
     * @param path to folder with files
     */
    public static List<String> getListOfFiles(String path) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            // We want to find only regular files
            result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }                
        return result;
    }
    
    /**
     * Check if file or folder exist
     * @param path
     * @return 
     */
    public static boolean fileExist(String path) {
        File file = new File(path);
        return file.exists();
    }
    
    /**
     * Get text from file
     * @param path
     * @return text
     */
    public static String getTextFromFile(String path) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            InputStream fileInputStream = new FileInputStream(new File(path));
            while ( fileInputStream.available() > 0 ) { 
                byteArrayOutputStream.write(fileInputStream.read());
            }
            fileInputStream.close();
        } catch (IOException e){ 
            throw new OzoHelper().new OzoHelperException("Error in reading file : " + path + "! "); 
        }
        return byteArrayOutputStream.toString();
    }
    
    /**
     * Save Text to file
     * @param text
     * @param path
     * @param name 
     */
    public static File saveTextToFile(String text, String path, String name) {        
        File file = new File(path, name);
        try {
            OzoHelper ozHelp = new OzoHelper();            
            path = ozHelp.checkInfoAboutFolder(path);            
            OutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());            
        } catch (IOException e){ 
            throw new OzoHelper().new OzoHelperException("Error while writing file : " + path + File.separator + name + "! "); 
        }        
        return file;
    }
    
  /**
   * Create bunch of random files with random text inside in folder for testing purpose
   * @param pathToFolder folder where need to create files (example: "wfiles\\myFolder")
   * @param nameMaskOfFiles mask for files' name (example: "note%d.txt") %d is mandatory
   * @param amount how much files you need. (<code>amount > 0</code>)
   * @param sizePerOneFile yow much bytes (1 byte = 1 char)
   * @throws IOException 
   */
    public static File[] createFewFiles(String pathToFolder, String nameMaskOfFiles, int amount, int sizePerOneFile) {
        OzoHelper ozHelp = new OzoHelper();
        if (amount < 1) { throw ozHelp.new OzoHelperException("Wrong amount of files : " + amount + "! "); }
        pathToFolder = ozHelp.checkInfoAboutFolder(pathToFolder);
        File[] fileMatrix = new File[amount];
        int index = 0;
        for (int i = 0; i < amount; i++) {
            String fileName = String.format(nameMaskOfFiles, i);
            File file = new File(pathToFolder, fileName);
            try {
                OutputStream fileOutputStream = new FileOutputStream(file);
                for (int j = 0; j < sizePerOneFile; j++) {
                    fileOutputStream.write(getRandomText(amount, 
                            RandomText.LATIN_UPPERCASE, 
                            RandomText.LATIN_LOWERCASE, 
                            RandomText.SPACE, 
                            RandomText.NUMBERS, 
                            RandomText.BREAK_LINE).getBytes());
                }
                fileOutputStream.close();
            } catch (IOException e) { throw ozHelp.new OzoHelperException("Error in method createFewFiles() in OzoHelper class! "); }
            fileMatrix[index++] = file;
        }   
        return fileMatrix;
    } 
    // auxiliary private method for createFewFiles method
    private String checkInfoAboutFolder(String pathToFolder){        
        File file = new File(pathToFolder);
        boolean success = false;
        if (file.exists()) {
            if (file.isFile()) {
                return file.getParent(); // return path to file
            } else if (file.isDirectory()) {
                return pathToFolder; // path is ok
            } else {
                throw new OzoHelperException("Wrong folder path: its nor a file, nor a folder! ");
            }
        } else { // directory does not exist
            success = file.mkdirs();  // creating all un-existing directories
            if (!success) { throw new OzoHelperException("Folder path error: couldn't create a directory! "); }
        }
        return pathToFolder; // all directories from pathToFolder was created and path returned
    }
    
    /**
     * Get Random Text
     * @param amountOfChars
     * @param typesOfChar
     * @return 
     */
    public static String getRandomText(int amountOfChars, RandomText... typesOfChar) {        
        Set<Integer> setOfChars = new TreeSet<>();
        for (RandomText rt : typesOfChar) {
            switch (rt) {
                case LATIN_LOWERCASE : 
                    for (int i = 97; i < 123; i++) {
                        setOfChars.add(i);
                    }
                    break;
                case LATIN_UPPERCASE : 
                    for (int i = 65; i < 91; i++) {
                        setOfChars.add(i);
                    }
                    break;
                case NUMBERS : 
                    for (int i = 48; i < 58; i++) {
                        setOfChars.add(i);
                    }
                    break;
                case SPACE : 
                    setOfChars.add(32);
                    break;
                case BREAK_LINE : 
                    setOfChars.add(0);
                    break;
                default: throw new OzoHelper().new OzoHelperException(" Undefined error with ASCII chars! ");
            }
        }
                
        StringBuilder text = new StringBuilder();
        Integer[] matrixInt = new Integer[setOfChars.size()];
        matrixInt = setOfChars.toArray(matrixInt);
        for (int i = 0; i < amountOfChars; i++) {            
            int charCode = getRandomElementFromMatrix(matrixInt);
            if (charCode != 0)
                text.append((char)charCode);
            else 
                text.append(System.lineSeparator());
        }        
        return text.toString();
    }
    
    /**
     * Get random element from matrix
     * @param <T>
     * @param matrix
     * @return one element of matrix
     */
    public static <T> T getRandomElementFromMatrix(T[] matrix) {
        //Random rand = new Random(new Date().getTime());        
        int index = (int)(Math.random()* matrix.length);
        return matrix[index];
    }
    
    /**
     * Get random integer number from 0 to N
     * 0 - included, N - not included
     * @param n
     * @return 
     */
    public static int getRandomFromZeroToN(int n) {
        return (int)(Math.random() * n);
    }
    
    /**
     * Check regular expression in text and output them
     * @param regex
     * @param text string or text
     * @param separator
     * @return true if at least one string was found, false if nothing found
     */
    public static boolean checkRegExp(String regex, String text, String separator) {        
        Matcher matcher = Pattern.compile(regex).matcher(text);
        StringBuilder sb = new StringBuilder();        
        while (matcher.find()) {
            sb.append(matcher.group()).append(separator);            
        }
        if (sb.length() > 0) { 
            sb = sb.delete(sb.lastIndexOf(separator), sb.length());
            System.out.println(sb);
            return true;
        } else return false;
    }
    
    public File getFile(String message) {
        System.out.println(message);               
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = null;        
        try {
            file = new File(reader.readLine());
            
            while( !file.exists() || file.isDirectory() || !file.isFile()) {
                System.out.println("Файла не существует! Введите путь:");
                file = new File(reader.readLine());
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в методе getFile класса HelpMethods");            
        }
        return file;                
    }
}