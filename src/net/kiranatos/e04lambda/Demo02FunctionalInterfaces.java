package net.kiranatos.e04lambda;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.kiranatos.OzoHelper;

public class Demo02FunctionalInterfaces {
    public static void main(String[] args) {
        OzoHelper.printMe(null, 
                "::: ::: Примеры Встроенные функциональные интерфейсы ::: :::",
                "\t    [ java.util.function ]    ",
                "Predicate<T>               boolean test(T t);",
                "Consumer<T>                void accept(T t);",
                "Function<T,R>              R apply(T t);",
                "Supplier<T>                T get();",
                "UnaryOperator<T>           T apply(T t);",
                "BinaryOperator<T>          T apply(T t1, T t2);",
                "\n"
        );
        
        Predicate<Integer> isPositive = x -> x > 0;         
        System.out.println("Example of Predicate: " + isPositive.test(5)); // true
        System.out.println("Example of Predicate: " + isPositive.test(-7)); // false
        
        Consumer<Integer> printer = x -> System.out.printf("%d долларов \n", x);
        System.out.print("Example of Consumer: ");
        printer.accept(600); // 600 долларов
        
        Function<Integer, String> convert = x -> String.valueOf(x) + " долларов";
        System.out.println("Example of Function: " + convert.apply(5)); // 5 долларов
        
        Supplier<ClassA> userFactory_1 = ()-> {
            System.out.println("Examples of Supplier: ");
            return new ClassA();
        };
        Supplier<ClassA> userFactory_2 = ()-> new ClassA();
        System.out.println("\tКласс получен - " + userFactory_1.get().toString());
        System.out.println("\tКласс получен - " + userFactory_2.get().toString());
        
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println("Example of UnaryOperator: " + square.apply(5)); // 25
        System.out.println("Example of UnaryOperator: " + square.apply(-11)); // 121
        
        BinaryOperator<Integer> multiply = (x, y) -> x*y;         
        System.out.println("Example of BinaryOperator: " + multiply.apply(3, 5)); // 15
        System.out.println("Example of BinaryOperator: " + multiply.apply(10, -2)); // -20
        
    } // End of public 'static void main(String[] args)'
    
     private static class ClassA {    }
}
