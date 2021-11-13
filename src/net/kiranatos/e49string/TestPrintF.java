package net.kiranatos.e49string;

import java.util.Date;
import java.util.Locale;

/*
https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
https://docs.oracle.com/javase/tutorial/essential/io/formatting.html
Date:
https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#dt

Check:
http://study-java.ru/uroki-java/formatirovanie-chisel-i-texta-v-java/
https://javarush.ru/groups/posts/1412-formatiruem-vihvod-chisel-v-java
https://spec-zone.ru/RU/Java/Docs/8/api/java/util/Formatter.html
ThreadsL2L07T1
ThreadsL2L07T2
ThreadsL2L07T3
*/

public class TestPrintF {    
        /*        %[argument_index$][flags][width][.precision][conversion]
        % — Begin Format Specifier. Cпециальный символ, обозначающий начало инструкций форматирования.
        [argument_index$] — целое десятичное число, указывающее позицию аргумента в списке аргументов. 
    Ссылка на первый аргумент "1$", ссылка на второй аргумент "2$" и т.д.  
    Если позиция не задана, то аргументы должны находиться в том же порядке, что и ссылки на них в строке форматирования.
    НЕ ОБЯЗАТЕЛЬНАЯ часть инструкции.
        [flags] — специальные символы для форматирования. Например, флаг "+" означает, 
    что числовое значение должно включать знак +, флаг "-" означает выравнивание результата по левому краю,
    флаг «,» устанавливает разделитель тысяч у целых чисел.
    НЕ ОБЯЗАТЕЛЬНАЯ часть инструкции.
        [width] — положительное целое десятичное число, которое определяет минимальное количество символов, которые будут выведены.
    НЕ ОБЯЗАТЕЛЬНАЯ часть инструкции.
        [.precision] — не отрицательное целое десятичное число с точкой перед ним. Обычно используется для 
    ограничения количества символов. Специфика поведения зависит от вида преобразования.
    НЕ ОБЯЗАТЕЛЬНАЯ часть инструкции.
        [conversion] — это символ, указывающий, как аргумент должен быть отформатирован. 
        Например d для целых чисел, s для строк, f для чисел с плавающей точкой.
    ОБЯЗАТЕЛЬНАЯ часть инструкции. */    
    public static void main(String[] args) {
        System.out.println("\t\tExamples: ");
        System.out.printf("1) Symbols: %% %n");
        System.out.printf("Test: %c\n", 'a' 	);
        System.out.printf("1) Types: %s %c %c %b %d %f", 
                "string", 'z', '\u0063', true, 10, Math.PI);
        System.out.printf("%n2)%n %5d %n %6d %n %6d %n %6d %n %6d %n %6d %n %6d %n", 
                77777777, 666666, 55555, 4444, 333, 22, 1);        

        System.out.printf("3) e= %2$+20.5f %npi= %1$+020.10f %n", Math.PI, Math.E);
      /*f — указывает на то, что выводим число с плавающей точкой.
        .10 — выведенное число будет содержать 10 знаков после запятой.
        20 — всего выведенное число будет иметь ширину в 20 символов
        +0 — недостающие (до 20-ти) символы будут заполнены нулями, перед числом будет указан знак (+)
        1$ — данный формат применяется к первому аргументу, который находится после строки форматирования. */

        System.out.println("\n4) Пример использования String.format:");
        String s = String.format("Курс валют: %-4s%-8.4f  %-4s%-8.4f","USD", 58.4643, "EUR", 63.3695);
        System.out.println(s);
                
        System.out.println("\n\t\t << ЦЕЛЫЕ ЧИСЛА >>");
        System.out.println("5) Вывод целого числа с разделением тысяч:");
        System.out.printf("%,d", 7845); // --> "7 845"
        System.out.println("\n6) Число менее 7 знаков будет «подвинуто» вправо на недостающее количество знаков.");
        System.out.printf("%7d", 7845); // --> "   7845"        
        System.out.println("\n7) Число будет выровнено по левому краю и, если оно менее 7 знаков, то будет дополнено пробелами справа на недостающее количество знаков:");
        System.out.printf("%-7d", 7845); // --> "7845   "
        System.out.println("\n8) Число менее 7 знаков будет дополнено нулями слева на недостающее количество знаков:");
        System.out.printf("%07d", 7845); // --> "0007845"        
        System.out.println("\n9) Число будет дополнено знаком + и, если оно менее 7 знаков, то будет дополнено нулями на недостающее количество знаков:");
        System.out.printf("%+07d", 7845); //--> "+007845"   
        System.out.println("\n\t\t << ЧИСЛА С ПЛАВАЮЩЕЙ ТОЧКОЙ >>");
        System.out.println("10) Вывод числа e. Автоматическое округление до 6 знаков после запятой:"); // --> "2,718282"
        System.out.printf("%f", Math.E); // --> "2,718282"
        System.out.println("\n11) Число менее 10 знаков будет подвинуто вправо на недостающее количество знаков:");
        System.out.printf("%10f", Math.E); // --> "  2,718282"
        System.out.println("\n12) Число менее 10 знаков будет дополнено нулями слева на недостающее количество знаков:");
        System.out.printf("%010f", Math.E); // --> "002,718282"
        System.out.println("\n13) Число будет дополнено знаком + и, если оно менее 10 знаков, то будет дополнено нулями на недостающее количество знаков:");
        System.out.printf("%+010f", Math.E); // --> "+02,718282"        
        System.out.println("\n14) Число будет выведено с 15 знаками после запятой:");
        System.out.printf("%.15f", Math.E); // --> "2,718281828459045"        
        System.out.println("\n15) Число будет выведено с  3-мя знаками после запятой и, если оно менее 8 символов, то  будет «подвинуто» вправо на недостающее количество знаков:");
        System.out.printf("%8.3f", Math.E); // --> "   2,718"        
        System.out.println("\n16) Число будет выровнено по левому краю, выведено с  3-мя знаками после запятой и, если оно менее 8 знаков, то будет дополнено пробелами справа на недостающее количество знаков:");
        System.out.printf("%-8.3f", Math.E); // --> "2,718   "
        System.out.println("\n\t\t << СТРОКИ >>");
        System.out.println("17) Если строка содержит менее 10 символов, то подвинуть ее вправо на недостающее количество символов:");
        System.out.printf("%10s", "Hello"); // --> "     Hello"
        System.out.println("\n18) Строка будет выровнена по левому краю и, если она менее 10 символов, то будет дополнена справа пробелами на недостающее количество символов:");
        System.out.printf("%-10s", "Hello"); // --> "Hello     "
        System.out.println("\n19) Выведет первые 3 символа строки:");
        System.out.printf("%.3s", "Hello"); // --> "Hel"
        System.out.println("\n20) Выведет первые 3 символа строки и подвинет их вправо на недостающее до 8 количество символов:");
        System.out.printf("%8.3s", "Hello"); // --> "     Hel"
        
        System.out.println("\n\t\t << ЛОКАЛИЗАЦИЯ >> ");
        System.out.printf(Locale.ENGLISH,"%.2f%n", 9.87 ); //9.87
        System.out.printf(Locale.FRANCE,"%.2f%n", 9.87 ); //9,87
        System.out.printf(Locale.ENGLISH,"%,d%n", 1000000 );// 1,000,000
        System.out.printf(Locale.GERMAN,"%,d%n", 1000000 ); // 1.000.000
        System.out.printf(Locale.FRANCE,"%,d%n", 1000000 ); // 1 000 000        
    }    
}
