package net.kiranatos.e05stream;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.kiranatos.OzoHelper;

final class MyHelpClass {
    static void showElement(int element)      { System.out.print(element + " "); }
    static void showElement(double element)   { System.out.printf(" %.2f ", element); }
    static void showElement(String element)   { System.out.print(element + " "); }

    static Set<String> getSet() { 
        Set<String> vocabulary = new HashSet<String>();
        vocabulary.add("embarrassing");
        vocabulary.add("remarks");
        vocabulary.add("sappy");
        vocabulary.add("resume");        
        return vocabulary;
    }
    
    static Map<String, Integer> getMap() { 
        Map<String, Integer> vocabulary = new HashMap<String, Integer>();
        vocabulary.put("one", 521);
        vocabulary.put("two", 523);
        vocabulary.put("dog", 524);
        vocabulary.put("cat", 525);
        return vocabulary;
    }
    
    static List<Integer> getListInt() { 
        final List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(15);
        list.add(1);
        list.add(4);
        return list;
    }
    
    static Set<BigInteger> getSetBigInteger() { 
        Set<BigInteger> page = new HashSet<BigInteger>();
        page.add(new BigInteger("5"));
        page.add(new BigInteger("9"));
        page.add(new BigInteger("1"));
        page.add(new BigInteger("4"));        
        return page;
    }
    
    static String createFileForDemo01() {
        String s = "string 1" + System.lineSeparator() + "string 2" + System.lineSeparator() + "string 3" ;
        File file = OzoHelper.saveTextToFile(s, "wfiles\\e05", "Demo01StreamAPI.txt"); //"D:\\JunkToDelete\\testRead001A.txt";
        return file.getAbsolutePath();
    }
}

class User {
    private String name;
    private int age;
    
    static List<User> listUsers = Arrays.asList(
            new User("C", 30),
            new User("D", 40),
            new User("A", 10),
            new User("B", 20),
            new User("E", 50));
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }
    public int getAge()                 { return age; }
    public void setAge(int age)         { this.age = age; }
    
    @Override
    public String toString() {
        return "User{name=" + name + ", age=" + age +'}';
    }
}
