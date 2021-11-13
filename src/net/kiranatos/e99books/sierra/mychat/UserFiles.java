package net.kiranatos.e99books.sierra.mychat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserFiles {
    
    UserSocketDEEPER usd;
    
    public String[] callComand(String path) 
    {
        try {            
            File filesOfFoder = new File(path);
            System.out.println(path);
            
            FilenameFilter filterOfNameFiles = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("");
            }
        };
        
            return filesOfFoder.list(filterOfNameFiles);
            /*
        String[] countFolders = filesOfFoder.list(filterOfNameFiles);
        
        for (int i=0; i < countFolders.length; i++) {
            String fullPath = path + "\\" + countFolders[i];
            System.out.println(fullPath);
        }*/

        } catch (NullPointerException ex) {            
            //usd.newUserMessage("***ОШИБКА ВЗЛОМА: folder");            
        }
        
        return null;        
    }

    public void setUsd(UserSocketDEEPER usd) {
        this.usd = usd;
    }
}
