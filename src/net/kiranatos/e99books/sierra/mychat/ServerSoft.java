package net.kiranatos.e99books.sierra.mychat;

public class ServerSoft {
    
    public static void main(String[] args) {        
        ServerGUI server1 = ServerGUI.singletonServer();
        Thread serverThread = new Thread (server1);
        serverThread.start();    
    }    
}
