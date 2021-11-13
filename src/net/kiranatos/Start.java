package net.kiranatos;

import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start { 
//    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private static String theChoice;
//    
    public static void main(String[] args) throws IOException {
//        System.out.println("\n\n"
//                + "\n#######################################################################################"
//                + "\n#######################################################################################"
//                + "\n##### Проект-справочник примеров, не использующий Maven или подключаемых библиотек ####"
//                + "\n#######################################################################################"
//                + "Choose examples:"
//                + "\n 0. Базовые вещи, которые забываются или misunderstanded"
//                + "\n 1. Working with Files"
//                + "\n 2. Generics, List, Map, Set, Properites, Matrix/Array"
//                + "\n 3. Different types of sorts"
//                + "\n 4. Time and Date"
//                + "\n 5. Functional Programming, Lambda Expressions and Stream API"
//                + "\n 6. Working with Fonts"
//                + "\n 7. Multithreading"
//                + "\n 8. Random"
//                + "\n 9. String, System.out.printf"
//                + "\n 10. Design Patterns"
//                + "\n 11. Books"
//                + "\n 12. Examples from different sites (WWW)"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": 
//                filesStart();            
//                break;
//            case "2":
//                genericsStart();
//                break;
//            case "3": 
//                net.kiranatos.e03sorts.StartSorts.main(null);
//                break;
//            case "4": 
//                timeAndDateStart();
//                break;
//            case "5": 
//                lambdaStart();
//                break;
//            case "6": 
//                fontsStart();
//                break;
//            case "7": 
//                multithreadingStart();                
//                break;
//            case "8": 
//                net.kiranatos.e08random.TestRandom.main(null);
//                break;
//            case "9": 
//                stringStart();
//                break;
//            case "10": 
//                net.kiranatos.e10patterns.StartPatterns.main(null);
//                break;
//            case "11": 
//                net.kiranatos.e11books.StartBooks.main(null);
//                break;
//            case "12": 
//                wwwStart();
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//        } 
    }
//    
//    /**********************************************************************
//     ********************* 1. FILES ******************************************
//     **********************************************************************/
//    public static void filesStart() throws IOException {        
//        System.out.println("\n**********************************"
//                + "\n**** Choose example of Working with files: ****"
//                + "\n**********************************"
//                + "\n 1. Read and Write files using java.io and java.util.Scanner"
//                + "\n 2. Read and Write files using java.nio (НУЖНО ЗАКОНЧИТЬ)"
//                + "\n 3. Какую информацию можно получить с помощью класса java.io.File"
//                + "\n 4. Фокусы с панелькой (ЕЩЁ НЕ РЕАЛИЗОВАНО)"
//                + "\n 5. "
//                + "\n 6. "
//                + "\n 7. "
//                + "\n 8. "
//                + "\n 9. "
//                + "\n back"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": 
//                System.out.println("Внимание: Просмотри код и удостоверся что тестовые файлы на месте или создай новые");
//                net.kiranatos.e01files.TestFilesIO_ReadingAndWriting.main(null);
//                break;
//            case "2":
//                System.out.println("Внимание: Просмотри код и удостоверся что тестовые файлы на месте или создай новые");
//                net.kiranatos.e01files.TestFilesNIO_ReadingAndWriting.main(null);
//                break;
//            case "3": 
//                System.out.println("Внимание: Просмотри код и удостоверся что тестовые файлы на месте или создай новые");
//                net.kiranatos.e01files.TestFile.main(null);
//                break;
//            case "4":
//                net.kiranatos.e01files.TestPanel.main(null);
//                break;
//            case "5": 
//               
//                break;
//            case "6": 
//                
//                break;
//            case "7": 
//                
//                break;
//            case "8":       
//                
//                break;
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//        }  
//    }
//    
//    /**********************************************************************
//     ********************* 02. GENERICS ******************************************
//     **********************************************************************/
//    public static void genericsStart() throws IOException {
//        System.out.println("\n************************************************"
//                + "\n**** Choose example of Generics/Collection: ****"
//                + "\n****** (please look and investigate code) ******"
//                + "\n************************************************"
//                + "\n 1. How to work with ArrayList and Collections"
//                + "\n 2. How to work with HashMap"
//                + "\n 3. How to work with HashSet (НЕ РЕАЛИЗОВАНО)"
//                + "\n 4. How to work with Generics"
//                + "\n 5. How to work with Properites"
//                + "\n 6. Еще одна демонстрация записи и чтенния класса Properites"
//                + "\n 7. How to work with Matrix/Массивами/Arrays"
//                + "\n 8. "
//                + "\n 9. "
//                + "\n back"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//                
//        switch (theChoice) {
//            case "1": 
//                net.kiranatos.e02generics.TestCollectionArrayList.main(null);
//                break;
//            case "2":
//                net.kiranatos.e02generics.TestCollectionHashMap.main(null);
//                break;
//            case "3": 
//                net.kiranatos.e02generics.TestCollectionHashSet.main(null);
//                break;
//            case "4": 
//                net.kiranatos.e02generics.TestGenerics.main(null);
//                break;
//            case "5": 
////                net.kiranatos.e02generics.TestProperties.main(null);
//                break;
//            case "6": 
////                net.kiranatos.e02generics.TestPropertiesReading.main(null);
//                break;
//            case "7": 
//                net.kiranatos.e02generics.ArraysSimple01.main(null);
//                break;
//            case "8":       
//                System.exit(0);
//                break;
//            case "9": 
//                System.exit(0);
//                break;
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//        }
//    }
//       
//    /**********************************************************************
//     ********************* 4. TIME AND DATE ******************************************
//     **********************************************************************/
//    public static void timeAndDateStart() throws IOException {
//        System.out.println("\n************************************************"
//                + "\n**** Choose example of Time & Date: ****"
//                + "\n****** (please look and investigate code) ******"
//                + "\n************************************************"
//                + "\n 1. Date"
//                + "\n 2. "        
//                + "\n 3. "
//                + "\n back"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": {
//                try {  net.kiranatos.e04timeanddate.TestDate.main(null);
//                } catch (ParseException ex) { Logger.getLogger(Start_Ex_WithoutPlugInLibs.class.getName()).log(Level.SEVERE, null, ex); }
//            }
//                break;
//            case "2":
//                System.exit(0);
//                break;            
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;        
//        }        
//    }
//    
//    /**********************************************************************
//     ************** Functional Programming and Lambda Expressions **************
//     **********************************************************************/    
//    public static void lambdaStart() throws IOException {
//        OzoHelper.printMe(null,
//                "************************************************",
//                "**** Choose example of Functional Programming, ****",
//                "**** Lambda Expressions or Stream API: ****",
//                "****** (please look and investigate code) ******",
//                "************************************************",
//                "1. Lambda Expressions",
//                "2. Integral Functional Interfaces",
//                "3. ",
//                "back",
//                "exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1":
//                net.kiranatos.e05lambda.TestLambda.main(null);
//                break;
//            case "2":
//                net.kiranatos.e05lambda.TestFunctionalInterfaces.main(null);
//                break;            
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;        
//        }
//    }
//    
//    /**********************************************************************
//     ************** 6. FONTS **************
//     **********************************************************************/    
//    public static void fontsStart() throws IOException {
//        OzoHelper.printMe(null,
//                "************************************************",
//                "**** Choose examples for working with Fonts, ****",                
//                "****** (please look and investigate code) ******",
//                "************************************************",
//                "1. Пример отображения шрифтов, Japanese, Font from file",
//                "2. Вывод текста со шрифтом в png-файл",
//                "3. ",
//                "back",
//                "exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": { try {
//                    net.kiranatos.e06fonts.FontsView.main(null);
//                } catch (FontFormatException ex) { Logger.getLogger(Start_Ex_WithoutPlugInLibs.class.getName()).log(Level.SEVERE, null, ex); } }
//                break;
//            case "2":
//                net.kiranatos.e06fonts.TextToBuferedImage.main(null);
//                break;            
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;        
//        }
//    }
//    
//    /**********************************************************************
//     ********************* 7. MULTITHREADING ******************************************
//     **********************************************************************/
//    public static void multithreadingStart() throws IOException {
//        OzoHelper.printMe(null,
//                "************************************************",
//                "**** Choose example of Multithreading: ****",
//                "****** (please look and investigate code) ******",
//                "************************************************",
//                "Homework: ",
//                " - розгребти записи в Info001_Basic",
//                " - доповторять/доизучать більш глибокий матеріал по багатопоточності (Сімафори, Екзекютори, ...) \n",
//                " 1. Приклади створення ниток в багатопотоковісті",
//                " 2. Демонстрація роботи методу .interrupt()",
//                " 3. Ігри з volatile і synchronized модіфікаторами",
//                " 4. ",
//                " 5. ",
//                " 6. ",
//                " 7. ",
//                " 8. ",
//                " 9. ",
//                " back",
//                " exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": 
//                net.kiranatos.e07concurrency.TestThread_001.main(null);
//                break;
//            case "2":
//                net.kiranatos.e07concurrency.TestInterruptInThread.main(null);
//                break;
//            case "3": { 
//                try {
//                    net.kiranatos.e07concurrency.TestSynchronized.main(null);
//                } catch (InterruptedException ex) { Logger.getLogger(Start_Ex_WithoutPlugInLibs.class.getName()).log(Level.SEVERE, null, ex); }
//            }
//                break;
//            case "4": 
//                //TestGenerics.main(null);
//                break;
//            case "5": 
//                System.exit(0);
//                break;
//            case "6": 
//                System.exit(0);
//                break;
//            case "7": 
//                System.exit(0);
//                break;
//            case "8":       
//                System.exit(0);
//                break;
//            case "9": 
//                System.exit(0);
//                break;
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//        }
//    }
//    
//    /**********************************************************************
//     ********************* 9. STRINGS ******************************************
//     **********************************************************************/
//    public static void stringStart() throws IOException {
//        System.out.println("\n************************************************"
//                + "\n**** Choose example of Working with String: ****"
//                + "\n****** (please look and investigate code) ******"
//                + "\n************************************************"
//                + "\n 1. System.out.printf"
//                + "\n 2. "        
//                + "\n 3. "
//                + "\n back"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": 
//                net.kiranatos.e09string.TestPrintF.main(null);
//                break;
//            case "2":
//                System.exit(0);
//                break;            
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;        
//        }
//    }
//    
//    /**********************************************************************
//     ********************* 12. WWW ******************************************
//     **********************************************************************/
//    public static void wwwStart() throws IOException {        
//        System.out.println("\n**********************************"
//                + "\n**** Choose site: ****"
//                + "\n**********************************"
//                + "\n 1. www.java2s.com"
//                + "\n 2. "
//                + "\n 3. "
//                + "\n 4. "
//                + "\n 5. "
//                + "\n 6. "
//                + "\n 7. "
//                + "\n 8. "
//                + "\n 9. "
//                + "\n back"
//                + "\n exit");
//        
//        theChoice = reader.readLine();    
//        System.out.println("Your choice: " + theChoice);
//        
//        switch (theChoice) {
//            case "1": 
//                net.kiranatos.www.java2s.Start_Java2S_Site.main(null);
//                break;
//            case "2":
//               
//                break;
//                          
//          
//            case "back": 
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//            case "exit": 
//                System.exit(0);
//                break;
//            default: 
//                System.out.println("Incorrect choice !!!");
//                Start_Ex_WithoutPlugInLibs.main(null);
//                break;
//        }  
//    }    
}


/*
Homework for future breviaring:

DecimalFormat
    Настройка форматов	http://khpi-iip.mipk.kharkiv.edu/library/extent/prog/inter/decimalFormat.html
    Синтаксис шаблона формата числа 	http://khpi-iip.mipk.kharkiv.edu/library/extent/prog/inter/numberpattern.html
    Форматирование чисел в Java: DecimalFormat	https://habr.com/ru/company/otus/blog/415521/
    (пример использования DecimalFormat можно найти в примере #4 Спостерiгача)

*/