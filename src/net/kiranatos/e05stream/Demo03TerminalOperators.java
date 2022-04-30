package net.kiranatos.e05stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo03TerminalOperators {
    private static int n = 1;
    public static void main(String[] args) {
        IntStream stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        System.out.println("\n\t << III. TERMINAL OPERATORS OF STREAMS >> \n" +
                " Oбрабатывают элементы, запускают и завершают работу стрима, Тerm.Оperat. в цепочке может быть только один!");
        
        /********************* .forEach ***************************************/
        System.out.printf("\n\n%d) .forEach( cosumer ) Применяет функцию к каждому объекту стрима, "
                + "порядок при параллельном выполнении не гарантируется \n Example:\n", n++);
        stream.forEach(System.out::print); // Вывод каждого элемента на экран
        
        /********************* .forEachOrdered​ ***************************************/
        System.out.printf("\n\n%d) .forEachOrdered​( cosumer ) Выполняет действие для каждого эл., но перед этим добивается правильного порядка вхождения элементов. \n"
                + "Используется для параллельных стримов, когда нужно получить правильную последовательность элементов. \n Example:\n"
                + " forEach parallel : ", n++);
        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEach(System.out::print); // 5, 6, 7, 3, 4, 8, 0, 9, 1, 2
        System.out.print("\n forEachOrdered parallel : ");
        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEachOrdered(System.out::print); // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        
        /********************* .iterator ***************************************/
        System.out.printf("\n\n%d) .iterator() Terminal operation, returns an iterator for the elements of this stream."
                + "\n Example:\n", n++);        
        Iterator<Integer> streamIterator = Stream.iterate(3, f -> f + 1)
                .peek(x -> System.out.println("\t\tpeek"))
                .limit(4)                
                .iterator();        
        while ( streamIterator.hasNext() )
            System.out.printf("\t%d in while cycle%n", streamIterator.next());
        
        /********************* .findFirst(), .findAny() **********************/
        stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        System.out.printf("\n\n%d) .findFirst() Возвращает 1й подходящий элемент обёрнутый в OptionalInt т.к. стрим может оказаться пустым \n" +
                " .findAny()  Возвращает любой подходящий элемент в OptionalInt \n Example:\n", n++);
        OptionalInt result = stream
                .filter(n -> n==5)
                .findFirst();
        System.out.printf(" .findFirst() = %d \n", result.getAsInt()); // throw NoSuchElementException if no element
        result = IntStream.of(2,5,6,5,8,1,9,2,2)
                .filter(n -> n==200)
                .findAny();
        System.out.printf(" .findAny() = %d (элемент не может быть найден, вернёт заданную альтернативу)", result.orElse(555));
        
        /********************* .allMatch , .anyMatch , .noneMatch *************/
        System.out.printf("\n\n%d) Проверяет, соотв. ли условию эл-т(ы)\n\t" +
                " .allMatch(predicate) - все \n\t" +
                " .anyMatch(predicate) - хотя бы один \n\t" +
                " .noneMatch(predicate) - ни один \n" +
                " Example:\n", n++);
        Stream<String> streamString = MyHelpClass.getSet().stream();
        boolean allStringAreAtLeast10Chars = streamString.allMatch(s -> s.length()>10);
        System.out.printf(" %b %b %b ",
                allStringAreAtLeast10Chars,
                MyHelpClass.getSet().stream().anyMatch(s -> s.length()>10),
                MyHelpClass.getSet().stream().noneMatch(s -> s.length()>20)
                );
        
        /********************* .min/.max **************************************/
        System.out.printf("\n\n%d) .min/max( comparator ) Возвращает min/max элемент обёрнутый в OptionalInt \n\t" + 
                " для стримов объектом можна передавать компаратор. \n Example:\n", n++);
        streamString = MyHelpClass.getSet().stream();
        Optional<String> minString = streamString.min(
                Comparator.comparing(String::length, Integer::compare)     );        
        OptionalInt maxInt = IntStream.of(2,5,6,5,8,1,9,2,2).max();
        System.out.printf(" MIN = %s, MAX = %d ", minString.get(), maxInt.getAsInt());
        
        /********************* .count() ***************************************/
        System.out.printf("\n\n%d) .count() Загальна к-сть ел. в стрімі після виконання операцій \n Example:\n", n++);
        stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        long count = stream.count();
        System.out.println(" total = " + count);
        
        /********************* .sum() *****************************************/
        System.out.printf("\n\n%d) .sum() Сумма всіх елементів \n Example:\n", n++);
        stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        int su = stream.sum();
        System.out.println(" sum = " + su);
    }    
}