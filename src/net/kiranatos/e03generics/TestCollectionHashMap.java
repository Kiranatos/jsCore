package net.kiranatos.e03generics;

/**
 * Source: developed by my own knowledge
 */

import java.util.*;

public class TestCollectionHashMap {
    public static void main(String[] args) {
        Map<String,Integer> map_0 = getMap();
        
        System.out.println("Приклад виведення через ітератор:");
        Iterator<Map.Entry<String,Integer>> it = map_0.entrySet().iterator();		
        while ( it.hasNext() ) {		
            Map.Entry<String,Integer> pair = it.next();		
            String k = pair.getKey();		
            Integer v = pair.getValue();
            System.out.print(" (" + k + ":" + v + ") ");
        }		
        
        System.out.print("\n\nПриклад виведення через for each:\n1й варіант:");        
        for (String str: map_0.keySet()) // Отримуємо коллекцію ключів
            System.out.print(" (" + str + ":" + map_0.get(str) + ") "); // Получаем значение по ключу		
        System.out.print("\n2й варіант:");
        for (Map.Entry<String, Integer> pair : map_0.entrySet()) // Отримуємо множину всіх пар
            System.out.print(" (" + pair.getKey() + ":" + pair.getValue() + ") ");
        
        System.out.println("\nПроверить наличие ключа 	containsKey(key) : " + map_0.containsKey("Whereby"));
        System.out.println("Проверить наличие значения containsValue(value) : " + map_0.containsValue(60));
        System.out.println("Удалить элемент по ключу 	remove(key) : " + map_0.remove("Whereby"));
                
        map_0.clear(); // очистити карту
        System.out.println("Пустий? " + map_0.isEmpty());       
        
        // Получить множество всех значений 	.values()
        
        


        /*******************************************************/
        /*               TO BE CONTINUED ...                   */
        /*******************************************************/

        
    }    
    
    private static Map<String,Integer> getMap(){
        Map<String,Integer> map = new HashMap <String,Integer>();
        map.put("pursuer", 10);
        map.put("dried up", 20);
        map.put("ascertain", 30);
        map.put("snide", 40);
        map.put("Whereby", 50);
        map.put("stalemate", 60);
        map.put("egging", 70);
        map.put("hesitation", 80);
        map.put("Commodification", 90);
        map.put("choir", 100);
        map.put("BATMAN", 110);         
        return map;
    }
    
    
    private static void print(Map<String,String> props) {
        for(String key:props.keySet()) {
            System.out.println(key+" : "+props.get(key));
        }
    }    
}


//public class MyUtils {    
//    public boolean listMapCompare(List<String> list, Map<String, String> map) {
//        if (!list.containsAll(map.values())) return false;
//        
//        for (String el : list)
//            if (!map.containsValue(el)) return false;
//        
//        /*
//        for (String str: map.keySet()) {
//            System.out.println("MAP");
//            if (!list.contains(map.get(str))) return false; }*/
//
//        return true;
//    }
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap <>();
//        map.put("1", "cc");
//        map.put("2", "bb");
//        map.put("3", "cc");
//        map.put("4", "aa");
//        map.put("5", "cc");
//        map.put("6", "cc");
//        map.put("7", "cc");
////        map.put("5", "helicopter");
//        
//        List<String> list = new ArrayList<>();
//        list.add("aa");
//        list.add("bb");
//        list.add("aa");
//        list.add("cc");
//        list.add("cc");
//        list.add("kk");
//        
//        System.out.println(new MyUtils().listMapCompare(list, map));
//    }
//}