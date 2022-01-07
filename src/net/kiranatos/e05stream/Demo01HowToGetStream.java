package net.kiranatos.e05stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo01HowToGetStream {
    private static int paragraph = 1;
    private final static int LIMIT = 15;
    private final static String PATH_TO_FILE = MyHelpClass.createFileForDemo01();
    
    public static void main(String[] args) throws IOException {
        System.out.println("\n\t << I. SOURCES OF STREAMS (Источник) >> \n" +
                " каждая последовательность одноразовая и недоступна для использования повторно !!! \n\n");
        
        /****** .iterate​(T seed, UnaryOperator f) ************** (everlasting)*/
        System.out.printf(" %d) IntStream.iterate(firstElem, intUnaryOperator) C помощью итерации функции \n" +
                " параметры: 1й элемент последовательности и функ.интерфейс IntUnaryOperator, который считает след.элементЫ \n" + 
                " возвращается бесконечная последовательность IntStream, начинающаяся с firstElem", paragraph++);
        IntStream intStream_01 = IntStream.iterate(1, n -> n + 10);
        System.out.println(" Example: ");
        intStream_01.limit(LIMIT).forEach(MyHelpClass::showElement);
        
        System.out.printf("\n Конечный стрим .iterate​(T seed, Predicate hasNext, UnaryOperator f) появился в Java 9:"
                + "\n если hasNex возвращает false, то стрим завершается \n");
        intStream_01 = IntStream.iterate(1, g -> g < 50, n -> n + 6);
        intStream_01.forEach(MyHelpClass::showElement);
        
        /****** .generate(Supplier) **************************** (everlasting)*/
        System.out.printf("\n\n %d) DoubleStream.generate Можно генерировать "
                + "динамически и бесконечно при помощи функ.интерф Supplier : ", paragraph++);
        DoubleStream doubleStream_02 = DoubleStream.generate(Math::random);
        System.out.println("\n Example 1 via Math::random: ");
        doubleStream_02.limit(LIMIT).forEach(MyHelpClass::showElement);
        
        System.out.println("\n Example 2 via AtomicInteger: ");
        AtomicInteger atomicInt = new AtomicInteger(0);
        Stream<Integer> streamInt = Stream.generate(atomicInt::getAndIncrement);
        streamInt.limit(LIMIT).forEach(MyHelpClass::showElement);
        
        /****** Arrays.stream() ***********************************************/
        System.out.printf("\n\n %d) Arrays.stream() Из масива: ", paragraph++);
        DoubleStream doubleStream_07 = Arrays.stream( new double[]{.5,.6,1.1,.9} );
        System.out.println("\n Example: ");
        doubleStream_07.forEach(MyHelpClass::showElement);
        
        /****** Коллекції.stream() ***********************************************/
        System.out.printf("\n\n %d) .stream() Можно получить из коллекций: ", paragraph++);
        Set<String> vocabulary = MyHelpClass.getSet();
        Stream<String> streamString_03 = vocabulary.stream();
        System.out.println("\n Example 1 [set(sized,distinct)]: ");
        streamString_03.limit(LIMIT).forEach(MyHelpClass::showElement);
        System.out.println("\n Example 2 [list(sized,ordered)]: ");
        MyHelpClass.getListInt().stream().forEach(System.out::print);
        Arrays.asList(" r1 ", " r2 ", " r3 ").stream().forEach(System.out::print);
        System.out.println("\n Example 3 [map]: ");
        System.out.println("\n Example 4 [TreeSet]: ");
        //TreeSet<T>
        // Stream<T> s = set.stream(); // sized, distinct, sorted, ordered
        
        /****** файл.lines() ***********************************************/
        System.out.printf("\n\n %d) .lines() Можно получить из файла: ", paragraph++);
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE));
        Stream<String> streamString_04 = reader.lines();
        System.out.println("\n Example: ");
        streamString_04.limit(LIMIT).forEach(MyHelpClass::showElement);
        reader.close();
        
        /****** Files.list(файл.getParent()) или Files.walk(файл.getParent())**/
        System.out.printf("\n\n %d) Files.list(path) Можно получить стрим состаящий из файлов и папок одного уровня директории, \n"
                + " или обойти рекурсивно Files.walk(path) нижние уровни и получить все подпапки и файлы директории : ", paragraph++);
        Path path = Paths.get(PATH_TO_FILE);
        Stream<Path> streamDir = Files.list(path.getParent());
        System.out.println("\n\t\t Example of scan one level folder: ");
        streamDir.limit(LIMIT+10).forEach(System.out::println);        
        streamDir = Files.walk(path.getParent());
        System.out.println("\t\t Example of deep scan folder: ");
        streamDir.limit(LIMIT+1000).forEach(System.out::println);
        
        /****** chars() *****************************************************/
        System.out.printf("\n\n %d) .chars() Превратить строку в последовательность: ", paragraph++);
        IntStream intStream_05 = "embarrassing remarks".chars();
        System.out.println("\n Example (вывод кодов символов): ");
        intStream_05.limit(LIMIT).forEach(System.out::print);
        
        /****** LongStream.range​(), IntStream.rangeClosed() *******************/
        System.out.printf("\n\n %d) IntStream.range(n1, n2) Задать диапазон целых чисел от n1 до n2 (n2 не включительно) или \n"
                + " IntStream.rangeClosed(k1, k2) от k1 до k2 (k2 включительно)", paragraph++);
        System.out.println("\n Example (не включительно): ");
        IntStream intStream_06 = IntStream.range(0, 10);        
        intStream_06.limit(LIMIT).forEach(System.out::print);
        System.out.println("\n Example (включительно): ");
        LongStream longStream_07 = LongStream.rangeClosed(-10L, 1L);        
        longStream_07.limit(LIMIT).forEach(System.out::print);
        
        /****** IntStream.of ***********************************************/
        System.out.printf("\n\n %d) IntStream.of(2,5,...) Задать элементы явно: ", paragraph++);
        intStream_01 = IntStream.of(2,5,6,5,8,10,22,-11);
        System.out.println("\n Example: ");        
        intStream_01.forEach(System.out::print);
        //Stream<String> a = Stream.of("a", "b", "c");
        
        /****** .concat() ***********************************************/
        System.out.printf("\n\n %d) IntStream.concat(stream1, stream2) Конкатинация двух стримов: ", paragraph++);
        intStream_01 = IntStream.concat( IntStream.of(2,5,6,5,8), 
                IntStream.of(-5,-8,-1,-2));        
        System.out.println(" Example: ");        
        intStream_01.forEach(System.out::print);
        
        /****** .empty() ***********************************************/
        System.out.printf("\n\n %d) IntStream.empty() Пустой стрим для подальших манипуляций : ", paragraph++);
        IntStream emptyStream = IntStream.empty();
        
        /****** .ofNullable() ***********************************************/
        System.out.printf("\n\n %d) ofNullable(T t) Появился в Java 9. "
                + "Возвращает пустой стрим, если в качестве аргумента передан null, \n"
                + "в противном случае, возвращает стрим из одного элемента. \n Example: ", paragraph++);
        String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
        Stream.ofNullable(str).forEach(System.out::println);
        
        /****** .build() ***********************************************/
        System.out.printf("\n\n %d) .build() Создаёт мутабельный объект для добавления элементов в стрим"
                + " без использования контейнеров: \n Example: ", paragraph++);
        Stream.Builder<Integer> streamBuider = 
                Stream.<Integer>builder().add(0).add(1);
        for (int i = 2; i <= 8; i += 2) { streamBuider.accept(i); }        
        Stream<Integer> intStreamBuild = streamBuider.add(9).add(10).build();
        intStreamBuild.forEach(System.out::print);       
                
        /****** .splitAsStream() ***********************************************/
        System.out.printf("\n\n %d) .splitAsStream() Creates a stream from the given input sequence around matches of this pattern"
                + "\n Example: ", paragraph++);
        streamString_04 = Pattern.compile("[0-9]").splitAsStream("word5ttt5dome4_4kisa wagag-Ga9fofo");
        streamString_04.limit(LIMIT).forEach(MyHelpClass::showElement);
        
        /****** RANDOM ***********************************************/
        System.out.printf("\n\n %d) Streams & Randoms."
                + "\n Example: ", paragraph++);
        DoubleStream s = new SplittableRandom().doubles();
        s.limit(LIMIT).forEach(n -> System.out.print(n + " "));
        System.out.print("\n Example .ints(кол-во эл., начало inclusive, конец exclusive: ");
        new Random().ints(10, 1, 7).forEach(n -> System.out.print(n + " "));
        System.out.print("\n Example: ");
        new SplittableRandom().ints(10, -1, 9).forEach(n -> System.out.print(n + " "));        
    }    
}