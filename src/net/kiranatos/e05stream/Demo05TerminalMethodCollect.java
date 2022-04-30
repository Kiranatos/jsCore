package net.kiranatos.e05stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo05TerminalMethodCollect {
    public static void main(String[] args) {
        System.out.println(".collect( collector ) Повертає стрім у вигляді однієй з коллекцій або іншої структури данних \n\t" + 
                " Тип коллекції визначається коллектором. \n\tExamples:");
        
        System.out.println("************** I type of .collect() *************");
        /* I type: <R,A> R collect(Collector<? super T,A,R> collector)
        Performs a mutable reduction operation on the elements of this stream using a Collector.
        С его помощью можно собрать все элементы в список, множество или другую коллекцию, 
        сгруппировать элементы по какому-нибудь критерию, объединить всё в строку и т.д. 
        В классе java.util.stream.Collectors много методов на все случаи жизни.*/
        Stream<String> streamString = MyHelpClass.getSet().stream();
        List<String> listStr = streamString.collect(Collectors.toList());
        System.out.print("LIST: ");
        for (String str : listStr) {
            System.out.print(str + " ");            
        }
        
        // 2nd examle with list:
        List<Integer> listInt = IntStream.of(9,2,2,7,9,7,8,6,10,4,4,6)
                .sorted() // it is IntStream
                .boxed() // convert IntStream to Stream<Integer>
                .collect(Collectors.toList());
        
        
        Set<Integer> setInt = Stream.of(1, 2, 3, 5, 5, 5, 5).collect(Collectors.toSet());
        System.out.print("\nSET: ");
        for (Integer num : setInt) {
            System.out.print(num + " ");            
        }
        
        String stringCollected = Stream.of(1, 2, 3, 5, 5, 5, 5)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "<", ">")); // delimiter, prefix, suffix
        System.out.println("\nSTRING 1: " + stringCollected);
        System.out.println("STRING 2: " + Stream.of(1, 2, 3, 5, 5, 5, 5).map(String::valueOf).collect(Collectors.joining("-")));
        System.out.println("STRING 3: " + Stream.of(1, 2, 3, 5, 5, 5, 5).map(String::valueOf).collect(Collectors.joining()));
    
        System.out.println("************** II type of .collect() ************");
        /* II type: <R> R collect(  Supplier<R> supplier, 
                                    BiConsumer<R,? super T> accumulator, 
                                    BiConsumer<R,R> combiner )
        Performs a mutable reduction operation on the elements of this stream.
        То же, что и collect(collector), только параметры разбиты для удобства. 
        Если нужно быстро сделать какую-то операцию, нет нужды реализовывать 
        интерфейс Collector, достаточно передать три лямбда-выражения.
        supplier должен поставлять новые объекты (контейнеры), например new ArrayList(),
        accumulator добавляет элемент в контейнер,
        combiner необходим для параллельных стримов и объединяет части стрима воедино. */
        listStr = Stream.of("a", "b", "c", "d").collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.print("LIST: ");
        for (String str : listStr) {
            System.out.print(str + " ");            
        }
        
        System.out.println("\n************** User's own collector ***********");
        // Task: посчитать медианное значение длин строк.
        List<String> longWelcome = Arrays.asList("Hello", "and", "welcome", "to", "the", "wonderful", "world", "of", "java", "8");
        Integer resultInt = longWelcome
                .stream()
                .map(String::length)
                .collect(new MedianCollector());
        System.out.println(" result = " + resultInt);

    }
}

/* interface Collector<T, A, R>
    T - входной тип для коллектора 
    A - тип контейнера для хранения промежуточных вычислений
    R - выходной тип коллектора, который он возвращает
*/
class MedianCollector implements Collector<Integer, TreeSet<Integer>, Integer> {
    @Override
    public Supplier<TreeSet<Integer>> supplier() { // возвращает лямбда-выражение, создающее контейнер для хранения промежуточных выражений
        return TreeSet<Integer>::new;
    }
 
    @Override
    public BiConsumer<TreeSet<Integer>, Integer> accumulator() { // добавляет очередное значение в контейнер промежуточных значений. Если быть точным, то возвращает лямбда-выражение, которое обрабатывает очередное значение и сохраняет его.
        return TreeSet::add;
    }
 
    @Override
    public BinaryOperator<TreeSet<Integer>> combiner() { // возвращает лямбда-выражение, объединяющее два контейнера промежуточных значений в один. Дело в том, что Stream API может создать несколько таких контейнеров, для параллельной обработки и в конце слить их в один общий контейнер.
        return (k, r) -> { k.addAll(r); return k; };
    }
 
    @Override
    public Function<TreeSet<Integer>, Integer> finisher() { // возвращает лямбда-выражение, которое производит финальное преобразование: обрабатывает содержимого контейнера промежуточных результатов и приводит его к заданному выходному типу.
        return s -> {
            long size = s.size();
            if (size%2==0) {
                return Double.valueOf(s
                        .stream()
                        .skip(size % 2+2)
                        .limit(2)
                        .mapToInt(i->i)
                        .average()
                        .getAsDouble())
                        .intValue();
            }
            return s.stream()
                    .skip(size % 2+2)
                    .findFirst()
                    .get();
        };
    }
 
    @Override
    public Set<Characteristics> characteristics() { // для декларирования свойств коллектора.
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}