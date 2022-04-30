package net.kiranatos.e05stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo05TerminalStreamConvertation {
    private static int n = 1;
    public static void main(String[] args) {
        System.out.printf("%d) .boxed() method:%n", n++);
        IntStream intStream = IntStream.of(2,5,6,5,8,1,9,2,2);
        System.out.println("\t IntStream -> Stream<Integer>");
        Stream<Integer> streamInteger = intStream.boxed();        
        streamInteger.forEach(System.out::print);
        
        //intStream = streamInteger.;
    }
}
