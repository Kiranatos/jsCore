package net.kiranatos.e01core;

/* Immutable classes: String and all childs of Number: Byte, Short, Integer, Long, Float, Double, Character, Boolean.
Как сделать Immutable class:
 - private fileds
 - getters but no any setters
 - different constructors
 - overide methods: toString(), equals(Object obj), hashCode()
*/
public class DifferentUnsortedGeneralInformation {  
    public static void main(String[] args) {
        int a, b, n;
        
        a=10; 
        b=10;
        System.out.println("Проверка на равенство (на 35% быстрее, чем JS):");
        System.out.printf("\t%b ",(a^b) == 0);
        a = 55;
        b = 66;
        System.out.printf("\t%b%n",(a^b) == 0);
        
        System.out.println("Проверка на чётность:");
        System.out.printf("\t%b\t%b%n",(a & 1) == 1, (b & 1) == 1);
        
        System.out.println("Проверка на одинаковый знак:");
        System.out.printf("\t%b ", (a ^ b) >= 0);
        a = -55;
        b = 66;
        System.out.printf("\t%b%n",(a ^ b) >= 0);
        
        System.out.println("Смена знака:");
        System.out.printf("\t%d\t%d%n", ~a + 1, (b ^ -1) + 1);
        
        System.out.println("Проверка на степень 2:");
        n = 1024;
        System.out.printf("\tn = %d, %b%n", n, n > 0 && (n & (n - 1)) == 0);
        n = 1023;
        System.out.printf("\tn = %d, %b%n", n, n > 0 && (n & (n - 1)) == 0);
        
        System.out.println("Преобразование буквы в строчную (x | ' ') OR by space :");
        System.out.println("Result is always lowercase even if letter is already lowercase.");
        System.out.printf("\t%c %c %c %c%n", 
                (char)('a' | ' '), (char)('K' | ' '), (char)('A' | ' '), (char)('Z' | ' '));
        
        System.out.println("Преобразование буквы в заглавную (x & '_') AND by underline :");
        System.out.println("Result is always uppercase even if letter is already uppercase.");
        System.out.printf("\t%c %c %c %c%n", 
                (char)('a' & '_'), (char)('K' & '_'), (char)('A' & '_'), (char)('z' & '_'));
        
        System.out.println("Смена вида буквы (x ^ ' ') XOR by space :");        
        System.out.printf("\t%c %c %c %c%n", 
                (char)('a' ^ ' '), (char)('K' ^ ' '), (char)('b' ^ ' '), (char)('Z' ^ ' '));
        
        
    }
}
