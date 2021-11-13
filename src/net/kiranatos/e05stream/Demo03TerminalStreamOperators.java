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

public class Demo03TerminalStreamOperators {
    private static int n = 1;
    public static void main(String[] args) {
        IntStream stream = IntStream.of(2,5,6,5,8,1,9,2,2);        
        System.out.println("\n\t << III. TERMINAL OPERATORS OF STREAMS >> \n" +
                " Oбрабатывают элементы, запускают и завершают работу стрима, Тerm.Оperat. в цепочке может быть только один!");
        
        /********************* .forEach ***************************************/
        System.out.printf("\n\n%d) .forEach( cosumer ) Применяет функцию к каждому объекту стрима, порядок при параллельном выполнении не гарантируется \n Example:\n", n++);
        stream.forEach(System.out::print); // Вывод каждого элемента на экран
        
        /*
        void forEachOrdered​(Consumer action)
Тоже выполняет указанное действие для каждого элемента стрима, но перед этим добивается правильного порядка вхождения элементов. Используется для параллельных стримов, когда нужно получить правильную последовательность элементов.
[копировать] [скачать]

    IntStream.range(0, 100000)
        .parallel()
        .filter(x -> x % 10000 == 0)
        .map(x -> x / 10000)
        .forEach(System.out::println);
    // 5, 6, 7, 3, 4, 8, 0, 9, 1, 2
     
    IntStream.range(0, 100000)
        .parallel()
        .filter(x -> x % 10000 == 0)
        .map(x -> x / 10000)
        .forEachOrdered(System.out::println);
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9

        */
//        
//        Iterator<Integer> ttt = Stream.iterate(0, f -> f + 1)
//                .limit(100)
//                .iterator();
        
        
        /********************* .findFirst() , .findAny() **********************/
        stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        System.out.printf("\n\n%d) .findFirst() Возвращает 1й подходящий элемент обёрнутый в OptionalInt т.к. стрим может оказаться пустым \n" +
                " .findAny()  Возвращает любой подходящий элемент в OptionalInt \n Example:\n", n++);
        OptionalInt result = stream
                .filter(n -> n==5)
                .findFirst();
        System.out.printf(" .findFirst() = %d \n", result.getAsInt());
        result = IntStream.of(2,5,6,5,8,1,9,2,2)
                .filter(n -> n==200)
                .findAny();
        System.out.printf(" .findAny() = %d (элемент не может быть найден, вернёт заданную альтернативу)", result.orElse(555));
        
        /********************* .allMatch , .anyMatch , .noneMatch *************/
        System.out.printf("\n\n%d) .allMatch( predicate ) Проверяет, что все эл. соотв. условию \n\t" +
                " .anyMatch() хотя бы один \n\t" +
                " .noneMatch() ни один \n" +
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
    
        /********************* .reduce() *****************************************/
        System.out.printf("\n\n%d) .reduce() Позволяет выполнять агрегатные функции " +
                "на всей коллекцией и возвращать один результат \n Example:\n", n++);
        Stream<BigInteger> streamBigInt = MyHelpClass.getSetBigInteger().stream();        
        BigInteger summma = streamBigInt.reduce(BigInteger.ZERO, BigInteger::add);        
        System.out.println(summma.toString());
        // BigInteger::add - применяется к каждой паре стрима бинарным оператором, пока не останется один 
        // BigInteger.ZERO - если стрим был пуст, то возвращается некое нулевое значение
        
//        Stream<Integer> s;
//        Integer sum1 = s.reduce(0, (x,y) -> x+y);
//        Optional<Integer> sum2 = s.reduce((x,y) -> x+y);
//        Stream<T>{
//            <U> U reduce(U identity, BiFunctional<U,T,U> accumulator,
//            BinaryOperator<U> combiner)   }

        /********************* R .collect​(Collector collector) ***************************************/
        System.out.printf("\n\n%d) .collect( collector ) Повертає стрім у вигляді однієй з коллекцій або іншої структури данних \n\t" + 
                " Тип коллекції визначається коллектором " +
                "\n Example: \t", n++);
        streamString = MyHelpClass.getSet().stream();
        List<String> listStr = streamString.collect(Collectors.toList());
        for (String str : listStr) {
            System.out.print(str + " ");            
        }
        
        /*
        R collect​(Collector collector)
Один из самых мощных операторов Stream API. С его помощью можно собрать все 
        элементы в список, множество или другую коллекцию, сгруппировать 
        элементы по какому-нибудь критерию, объединить всё в строку и т.д.. 
        В классе java.util.stream.Collectors очень много методов на все случаи 
        жизни, мы рассмотрим их позже. При желании можно написать свой коллектор, 
        реализовав интерфейс Collector.

    List<Integer> list = Stream.of(1, 2, 3)
        .collect(Collectors.toList());
    // list: [1, 2, 3]
     
    String s = Stream.of(1, 2, 3)
        .map(String::valueOf)
        .collect(Collectors.joining("-", "<", ">"));
    // s: "<1-2-3>"



R collect​(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
То же, что и collect(collector), только параметры разбиты для удобства. Если нужно быстро сделать какую-то операцию, нет нужды реализовывать интерфейс Collector, достаточно передать три лямбда-выражения.

supplier должен поставлять новые объекты (контейнеры), например new ArrayList(), accumulator добавляет элемент в контейнер, combiner необходим для параллельных стримов и объединяет части стрима воедино.
[копировать] [скачать]

    List<String> list = Stream.of("a", "b", "c", "d")
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    // list: ["a", "b", "c", "d"]

        */

		

        
        
        
        
        //collection.stream().filter((s) -> s.contains(«1»)).collect(Collectors.toList())
        //collection.stream().sorted().collect(Collectors.toList())
//        stream = IntStream.of(9,2,2,7,9,7,8,6,10,4,4,6);
//        stream = stream.sorted().collect(Collectors.toList());
//        stream.forEach((e) -> System.out.print("," + e));
//    
        
    }    
}