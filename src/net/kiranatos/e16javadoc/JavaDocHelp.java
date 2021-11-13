package net.kiranatos.e16javadoc;

/**
 * Text above class
 * text with <strong>strong tag</strong>
 * Картинка: <img src="doc-files/ava.jpg" alt="альтернативный текст"/>
 * web link <a href="#{@link}">{@link URL}</a>
 * <p> paragraph tag
 *
 * @author Kiranatos - по умолчанию отключено и не берётся в javadoc
 * @version 1.1 - по умолчанию отключено и не берётся в javadoc
 * @since предыдущая версия
 */
public class JavaDocHelp {
    /**
     * Text above field
     */
    public String value = "string";
    
    /**
     * Text above method {@}
     * @deprecated please use new methods
     * @param length
     * @throws RuntimeException 
     * @return 
     */
    public static String method1(int length) throws RuntimeException {       
        return "";
    }
    
    /**
     * Пример ссылок в тексте {@link Other#otherMethod1()} и отдельным блоком:
     * @see Other#otherMethod1() 
     * @see "chapter 2"
     */
    public static void method2() { }
}

class Other{
    /**
     * some info for other method
     */
    void otherMethod1() { }
}

/*
для создания пакетного java doc есть два варианта создания файл:
1) package.html
2) package-info.java

В терминале: 
javadoc -help
MyProject/src> javadoc -d папкаВпапкеsrc/куда/сложить/файлы net.kiranatos.e16javadoc
(img підтянуло нормально, але злетіло кодування русс.букв, не працюють ссилки по @see)

В NetBeans:
Tools > Analyze Javadoc
Run > Generate Javadoc
док-файли склало в папку MyProject\dist
(img не підтянуло, проте з кодування русс.букв все норм, не працюють ссилки по @see)
Параметри настройки джавадок можно задавать в Properties проекта > Documentation

IDEA:
Tools > Generate JavaDoc // зручне вікно з настройками. Можна задать будь-яку папку, куда можна зложить док-файли
(img підтянуло нормально, але злетіло кодування русс.букв, працюють ссилки по @see)
*/