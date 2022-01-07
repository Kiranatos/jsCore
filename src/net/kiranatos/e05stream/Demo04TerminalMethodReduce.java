package net.kiranatos.e05stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo04TerminalMethodReduce {
    public static void main(String[] args) {
        /******************** .reduce() / сокращение **************************/
        System.out.println(".reduce() Позволяет выполнять агрегатные функции "
                + "на всей коллекцией и возвращать один результат.\n");
        /*  Aggregate function - мат.функции, применяемые к набору входных данных
        и возвращающие по ним одно результирующее значение.
        Three methods varaints:
            1) Optional<T> reduce(BinaryOperator<T> accumulator) - возвращает 
        результат в виде объекта Optional<T>
            2) T reduce(T identity, BinaryOperator<T> accumulator)        
            3) U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
        use on parallelized streams.
        
            Identity – an element that is the initial value of the reduction 
        operation and the default result if the stream is empty. Элемент, который
        является начальным значением операции сокращения и результатом по умолчанию,
        если поток пуст.
            Accumulator – a function that takes two parameters: a partial result
        of the reduction operation and the next element of the stream. Функция, 
        которая принимает два параметра: частичный результат операции сокращения
        и следующий элемент потока.
            Combiner – a function used to combine the partial result of the 
        reduction operation when the reduction is parallelized or when there's 
        a mismatch between the types of the accumulator arguments and the types 
        of the accumulator implementation. Объединитель – функция, которая 
        принимает два параметра: частичный результат операции сокращения и 
        следующий элемент потока. ОБЪЕДИНИТЕЛЬ – ФУНКЦИЯ, ИСПОЛЬЗУЕМАЯ ДЛЯ 
        ОБЪЕДИНЕНИЯ ЧАСТИЧНОГО РЕЗУЛЬТАТА ОПЕРАЦИИ СОКРАЩЕНИЯ, КОГДА СОКРАЩЕНИЕ
        РАСПАРАЛЛЕЛИВАЕТСЯ ИЛИ КОГДА СУЩЕСТВУЕТ НЕСООТВЕТСТВИЕ МЕЖДУ ТИПАМИ
        АРГУМЕНТОВ АККУМУЛЯТОРА И ТИПАМИ РЕАЛИЗАЦИИ АККУМУЛЯТОРА.

        Когда поток выполняется параллельно, среда выполнения Java разбивает 
        поток на несколько подпотоков. В таких случаях нам нужно использовать 
        функцию для объединения результатов подпотоков в один. Это роль объединителя.
        
        If we use sequential streams and the types of the accumulator arguments 
        and the types of its implementation match, we don't need to use a combiner. */
        
        System.out.println("\nExamples of 1st variant: ");
        
        Stream<String> streamString = Stream.of("pen", "pineapple", "apple", "pen");
        Optional<String> sentence = streamString.reduce((x, y) -> x + '*' + y);
        System.out.println("\t" + sentence.get());
        
        System.out.println("\nExamples of 2nd variant: ");
        
        Stream<Integer> streamInteger = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        Integer sumInteger = streamInteger.reduce(-100, Integer::sum);        
        System.out.println("\t" + sumInteger + " (sum of numbers)");
        streamInteger = Stream.empty();
        sumInteger = streamInteger.reduce(-100, (x, y) -> x + y);
        System.out.println("\t" + sumInteger + " (sum of empty stream)");
                
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String resultStr = letters.stream().reduce("&", String::concat);        
        System.out.println("\t" + resultStr + " (concatenation of letters)");

        Stream<BigInteger> streamBigInt = MyHelpClass.getSetBigInteger().stream();
        BigInteger sumBR3 = streamBigInt.reduce(BigInteger.ZERO, BigInteger::add); // BigInteger::add - применяется к каждой паре стрима бинарным оператором, пока не останется один
        System.out.println("\t" + sumBR3.toString() + " (sum of BigInteger numbers)");
        
        System.out.println("\nExamples of 3rd variant: ");
        
        /* When we use parallelized streams, we should make sure that reduce()
        or any other aggregate operations executed on the streams are:
        - associative: the result is not affected by the order of the operands.
        - non-interfering: the operation doesn't affect the data source.
        - stateless and deterministic: the operation doesn't have state and
        produces the same output for a given input.
        We should fulfill all these conditions to prevent unpredictable results.
        Parallelized streams are much more performant than the sequential counterparts.
        Кроме того, функция объединителя должна быть совместима с функцией 
        аккумулятора, для всех u и t должно выполняться следующее:
        combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
        
        Обратить внимание! Ключевой момент для понимания, связь типов T и U:
        reduce 3rd method:  U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
        in original:        BiFunction<T,K,R> = R apply(T t, K k)
        in reduce method:   BiFunction<U, ? super T, U> = U apply(U u, ? super T t)
        in reduce method:   BinaryOperator<U> extends BiFunction<U,U,U> = U apply(U u1, U u2) 
        so reduce 3rd method look like:  U reduce(U identity, (U u, T t) -> {...}, (U u1, U u2) -> {...})
        
        Обратить внимание: в 1st & 2nd methods accumulator is :
            BinaryOperator<T> accumulator = T apply(T t1, T t2) - тип должен совпадать с T identity             
        but in 3rd method:
            BiFunction<U,? super T,U> accumulator = U apply(U u, ? super T t) */
        
        // U = Integer, T = Integer
        List<Integer> listCR1 = Arrays.asList(25, 25, 45, 5, 1000, 1);
        int sumCR1 = listCR1.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        System.out.println("\t" + sumCR1 + " (sum of numbers)");
        
        // U = Integer, T = Phone
        Stream<Phone> phoneStreamCR3 = Stream.of(
                new Phone("iPhone 6 S", 54000), 
                new Phone("Lumia 950", 45000), 
                new Phone("Samsung Galaxy S 6", 40000), 
                new Phone("LG G 4", 32000));        
        int sum = phoneStreamCR3
                .parallel()
                .reduce(0,
                        (totalСost, phone) -> phone.getPrice() < 50000 ? totalСost + phone.getPrice() : totalСost + 0, 
                        (a, b) -> a + b);
        System.out.println("\t" + sum + " (sum of phones' prices that < 50000)"); // 117000
        
        // U = Integer, T = User
        List<User> users = User.getListUsers(true);
        /* This code won't compile: 
        int computedAges = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge());
        In this case, we have a stream of User objects, and the types of the accumulator arguments are Integer and User.
        Чтобы код сработал, partialAgeResult и user должны быть одного типа, см. BinaryOperator<T> accumulator метод выше.        */
        Optional<Integer> optSumInt = users
                .stream()
                .map(a -> a.getAge())
                .reduce((intSumOfAges, age) -> intSumOfAges + age);
        System.out.println("\t" + optSumInt + "\t(sum of ages via 1st method, where intSumOfAges and age are int type)");
        /* * * * */
        Optional<User> optSumUser = users
                .stream()                
                .reduce((aggregateUser, user) -> 
                        aggregateUser.setAge(aggregateUser.getAge() + user.getAge())
                );
        System.out.println("\t" + optSumUser + "\t(sum of ages via 1st method, where aggregateUser and user both have User type)");
        for (User u : users) System.out.println("\t\t" + u);
        users = User.getListUsers(true);
        /* * * * */
        int sumOfAges = users
                .stream()
                .reduce(
                        new User("Sum Of All Ages", 0), 
                        (aggregateUser, user) -> aggregateUser.setAge(aggregateUser.getAge() + user.getAge())
                ).getAge();
        System.out.println("\t" + sumOfAges + " (sum of ages via 2nd method, where aggregateUser and user both have User type)");
        /* * * * */
        sumOfAges = users
                .stream()
                .map(a -> a.getAge())
                .reduce(0, (intSumOfAges, age) -> intSumOfAges + age);
        System.out.println("\t" + sumOfAges + " (sum of ages via 2nd method, where intSumOfAges and age are int type)");
        /* * * * */
        sumOfAges = users.stream()
                .parallel()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        System.out.println("\t" + sumOfAges + " (sum of ages via 3rd method)");
           
        // U = ArrayList, T = Integer
        List<Integer> listOfIntegers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> resultIntegers = listOfIntegers
                .stream()
                //.parallel()
                .reduce(new ArrayList<>(),
                        (list, valueInteger) -> {
                            list.add(valueInteger * 10);
                            return list; },
                        (list1, list2) -> { /*
Як використовувати combiner при типах U = ArrayList, T = Integer так і не придумав.
Тому тут НЕпаралельний стрим, а одже combiner ні нащо не впливає.
Проте даний вараінт цікавий тим, що тут в accumulator можна використовувати різні типи, тоді як у 1 і 2 варантах метода, в accumulator типи мають співпадати.
                            */ return list1;
                        });
        System.out.println("\t" + resultIntegers + " (result via 3rd method NON-parallel stream)");

        // U = ArrayList, T = String
        List<String> stream5 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        List<String> stream5result = stream5
                .stream()
                //.parallel()
                .reduce(new ArrayList(), 
                        (list, symbol) -> {
                            list.add('(' + symbol + ')');
                            return list;                            
                        },
                        //те саме що і вище: НЕпаралельний стрим, combiner не працює.
                        (list1, list2) -> list1
                );           
        System.out.println("\t" + stream5result + " (result via 3rd method NON-parallel stream)");
        
        // U = String, T = String
        String str5result = stream5                
                .parallelStream()
                .reduce("0", 
                        (s1, s2) -> s1 + "+" + s2,
                        (s1, s2) -> s1 + "*" + s2
                );   
        System.out.println(str5result);
    }
}
/* Used sources for this summary class:
https://www.baeldung.com/java-stream-reduce
https://coderoad.ru/55970149/%D0%9A%D0%B0%D0%BA-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%D0%B5%D1%82-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4-reduce-%D0%B2-Java-8
*/



