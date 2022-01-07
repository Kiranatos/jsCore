package net.kiranatos.e05stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo02IntermediateStreamOperators {
    private static int n = 1;
    public static void main(String[] args) {
        IntStream stream = IntStream.of(2,5,6,5,8,1,9,2,2);
        System.out.println("\n\t << II. INTERMEDIATE OPERATORS OF STREAMS (Промежуточный)>> \n" +
                "Обрабатывают/преобразовывают поступающие элементы и возвращают новый стрим. \n" +
                "Промежуточных операторов в цепочке обработки элементов может быть много.");
        
        /********************* .filter ***************************************/
        System.out.printf("\n\n%d) .filter(predicate) Отфильтровывает записи, "
                + "возвращает только те что соответствуют условию \n Example:\n", n++);
        stream = stream.filter(n -> n%5 == 0 && n % 2 != 0); /// возвращает числа, которые делятся на 5 и не дел. на 2
        stream.forEach(System.out::println);
        
        /********************* .limit ***************************************/
        System.out.printf("\n\n%d) .limit(long) Возвращает определённой количество эл. \n Example:\n", n++);
        Stream<BigInteger> streamBigInt = Stream.iterate( new BigInteger("10"), b -> b.add(b) );
        streamBigInt = streamBigInt.limit(13);
        streamBigInt.forEach(System.out::println);
        
        /********************* .map ***************************************/
        System.out.printf("\n\n%d) .map(IntUnaryOperator) Преобразует каждый элемент стрима \n Example:\n", n++);
        stream = IntStream.of(9,2,2,7,9,12,8,6,10,4,5,6);
        stream = stream.map(n -> n * n);
        stream.forEach(System.out::println);
        
        /*
        Stream.mapToDouble​(ToDoubleFunction mapper)
Stream.mapToInt​(ToIntFunction mapper)
Stream.mapToLong​(ToLongFunction mapper)
IntStream.mapToObj(IntFunction mapper)
IntStream.mapToLong(IntToLongFunction mapper)
IntStream.mapToDouble(IntToDoubleFunction mapper)
        
        Stream.of("10", "11", "32")
    .map(x -> Integer.parseInt(x, 16)) // основа системи числення
    .forEach(System.out::println);
        
        */
        
        /********************* .mapToObj() ***************************************/
        System.out.printf("\n\n%d) <Generic> .mapToObj() Принимает функцию и преобразует каждый элемент стрима обратно в объектный \n Example:\n", n++);
        stream = IntStream.of(9,2,2,7,9,12,8,6,10,4,5,6);
        Stream<String> streamString = stream.mapToObj(Integer::toString);
        streamString.forEach(System.out::print);
        
        /********************* .flatMapToInt() ***************************************/
        System.out.printf("\n\n%d) .flatMapToInt() Аналог map, принимает функцию и создает из одного элемента несколько \n Example:\n", n++);        
        streamString = MyHelpClass.getSet().stream();
        stream = streamString.flatMapToInt(s -> s.chars()); // строки в стрим символов IntStream
        stream.forEach(System.out::print);
        
        // flatMap избавляет от ситуации типо, чтоб не возвращать Стрим Стримов map-ом
        
        /*
        flatMap​(Function<T, Stream<R>> mapper)
Один из самых интересных операторов. Работает как map, но с одним отличием — 
        можно преобразовать один элемент в ноль, один или множество других.

flatMapToDouble​(Function mapper)
flatMapToInt​(Function mapper)
flatMapToLong​(Function mapper)

Как и в случае с map, служат для преобразования в примитивный стрим.

Для того, чтобы один элемент преобразовать в ноль элементов, нужно 
        вернуть null, либо пустой стрим. Чтобы преобразовать в один 
        элемент, нужно вернуть стрим из одного элемента, например, 
        через Stream.of(x). Для возвращения нескольких элементов, можно 
        любыми способами создать стрим с этими элементами.

    Stream.of(2, 3, 0, 1, 3)
        .flatMap(x -> IntStream.range(0, x))
        .forEach(System.out::println);
    // 0, 1, 0, 1, 2, 0, 0, 1, 2

            Stream.of(1, 2, 3, 4, 5, 6)
        .flatMap(x -> {
             switch (x % 3) {
                 case 0:
                     return Stream.of(x, x*x, x*x*2);
                 case 1:
                     return Stream.of(x);
                 case 2:
                 default:
                     return Stream.empty();
             }
         })
        .forEach(System.out::println);
    // 1, 3, 9, 18, 4, 6, 36, 72

        */
        
        /********************* .distinct() ************************************/
        System.out.printf("\n\n%d) .distinct() Возвращает стрим без дубликатов, "
                + "помечая его как содержащий уникальные элементы \n Example:\n", n++);
        stream = IntStream.of(9,2,2,7,9,7,8,6,10,4,4,6);
        stream = stream.distinct();
        stream.forEach(System.out::print);
        
        /********************* .sorted() **************************************/
        System.out.printf("\n\n%d) .sorted() Cортировка, возможно также подключить Comparator. "
                + "Eсли стрим уже помечен как отсортированный, то сортировка проводиться не будет \n Example 1: ", n++);
        stream = IntStream.of(9,2,2,7,9,7,8,6,10,4,4,6);
        stream = stream.sorted();
        stream.forEach((e) -> System.out.print("," + e));
        
        System.out.print("\n Example 2: ");
        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
        sortedList.forEach(System.out::print);
        
        System.out.print("\n Example 3: ");
        list.stream().sorted((o1,o2)-> o1.compareTo(o2)).collect(Collectors.toList()).forEach(System.out::print);
        
        System.out.print("\n Example 4 with Comparator.naturalOrder(): ");
        list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()).forEach(System.out::print);
                
        System.out.print("\n Example 5 with Comparator.reverseOrder(): ");
        sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sortedList.forEach(System.out::print);
        
        System.out.print("\n Example 6: ");
        list.stream().sorted((o1,o2)-> o2.compareTo(o1)).collect(Collectors.toList()).forEach(System.out::print);

        System.out.print("\n Example 7 with Comparator.comparingInt(): \n");
        User.getListUsers(true).stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList()).forEach(System.out::println);
        
        System.out.print(" Example 8: ");
        User.getListUsers(true).stream().sorted((o1, o2) -> o1.getAge() - o2.getAge()).collect(Collectors.toList()).forEach(System.out::print);
			
        
        
        
        
        /********************* .skip() ***************************************/
        System.out.printf("\n\n%d) .skip()Можно пропустить N первых элементов \n Example:\n", n++);
        stream = IntStream.of(9,2,2,7,9,7,8,6,10,4,4,6);
        stream = stream.skip(3);
        stream.forEach((e) -> System.out.print("," + e));
        
        /********************* .peek(Consumer) ***************************************/
        System.out.printf("\n\n%d) .peek(IntConsumer) Применяет функцию к каждому элементу стрима \n Example:\n", n++);
        stream = IntStream.of(1,3,5,7,11);
        stream = stream.peek(System.out::print);
        stream.forEach((e) -> System.out.print("," + e));
        // для дебага
        
        /*
        Выполняет действие над каждым элементом стрима и при этом возвращает стрим с элементами исходного стрима. Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов (вы же помните, что forEach — терминальный оператор и после него стрим завершается?), либо для отладки.
        Stream.of(0, 3, 0, 0, 5)
    .peek(x -> System.out.format("before distinct: %d%n", x))
    .distinct()
    .peek(x -> System.out.format("after distinct: %d%n", x))
    .map(x -> x * x)
    .forEach(x -> System.out.format("after map: %d%n", x));
        */
        
        /*
        takeWhile​(Predicate predicate)
Появился в Java 9. Возвращает элементы до тех пор, пока они удовлетворяют условию, 
        то есть функция-предикат возвращает true. Это как limit, только не с числом, а с условием.
        Stream.of(1, 2, 3, 4, 2, 5)
    .takeWhile(x -> x < 3)
    .forEach(System.out::println);
        
        IntStream.range(2, 7)
    .takeWhile(x -> x != 5)
    .forEach(System.out::println); // 2, 3, 4
        */
        
        /*
        Появился в Java 9. Пропускает элементы до тех пор, пока они удовлетворяют условию, затем возвращает оставшуюся часть стрима. Если предикат вернул для первого элемента false, то ни единого элемента не будет пропущено. Оператор подобен skip, только работает по условию.
[копировать] [скачать]

    Stream.of(1, 2, 3, 4, 2, 5)
        .dropWhile(x -> x >= 3)
        .forEach(System.out::println);
    // 1, 2, 3, 4, 2, 5
     
    Stream.of(1, 2, 3, 4, 2, 5)
        .dropWhile(x -> x < 3)
        .forEach(System.out::println);
    // 3, 4, 2, 5
    IntStream.range(2, 7)
        .dropWhile(x -> x < 5)
        .forEach(System.out::println);
    // 5, 6
     
    IntStream.of(1, 3, 2, 0, 5, 4)
        .dropWhile(x -> x % 2 == 1)
        .forEach(System.out::println);
    // 2, 0, 5, 6

        
        boxed()
Преобразует примитивный стрим в объектный.
[копировать] [скачать]

    DoubleStream.of(0.1, Math.PI)
        .boxed()
        .map(Object::getClass)
        .forEach(System.out::println);
    // class java.lang.Double
    // class java.lang.Double

        */
        
        
        /*
        Операции которорый в принципе бесполезны для потока, более системно работают:
        Stream<S> s.unordered(); - неупорядоченный поток
        Stream<S> s.parallel(); - выполнять в параллельном режиме
        Stream<S> s.sequentila(); - выполнять в однопоточном режиме
        */
                
    }    
}

//    streamString.flatMap((p) -> Arrays.asList(p.split(",")).stream()).toArray(String[]::new)

// Пример факторіала:
//    public static BigInteger factorial(int n) {
//        return IntStream.rangeClosed(1, n)
//                .mapToObj(i -> BigInteger.valueOf(i))
//                .reduce(BigInteger.ONE, BigInteger::multiply);        
//    }
//    
//    public static boolean isPalindrome (String s){
//        StringBuilder leftToRight = new StringBuilder();
//        s.chars()
//                .filter(Character::isLetterOrDigit)
//                .map(Character::toLowerCase)
//                .forEach(leftToRight::appendCodePoint);
//        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();
//        return leftToRight.toString().equals(rightToLeft.toString());
//    }
    
    /* Приклади:
    Stream<BigInteger> streamBigInt = Stream.iterate( new BigInteger("10"), b -> b.add(b) );
Расшифровка лямбды: 
    class Test implements UnaryOperator<BigInteger>{ 
        @Override public BigInteger apply(BigInteger b) { 
            return b.add(b); 
    } }
Стрим бесконечный, поэтому нужно аккуратно использовать, например, когда пытаешся 
сложить все элементы - произойдёт зацыкливание.

*/

/*
java.util.stream;
public interface Stream<T> extends BaseStream<T, Stream<T>> {}
Stream - generic interface параметризованный типом Т. Его варианты: DoubleStream, IntStream, LongStream.
Представляет собой средство обхода элементов, описания алгоритмов, оброботки и описания последовательности.
В отличие от коллекций, в Stream может быть бесконечное кол-во элементов.
Ко-во элементов в коллекции конечно и есть возможность выбирать, удалять, добавлять элементы индивидуально. 
Stream не влияет на источник єлементов.
*/
/* Этапы создания стрима:
1. получение стрима, откуда будут браться элементы
2. 0-... промежуточных методов преобразования (.filter .limit .map). Стрим их запоминает, но не выполняет пока.
3. терминальная опирация - запускает вычисление и является результатом. Применяется один раз. Стрим после єтого не пригодній больше к применению.
4. опциональный, заключительный шаг - закрытие стрима .close(). Применяется в случае, если нужно закрыть каие-то ресурсы(Чтение из файла и тд).
Благодаря тому, что стрим использует Closable - его можно использовать в try c ресурсами (1.2  1.3).
  */      
        

/*
Если кто-то захочет в itarate заменить n + 1 на инкремент, то пользуйтесь префиксной версией. Постфиксный вариант не сработает.
То есть, IntStream.iterate(1, n -> n + 1) можно заменить на  IntStream.iterate(1, n -> ++n)
Вариант  IntStream.iterate(1, n -> n++) будет каждый раз возвращать 1
*/

/*
Фильтр работает очень просто: если условие выполняется и возвращается true, 
то элемент проходит, а иначе нет. Однако, для промежуточных операций важен порядок.
IntStream.iterate(1, n -> n+1).limit(3);
Генерируем числа и берем первые три. Получаем 1, 2 и 3.
IntStream.iterate(1, n -> n+1).filter(n -> n%2 ==0).limit(3);
Генерируем числа, фильтруем, а потом берем первые три из тех, которые прошли фильтр. Получаем 2, 4 и 6.
IntStream.iterate(1, n -> n+1).limit(3).filter(n -> n%2 ==0);
Генерируем числа, берем первые три, а потом фильтруем. Получаем 2.
*/