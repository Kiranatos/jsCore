package net.kiranatos.e02sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Source:
https://jalekberov.wordpress.com/2012/08/12/%d1%81%d0%be%d1%80%d1%82%d0%b8%d1%80%d0%be%d0%b2%d0%ba%d0%b0-%d0%bf%d1%83%d0%b7%d1%8b%d1%80%d1%8c%d0%ba%d0%be%d0%bc/
http://kvodo.ru/puzyirkovaya-sortirovka.html
*/

public class BubbleSort {
    private static int[] array = shuffle(1, 7);    
    
    public static void main(String[] args) {
        System.out.println("\t\t\t Bubble Sort. \n"
                + "Dificulty: "
                + "\n\t Best: O(n)"
                + "\n\t Average: O(n^2)"
                + "\n\t Worst: O(n^2)"
                + "\n Эффективен он лишь для небольших массивов. Метод лежит в основе шейкерной, пирамидальной и быстрой сортировок.");
        System.out.println("Start: \t\t\t" + Arrays.toString(array));
        
        int k = 1;
        for(int i=0; i < array.length; i++){
            for(int j=0; j < array.length-1; j++){
                if(array[j] > array[j+1]) {
                    int c = array[j];
                    array[j] = array[j+1];
                    array[j+1] = c;
                    System.out.println("Step #"+ k++ +": \t\t" + Arrays.toString(array) + " moving: " + c);
                }
            }
        }
        
        System.out.println("Finish: \t\t" + Arrays.toString(array));
        /*
        Приведенный код можно улучшить, а именно – вдвое уменьшить количество 
        выполняемых сравнений. Для этого достаточно с каждым шагом i внешнего цикла
        на i уменьшать количество итераций внутреннего цикла. 
        for(int i=0; i < array.length-1; i++){
            for(int j=0; j < array.length-(i+1); j++){        
        */
    }
    
    public static int[] shuffle(int startNumber, int endNumber) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = startNumber; i < endNumber+1; i++) 
            list.add(i);
        Collections.shuffle(list);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            arr[i] = list.get(i);        
        return arr;
    }
}

