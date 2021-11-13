package net.kiranatos.e05stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* sourses:
https://annimon.com/article/2778#characteristics
        остановился на Object[] toArray​()

https://habr.com/ru/company/luxoft/blog/270383/
*/

public class StreamsExamples {
    public static void main(String[] args) {
        /*
        Пока мы не присоединили терминальный оператор, доступа к источнику не 
        проводилось. Как только появился терминальный оператор forEach, он стал 
        запрашивать элементы у стоящего перед ним оператора limit. Тот в свою 
        очередь обращается к map, map к filter, а filter уже обращается к источнику. 
        Затем элементы поступают в прямом порядке: источник, filter, map, limit 
        и forEach. Пока какой-либо из операторов не обработает элемент должным 
        образом, новые запрошены не будут. Как только через оператор limit 
        прошло 3 элемента, он переходит в закрытое состояние и больше не будет 
        запрашивать элементы у map. forEach запрашивает очередной элемент, но 
        limit сообщает, что больше не может поставить элементов, поэтому forEach 
        делает вывод, что элементы закончились и прекращает работу.
        
        Такой подход зовётся pull iteration, то есть элементы запрашиваются у 
        источника по мере надобности. 
        К слову, в RxJava реализован push iteration подход, то есть источник 
        сам уведомляет, что появились элементы и их нужно обработать.
        */
        Stream.of(120, 410, 85, 32, 314, 12)
                .filter(v -> v < 300)
                .map(x -> x + 11)
                .limit(3)
                .forEach(System.out::println);        
        System.out.println(" =============================================== ");
        /*
        Стримы бывают последовательными (sequential) и параллельными (parallel). 
        Последовательные выполняются только в текущем потоке, а вот параллельные 
        используют общий пул ForkJoinPool.commonPool(). При этом элементы разбиваются 
        (если это возможно) на несколько групп и обрабатываются в каждом потоке 
        отдельно. Затем на нужном этапе группы объединяются в одну для 
        предоставления конечного результата.
        Чтобы получить параллельный стрим, нужно либо вызвать метод 
        parallelStream() вместо stream(), либо превратить обычный стрим в 
        параллельный, вызвав промежуточный оператор parallel.
        */        
        MyHelpClass.getListInt().parallelStream()
                .filter(x -> x > 10)
                .map(x -> x * 2)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println(" sum = " + 
                IntStream.range(0, 10)
                        .parallel()
                        .map(x -> x * 10)
                        .sum()
        );
        System.out.println(" =============================================== ");
        IntStream.range(0, 10).limit(5).skip(3).forEach(System.out::println);
        


        

    }
}
