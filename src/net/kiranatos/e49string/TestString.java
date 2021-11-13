package net.kiranatos.e49string;

public class TestString {
    public static void main(String[] args) {
        String str1 = "литерал, попадает в пулл";
        String str2 = new String("объект -> в кучу");
        str1 = 1 + str1;   // конкатенация с литералом -> в пул
        str2 = str2 + "f"; // конкатенация с объектом -> в кучу
        str1 = "equal";
        str2 = new String("equal");
        System.out.println(str1.compareTo(str2));   // 0
        System.out.println(str1.equals(str2));      // true        
        System.out.println(str1 == str2);           // false
        System.out.println(str1 == str2.intern());  // true
        String str3 = new String(str2);
        str3.intern();
        System.out.println(str3 == "equal".intern()); //false
        
        
    }    
}
