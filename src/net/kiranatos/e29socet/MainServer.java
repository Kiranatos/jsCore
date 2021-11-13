package net.kiranatos.e29socet;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSock;
        Socket sockClient = null;
        System.out.println("Start Server");
        serverSock = new ServerSocket(4242);
        sockClient = serverSock.accept();
        getInformation(sockClient);        
        
        InputStreamReader isReader = new InputStreamReader(sockClient.getInputStream());
        BufferedReader reader = new BufferedReader(isReader);
        
        while (true) {
            String message;
            if ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
            Thread.sleep(500);
        }
    }    
    
    private static void getInformation(Socket sockInfo) {
        try {
            FileWriter file = new FileWriter("ConnectionInformation.txt");
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
