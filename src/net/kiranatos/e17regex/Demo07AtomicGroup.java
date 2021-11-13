package net.kiranatos.e17regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo07AtomicGroup {
    private static Demo01 demo = new Demo01();

    public static void main(String[] args) {
        /* Atomic group (?>RegEx)
        (?>a|b|c) работает быстрее обычных, так как при выходе не помнит, что были ещё варианты
        */
        demo.print("Example #%d No Atomic", "abc",  "a(bc|b)c");//true: нашло bc и c
        demo.print("Example #%d Atomic",    "abc",  "a(?>bc|b)c");//false: нашло bc, поэтому c не искало, а при выходе уже не помнило что есть др.варианты
        
        //Остальные примеры из видео, but in my opinion они не раскрывают смысл Atomic group
        
        // Atomic group чем-то похожа на Possessive Quantifier
        demo.print("Example #%d No Atomic", "b",  "(a|b)*+b"); // X*+ = (?>X*)
        demo.print("Example #%d Atomic",    "b",  "(?>(a|b)*)b");
        demo.print("Example #%d Atomic",    "b",  "(?>a|b)*b");
        
        demo.print("Example #%d No Atomic", "abcc", "a(bc|b)c");
        demo.print("Example #%d Atomic",    "abcc", "a(?>bc|b)c");
        
        demo.print("Example #%d No Atomic", "integers", "\\b(integer|insert|in)\\b");
        demo.print("Example #%d Atomic", "integers",    "\\b(?>integer|insert|in)\\b");
    }
}
