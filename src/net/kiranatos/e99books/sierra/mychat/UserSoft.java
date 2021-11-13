package net.kiranatos.e99books.sierra.mychat;

public class UserSoft {
    
    public static void main(String[] args) {
        
        UserGUI client1 = new UserGUI();
        Thread clientThread = new Thread (client1);
        clientThread.start(); 
    }    
}
