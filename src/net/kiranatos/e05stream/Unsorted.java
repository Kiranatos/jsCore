package net.kiranatos.e05stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
52:00 https://www.youtube.com/watch?v=O8oN4KSZEXE
        https://www.youtube.com/watch?v=i0Jr2l3jrDA

середина статьи : https://annimon.com/article/2778
*/

public class Unsorted {
    public static void main(String[] args) {
        Optional<String> opt_str = Optional.of("");
        /***************************************************/
        
        Person.getPersons()
                .stream()
                .sorted( (p1,p2) -> p1.getName().compareTo(p2.getName()))
                .forEach(System.out::println);
        
        double averageDemonstration = Person.getPersons()
                .stream()
                .filter(p -> p.getAge() >= 18)
                .mapToInt(p -> p.getAge())
                .average()
                .getAsDouble();
        /***************************************************/
        
        List<Integer> result = MyHelpClass.getListInt()
                .stream()
                .filter(o -> 100 == o.intValue())
                .collect(Collectors.toList());
        Optional<Integer> optSSS = result.size() != 0 ? Optional.of(result.get(0)) : Optional.empty();
        // то же что и :
        optSSS = MyHelpClass.getListInt()
                .stream()
                .filter(o -> 100 == o.intValue())
                .findAny();
        
        /***************************************************/
        // пример насколько ненадёжно выполнять в параллельных потоках подсчёт:
        Stream<Integer> s1 = IntStream.range(0, 100)
                .mapToObj(i -> 1);
        System.out.println(getSum(s1));
        // 100
        
        Stream<Integer> s2 = IntStream.range(0, 100)
                .mapToObj(i -> 1)
                .parallel();
        System.out.println(getSum(s2));
        // 79, 63, 100, ...
        /***************************************************/
        
    }
    
    public static int getSum(Stream<Integer> s) {
        int[] sum = new int[1];
        s.forEach(i -> sum[0] += i); //сделано так, потому что принимает только final local переменные
        return sum[0];
    }
}

class Person {
    private static List<Person> persons = new ArrayList<>();
    private int age;
    private String name;

    public String getName() { return name; }
    public int getAge() { return age; }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static List<Person> getPersons() {
        persons.add(new Person(38,"Den"));
        persons.add(new Person(22,"Ben"));
        persons.add(new Person(10,"Ken"));
        persons.add(new Person(5,"Men"));
        return persons;
    }    
}

/*
Подключение компаратора:
class GFG { 
  
    // Driver code 
    public static void main(String[] args) 
    { 
  
        // Creating a list of Integers 
        List<Integer> list = Arrays.asList(5, -10, 7, -18, 23); 
  
        System.out.println("The sorted stream according "
                           + "to provided Comparator is : "); 
  
        // Displaying the list of Strings in 
        // reverse order after sorting 
        list.stream().sorted(Comparator.reverseOrder()). 
                          forEach(System.out::println); 
    } 
} 


 // Driver code 
    public static void main(String[] args) 
    { 
  
        // Creating a list of Strings 
        List<String> list = Arrays.asList("Geeks", "for", 
                    "GeeksforGeeks", "GeeksQuiz", "GFG"); 
  
        System.out.println("The sorted stream according "
                           + "to provided Comparator is : "); 
  
        // Displaying the list of Strings in 
        // reverse order after sorting 
        list.stream().sorted(Comparator.reverseOrder()). 
                            forEach(System.out::println); 
    } 

 // Driver code 
    public static void main(String[] args) 
    { 
  
        // Creating an array of Strings 
        String[] array = { "GFG", "Geeks", "for", 
                           "GeeksforGeeks", "GeeksQuiz" }; 
  
        System.out.println("The sorted stream is :"); 
  
        // sorting the elements of array based 
        // on their last character 
        Stream.of(array).sorted((str1, str2) 
                     -> Character.compare(str1 
                     .charAt(str1.length() - 1), 
                    str2.charAt(str2.length() - 1))) 
            .         forEach(System.out::println); 
    } 

посмотреть рекомендед посты:
https://www.geeksforgeeks.org/stream-sorted-comparator-comparator-method-java/
*/