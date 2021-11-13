package net.kiranatos.www.java2s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start_Java2S_Site {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String theChoice;
    
    public static void main(String[] args) throws IOException {
        System.out.println("\n\n"
                + "\n#######################################################################################"
                + "\n#######################################################################################"
                + "\n##### WWW.Java2S.com ####"
                + "\n#######################################################################################"
                + "Choose examples:"
                + "\n 1. Is Event Dispatcher Thread : Animation « 2D Graphics GUI « Java "
                + "\n 2. Timer based animation : Swing Timer « Swing « Java Tutorial "
                + "\n 3. "
                
                + "\n exit");
        
        theChoice = reader.readLine();    
        System.out.println("Your choice: " + theChoice);
        
        switch (theChoice) {
            case "1": 
                net.kiranatos.www.java2s.Graphics2DGUI.Animation.IsEDTExample.main(null);
                break;
            case "2":
                net.kiranatos.www.java2s.Graphics2DGUI.Animation.TimerBasedAnimation.main(null);
                break;
            case "3": 
                //sortStart();
                break;
            
            case "exit": 
                System.exit(0);
                break;
            default: 
                System.out.println("Incorrect choice !!!");
                //net.kiranatos.Start_Ex_WithoutPlugInLibs.main(null);
                break;
        }
        
    }    
}
