package net.kiranatos.e03generics;

/**
 * Source: developed by my own knowledge
 */

import java.util.*;

public class TestCollectionArrayList {
    public static void main(String[] args) {
        System.out.println("\n\n\t**** **** class ArrayList **** ****");
        List<String> list_0 = getList();  
        
        System.out.println("Приклад виведення через ітератор:");
        output(list_0);
            
        System.out.println("\nПриклад виведення через for each:");        
        for (String str : list_0)
            System.out.print(str + " ");        
        
        System.out.println("\n\nindex of \"Whereby\" is " + list_0.indexOf("Whereby"));
        
        Example_001();
        
        //#####################################################################
        System.out.println("\n\n\t**** **** class Collections : **** ****");
        System.out.println("Collections.shuffle - перемешать :");
        Collections.shuffle(list_0);
        output(list_0);
        System.out.println("\nCollections.sort - сортировать :");
        Collections.sort(list_0);
        output(list_0);
        System.out.println("\nCollections.frequency - повторы: " + Collections.frequency(list_0, "repeat"));        
        
        
        /*******************************************************/
        /*               TO BE CONTINUED ...                   */
        /* 
        1. check another metods from Collections
        2. http://developer.alexanderklimov.ru/android/java/arraylist.php
            will read one more time, when I will repeat lambda and Stream API
        */
        /*******************************************************/        
    }    
    
    private static List<String> getList(){
        List<String> list = new ArrayList <String>(50); // початковий розмір 50 елементів
        list.addAll(Arrays.asList(
                "pursuer", "dried up", "ascertain", "snide", "repeat",
                "Whereby", "stalemate", "egging", "hesitation",
                "Commodification", "choir", "repeat", "repeat"
        ));        
        return list;
    }
    
    private static void output(List<?> list){
        System.out.print("[ ");
        Iterator<?> it = list.iterator();
        while (it.hasNext()) 
            System.out.print(it.next() + " ");
        System.out.println("]");
    }
    
    private static void Example_001(){
        System.out.println("\n **** Inner structure of ArrayList **** ");
        ArrayList<Integer> list = new ArrayList <>();        
        System.out.println("initial size of ArrayList 10 by default");
        System.out.println("we can set other size of Capacity in constructor");
        list = new ArrayList<Integer>(20); // Capacity = 20
        System.out.println("or increase in metod");
        list.ensureCapacity(5000); // Capacity = 5000
        list.add(10); 
        list.add(20); 
        list.add(30); 
        list.add(40); 
        System.out.println("metod trimToSize удаляет ненужные ячейки, чтобы capacity соответствовал размеру");
        list.trimToSize(); // Capacity = 4
        System.out.println("to get the size of Capacity in ArrayList is immposible");
    }
}


