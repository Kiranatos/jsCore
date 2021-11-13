package net.kiranatos.e11zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DemoZipArchive {
    private static final String INPUT_TXT_FILE = "rfiles\\e11\\text.txt";
    private static final String OUTPUT_ZIP_FILE = "wfiles\\e11\\output.zip";
    private static final String PATH_FOR_UNZIPPED_FILES = "wfiles\\e11\\unzipped\\";
    private static final String ENTRY_NAME = "notes1.txt";
    
    public static void main(String[] args) {
        DemoZipArchive test = new DemoZipArchive();
        test.archiveFileInZip(INPUT_TXT_FILE, OUTPUT_ZIP_FILE, ENTRY_NAME); //file "text1.txt" was renamed in "notes1.txt"
        test.unzipFiles(OUTPUT_ZIP_FILE, PATH_FOR_UNZIPPED_FILES);
    }

    /**
     * Write one file in ZIP-archive
     * @param file path to file, that will be archived
     * @param pathToArchive path, where need to create archive
     * @param entryMask name or rename for {@code file} in archive
     */
    public void archiveFileInZip(String file, String pathToArchive, String entryMask) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToArchive));
                FileInputStream fis = new FileInputStream(file);) {
            /* Для записи файлов в архив для каждого файла создается объект ZipEntry,
            в конструктор которого передается имя архивируемого файла. А чтобы 
            добавить каждый объект ZipEntry в архив, применяется метод putNextEntry().*/
            ZipEntry entry1 = new ZipEntry(entryMask);
            zout.putNextEntry(entry1);
            
            /* После добавления объекта ZipEntry в поток, также надо добавить и содержимое файла.
            Используя метод, записывающий в поток массив байтов: .write(buffer)*/
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            
            /*Закрыть ZipEntry методом closeEntry(). После этого можно добавлять
            в архив новые файлы - в этом случае все вышеописанные действия для 
            каждого нового файла повторяются.*/
            zout.closeEntry();
        } catch (Exception ex) { System.out.println(ex.getMessage()); }
    }
    
    /**
     * Read and unzip ZIP-archive
     * @param pathToArchive path, where archive situated
     * @param pathToUnzip path, where need to write unzipped files
     */
    public void unzipFiles(String pathToArchive, String pathToUnzip) {
        /* Для считывания файлов из архива ZipInputStream использует метод getNextEntry(), 
        который возвращает объект ZipEntry. Объект ZipEntry представляет отдельную 
        запись в zip-архиве.
        ZipInputStream в цикле выводит все файлы и их размер в байтах, которые находятся в архиве.
        Затем данные извлекаются из архива и сохраняются в новые файлы, в указанной папке. */
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(pathToArchive))) {
            ZipEntry entry;
            String name;
            long size;
            while( null != (entry = zin.getNextEntry()) ){
                name = entry.getName(); // получим название файла
                size = entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                 
                // распаковка
                FileOutputStream fout = new FileOutputStream(pathToUnzip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){ System.out.println(ex.getMessage()); } 
    }            
}
