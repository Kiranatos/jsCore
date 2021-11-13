package net.kiranatos.e02sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Source:
https://java-master.com/%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC-%D0%B1%D1%8B%D1%81%D1%82%D1%80%D0%BE%D0%B9-%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B8-quick-sort-%D0%B2-java/

*/

public class QuickSort {
    private static int[] array = shuffle(1, 15);
    private static int k = 1;
    
    public static void main(String[] args) {
        System.out.println("Quick Sort. "
                + "Dificulty: "
                + "\n\t Best: O(n)"
                + "\n\t Average: O(n log n)"
                + "\n\t Worst: O(n^2)"
                + "\n Cамый быстрый метод");
        System.out.println("Start: \t\t" + Arrays.toString(array));
        
        quickSort(0, array.length-1);
        
        System.out.println("Finish: \t" + Arrays.toString(array));
    }
    
    public static void quickSort(int low, int high) {
        if (array.length == 0) return;//завершить выполнение если длина массива равна 0 
        if (low >= high) return;//завершить выполнение если уже нечего делить
        
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];
 
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) i++; 
            while (array[j] > opora) j--;
 
            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                System.out.println("Step #"+ k++ +": \t" + Arrays.toString(array) + " exchanging: " + array[i] + " & " + array[j] + " опорный элемент: " + opora);
                i++;
                j--;                
            }
        }
 
        // вызов рекурсии для сортировки левой и правой части
        if (low < j) quickSort(low, j);
        if (high > i) quickSort(i, high);
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

