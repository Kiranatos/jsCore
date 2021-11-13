package net.kiranatos.e02sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartSorts {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String theChoice;
    
    public static void main(String[] args) throws IOException {
        System.out.println("\n************************************************"
                + "\n**** Choose example of Sorts: ****"
                + "\n****** (please look and investigate code) ******"
                + "\n************************************************"
                + "\n 1. Bubble Sort from Youtube channel <<NomadRussian>>. For controls use buttons R and S"
                + "\n 2. Bubble Sort"
                + "\n 3. Quick Sort"
                + "\n 2. "        
                + "\n 3. "
                + "\n 2. "        
                + "\n 3. "
                + "\n 2. "        
                + "\n 3. "
                + "\n back"
                + "\n exit");
        
        theChoice = reader.readLine();    
        System.out.println("Your choice: " + theChoice);
        
        switch (theChoice) {
            case "1": 
                net.kiranatos.e02sort.BubbleSortFromNomad.main(null);
                break;
            case "2":
                System.exit(0);
                break;            
            case "back": 
                //net.kiranatos.Start_Ex_WithoutPlugInLibs.main(null);
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


/*
 сортировки выбором, сортировки вставками, сортировки слиянием, сортировки распределением, 
гибридные сортировки и параллельные сортировки. 
Есть, кстати, ещё эзотерические сортировки. Это различные фейковые, 
принципиально нереализуемые, шуточные и прочие псевдо-алгоритмы, про которые я в 
хабе «IT-юмор» как-нибудь напишу пару статей. 


epam
1.Insertion sort
2.Heap sort
3.Quick sort
4.Merge sort 
*/