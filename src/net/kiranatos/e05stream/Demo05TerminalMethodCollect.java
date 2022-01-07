package net.kiranatos.e05stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo05TerminalMethodCollect {
    public static void main(String[] args) {
        System.out.println(".collect( collector ) Повертає стрім у вигляді однієй з коллекцій або іншої структури данних \n\t" + 
                " Тип коллекції визначається коллектором " +
                "\n Example: \t");
        Stream<String> streamString = MyHelpClass.getSet().stream();
        List<String> listStr = streamString.collect(Collectors.toList());
        for (String str : listStr) {
            System.out.print(str + " ");            
        }
        
        /*
        <R,A> R collect(Collector<? super T,A,R> collector)
Performs a mutable reduction operation on the elements of this stream using a Collector.
<R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
Performs a mutable reduction operation on the elements of this stream.
        
        
        R collect​(Collector collector)
Один из самых мощных операторов Stream API. С его помощью можно собрать все 
        элементы в список, множество или другую коллекцию, сгруппировать 
        элементы по какому-нибудь критерию, объединить всё в строку и т.д.. 
        В классе java.util.stream.Collectors очень много методов на все случаи 
        жизни. При желании можно написать свой коллектор, реализовав интерфейс Collector.

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
