package net.kiranatos.e03generics;


import java.util.*;

public class TestCollectionHashSet {
    public static void main(String[] args) {
        List<String> list_0 = getList();  
        
        System.out.println("Приклад виведення через ітератор:");
        Iterator<String> it = list_0.iterator();
        while (it.hasNext()) 
            System.out.print(it.next() + " ");
            
        System.out.println("\n\nПриклад виведення через for each:");        
        for (String str : list_0)
            System.out.print(str + " ");
        
        
        /*******************************************************/
        /*               TO BE CONTINUED ...                   */
        /*******************************************************/

        
    }    
    
    private static List<String> getList(){
        List<String> list = new ArrayList <String>();
        list.addAll(Arrays.asList(
                "pursuer", "dried up", "ascertain", "snide",
                "Whereby", "stalemate", "egging", "hesitation",
                "Commodification", "choir", "fdsfsd"
        ));        
        return list;
    }
}
