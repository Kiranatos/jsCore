package net.kiranatos.e99books.sierra.mychat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocketDEEPER implements Runnable {
    
    private boolean newMessage = false;
    private String nsm;    
    private boolean newFileUpload = false;        
    ServerGUI gui;
    int a = 0;
    
    public void setGUI(ServerGUI gui) { this.gui = gui;  }    
    
    public void newServerMessage (String str) {
        nsm = str;
        newMessage = true;
    }
    
    public void newServerFileUpload (String pathUser)
    {
        newServerMessage("FILEUPLOAD:"+pathUser);
        newFileUpload = true;
    }

    @Override
    public void run() {        
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            
            while (true) {
                Socket sock = serverSock.accept();
                getInformation(sock);
                a++;
                Thread r = new Thread (new ClientReader(sock));
                r.start();
                Thread w = new Thread (new ClientWriter(sock));
                w.start();                
            }   
        } catch (IOException ex) { Logger.getLogger(ServerSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex); }
    }    
    
    /********************************************************/
    
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
            String message2;
            try {         
                while (true) {
                        if ((message2 = reader.readLine()) != null) {
                            gui.mainChatJTextArea.append("\n" + message2);                            
                        }
                }
            } catch (IOException ex) { Logger.getLogger(ServerSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex); }
        }                
    }
    
    /********************************************************/
    
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
            String message = "Kiranatos: ";
            while (true) {
                if (newMessage) {
                    String sentence = message + nsm;
                    System.out.println(sentence);
                    writer.println(sentence);
                    writer.flush();
                    //gui.mainChatJTextArea.append(sentence);
                    newMessage = false;
                    nsm = "";
                }
                else if (newFileUpload)
                {
                    //writer.print("FILEKIRANATOS");
                    FileInputStream file;
                    
                    try {
                        file = new FileInputStream("F:\\fromKiranatos.jpg");
                        while ( file.available() > 0 )
                        { 
                            int a = file.read();
                            System.out.print(a + " ");
                            writer.println(a);
                            writer.flush();
                            
                            //try {Thread.sleep(1000);} catch (InterruptedException ex) {}
                        }
                        writer.print(-1);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ServerSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerSocketDEEPER.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            }
        }                
    }
    
    
    private void getInformation(Socket sockInfo) {
        try {
            
            FileWriter file = new FileWriter ("F:\\ConnectionRender.txt");
            file.write("\n.getInetAddress()= " + sockInfo.getInetAddress());
            file.write("\n.getKeepAlive()= " + sockInfo.getKeepAlive());
            file.write("\n.getLocalAddress()= " + sockInfo.getLocalAddress());
            file.write("\n.getLocalPort()= " + sockInfo.getLocalPort());
            file.write("\n.getLocalSocketAddress()= " + sockInfo.getLocalSocketAddress());
            file.write("\n.getPort()= " + sockInfo.getPort());
            file.write("\n.getReceiveBufferSize()= " + sockInfo.getReceiveBufferSize());
            file.write("\n.getRemoteSocketAddress()= " + sockInfo.getRemoteSocketAddress());
            file.write("\n.getSendBufferSize()= " + sockInfo.getSendBufferSize());            
            file.close();
        } catch (IOException ex) { ex.printStackTrace(); }      
    }
}
