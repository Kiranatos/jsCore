package net.kiranatos.e03generics;

import java.util.*;

public class Demo10ArrayList {
    public static void main(String[] args) {
        System.out.println("\n\t**** **** class ArrayList **** ****");
        System.out.println("Example of creating ArrayList - look in code.");
        List<String> list_0 = demo_HowToGetList();  
        
        System.out.print("Example of output via <<iterator>>: ");
        demo_HowToOutputListViaIterator(list_0);
            
        System.out.print("\nExample of output via <<for each>>: ");
        for (String str : list_0)
            System.out.print(str + ", ");        
        
        System.out.println("\nIndex of \"dog\" in list is " + list_0.indexOf("dog"));
        
        System.out.println("\nInner structure of ArrayList");
        System.out.println("Example of possible actions with list's capacity: ");
        demo_Capacity();
        
        //#####################################################################
        
        System.out.println("\n\t**** **** interface <<Collection>> demonstration: **** ****");
        Collection<String> collectionOfArrayList = list_0;
        System.out.println("Removing elements with size=6 using method .removeIf(predicate) from Collection interface");
        collectionOfArrayList.removeIf(e -> e.length() == 6);
        demo_HowToOutputListViaIterator(list_0);
        System.out.println("\nIt's analogical to removing elements (size=5) using Iterator:");
        Iterator<String> it = collectionOfArrayList.iterator();
        while (it.hasNext()) {
            String word = it.next();
            if (word.length() == 5) it.remove();            
        }
        demo_HowToOutputListViaIterator(list_0);
                
        //#####################################################################
        
        System.out.println("\n\n\t**** **** class <<Collections>> demonstration: **** ****");
        System.out.print("Collections.shuffle: ");
        Collections.shuffle(list_0);        
        demo_HowToOutputListViaIterator(list_0);
        
        System.out.print("\nCollections.sort: ");
        Collections.sort(list_0);        
        demo_HowToOutputListViaIterator(list_0);
        
        System.out.println("\nCollections.frequency:");        
        System.out.printf("\tWord 'repeat' was repeated %d times in list.%n", 
                Collections.frequency(list_0, "repeat"));
        
        
        /*******************************************************/
        /*               TO BE CONTINUED ...                   */
        /* 
        1. check another metods from Collections
        2. http://developer.alexanderklimov.ru/android/java/arraylist.php
            will read one more time, when I will repeat lambda and Stream API
        */
        /*******************************************************/        
    }    
    
    private static List<String> demo_HowToGetList(){
        List<String> list = new ArrayList <String>(50); // initial size of ArrayList 50
        list.add("vulture");
        list.addAll(Arrays.asList(
                "pursuer", "dried up", "ascertain", "snide", "repeat",
                "Whereby", "stalemate", "dog", "egging", "hesitation",
                "Commodification", "choir", "repeat", "repeat"
        ));        
        return list;
    }
    
    private static void demo_HowToOutputListViaIterator(List<?> list){
        System.out.print("[");
        Iterator<?> it = list.iterator();
        while (it.hasNext()) 
            System.out.print(it.next() + ", ");
        System.out.print("]");
    }
    
    private static void demo_Capacity(){        
        ArrayList<Integer> list = new ArrayList <>();        
        System.out.print("Initial size of ArrayList is 10 by default. We can set other size of Capacity in constructor ");
        list = new ArrayList<Integer>(20); // Capacity = 20
        System.out.println("or increase in method <<ensureCapacity>>.");
        list.ensureCapacity(5000); // Capacity = 5000
        list.add(10); 
        list.add(20); 
        list.add(30); 
        list.add(40); 
        System.out.println("Method <<trimToSize>> removes unnecessary cells so the capacity matches the size of list.");
        list.trimToSize(); // Capacity must be 4, but we can't check it.
        System.out.println("It is impossible to find out value of capacity in ArrayList. We can only set this value.");
    }
}


