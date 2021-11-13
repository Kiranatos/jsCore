package net.kiranatos.e99books;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartBooks {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String theChoice;
    
    public static void main(String[] args) throws IOException {
        System.out.println("\n\n"
                + "\n#######################################################################################"
                + "\n#######################################################################################"
                + "\n##### Примеры из книг ####"
                + "\n#######################################################################################"
                + "\t Choose Books:"
                + "\n 1. Сьерра К., Бейтс Б. - Изучаем Java (2012-708-2)"
                + "\n 2. "
                + "\n exit");
        
        theChoice = reader.readLine();    
        System.out.println("Your choice: " + theChoice);
        
        switch (theChoice) {
            case "1": 
                sierraStart();            
                break;
            case "2":
                //genericsStart();
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
    
    /**********************************************************************
     ********************* Сьерра К., Бейтс Б. - Изучаем Java (2012-708-2) ******************************************
     **********************************************************************/
    public static void sierraStart() throws IOException {        
        System.out.println("\n**********************************"
                + "\n**** Choose example from book: ****"
                + "\n**********************************"
                + "\n 1. Cинтезатор"
                + "\n 2. Удивительный сжимающийся прямоугольник (swing + awt) / стр 428"
                + "\n 3. Beat Box / Музыкальный эквалайзер / стр 680"
                + "\n 4. Quiz Card Player / стр 486"
                + "\n 5. Мій чат с Рендером. Уже полуразрушен -> реанимировать знания и переделать"
                + "\n 6. RMI Browser (not working) -> реанимировать знания и переделать"                
                + "\n back"
                + "\n exit");
        
        theChoice = reader.readLine();    
        System.out.println("Your choice: " + theChoice);
        
        switch (theChoice) {
            case "1": 
                net.kiranatos.e99books.sierra.MiniMiniMusicApp.main(null);
                break;
            case "2":
                net.kiranatos.e99books.sierra.Animate.main(null);               
                break;
            case "3": 
                net.kiranatos.e99books.sierra.BeatBoxFinal.main(null);
                break;
            case "4": 
                net.kiranatos.e99books.sierra.p486.QuizCardPlayer.main(null);               
                break;
            case "5": 
                net.kiranatos.e99books.sierra.mychat.ServerSoft.main(null);
                net.kiranatos.e99books.sierra.mychat.UserSoft.main(null);               
                break;
            case "6": 
                //
                break;
            case "back": 
                StartBooks.main(null);
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
