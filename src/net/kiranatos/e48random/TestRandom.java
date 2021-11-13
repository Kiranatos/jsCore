package net.kiranatos.e48random;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.kiranatos.OzoHelper;

public class TestRandom {
    public static void main(String[] args) {
        OzoHelper.printMe(null, 
                "\t Random in class Math:",
                "- double random numbers from 0.0 to 0.9999...");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%3.3f ", Math.random());
            }            
            System.out.println("");
        }
        
        System.out.println("- int random numbers from 0 to 10");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%d ", (int)(Math.random()*10));
            }            
            System.out.println("");
        }
        
        System.out.print("\n\t Random in class Random:");
        Random r1 = new Random();
        Random r2 = new Random(new Date().getTime()); //its a seed
        r1.setSeed(50); // synchronized void setSeeD(long seed) - устанавливает начальное значение
        OzoHelper.printMe(null,                 
                "возвращает следующее случайное значение типа boolean: " + r1.nextBoolean(),
                "возвращает следующее случайное значение типа double: " + r1.nextDouble(),
                "возвращает следующее случайное значение типа float: " + r1.nextFloat(),
                "возвращает следующее случайное значение типа long: " + r1.nextLong(),
                "возвращает следующее случайное значение типа int: " + r1.nextInt(),
                "возвращает следующее случайное значение типа int в диапазоне от 0 до n: " + r1.nextInt(100),
                "(synchronized double) возвращает следующее случайное значение гауссова случайного числа,\n т.е. значения, центрированное по 0.0 со стандартным отклонением в 1.0 \n(кривая нормального распределения)"
                + r1.nextGaussian(),         
                "Пример: ");
        // void nextBytes(byte[] buf) - заполняет массив случайно созданными значениями
                
        Random r3 = new Random();        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%5d ", r3.nextInt(i*10+1));
            }            
            System.out.println("");
        }
        
        OzoHelper.printMe(null, 
                "Стандартный класс Random обычно используют для простых задач,",
                "не связанных с шифрованием. Для криптографии следует использовать",
                " схожий класс java.security.SecureRandom.");
        SecureRandom secureRandom = new SecureRandom();
        System.out.println("secureRandom = " + secureRandom.nextInt(6));
        
        ThreadLocalRandom r4 = ThreadLocalRandom.current();
        OzoHelper.printMe(null, 
                "Класс ThreadLocalRandom аналогичен классам java.util.Random или Math.random()",
                "для генерации случайных значений, но более надёжен.",
                "Random boolean: " + r4.nextBoolean(),
                "Random int: " + r4.nextInt(),
                "Random between 0 and 10: " + r4.nextInt(10),
                "Random between 10 and 20: " + r4.nextInt(10, 20),
                "Random float: " + r4.nextFloat(),
                "Random long: " + r4.nextLong(),
                "Random long between 0 and 10: " + r4.nextLong(10),
                "Random long between 10 and 20: " + r4.nextLong(10, 20));
        
        System.out.println("\nВ Java 8 появился ещё один класс SplittableRandom");        
    }
}
