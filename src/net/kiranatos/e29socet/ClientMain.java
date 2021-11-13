package net.kiranatos.e29socet;

import java.io.*;
import java.net.*;

public class ClientMain {
	public static void main(String[] args) {
		
		Socket sockClient;
		try {
			sockClient = new Socket("127.0.0.1",4242);		
        //sockClient = new Socket("176.113.154.80", 4242);
		PrintWriter writer = new PrintWriter(sockClient.getOutputStream());
		
		int index = 1;
		while (index < 20) {
			writer.println("I am Client !!!" + index);
            writer.flush();          
            Thread.sleep(1000);
            index++;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
