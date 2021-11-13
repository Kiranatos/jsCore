package net.kiranatos.e17regex;

import java.util.regex.Pattern;

public class Demo05ModesAndComments {
    private static Demo01 demo = new Demo01();
    public static void main(String[] args) {
        demo.print("Example #%d", "first line\nsecond line", "ne$");
        demo.print("Example #%d", "first line\nsecond line", "ne$", Pattern.MULTILINE);
        demo.print("Example #%d", "AAA", "a{3}", Pattern.CASE_INSENSITIVE);
        
        //it is possible to turn on modes inside RegEx:
        demo.print("Example #%d Case Insensitive", "AAA", "(?i)a{3}");
        demo.print("Example #%d Single Line", "first line\nsecond line", "(?s)ne$");
        demo.print("Example #%d Multi Line", "first line\nsecond line", "(?m)ne$");        
        demo.print("Example #%d Comment and Case Insensitive", "AAA", "(?ix)a{3}#this is comment");
        demo.print("Example #%d mode on/off", "AB aB ab Ab", "(?i)a(?-i)b"); // для a включен(?i), для b выключен(?-i)
        demo.print("Example #%d mode on/off selectively", "ABc aBC abc AbC", "(?i)a(?-i:B)c"); // для a и c включен(?i), для b выключен(?-i)
        
        // COMMENTS:
        demo.print("Example #%d 1st variant of Comments", "A", "A#this is comment", Pattern.COMMENTS);
        /* 2nd variant of Comments: Pattern.compile("(?#year)(19|20)\d\d[-/.](?#month)(0[1-9]|1[012])", Pattern.COMMENTS);
        #comment - только в конце регекса
        (?#month) - в любом месте, но java.util не поддерживает, разве что включить сторонню библиотеку типо jRegex  */
        demo.print("Example #%d 3rd variant of Comments", "AAA", "(?x)A$#this is comment");
        // также (?x) включает whitespaces в regex:
        demo.print("Example #%d Whitespaces in RegEx:", "abc", "(?x)a b c");        
        demo.print("Example #%d Whitespaces in RegEx:", "atomic", "(?x) ( ?> ato mic )");
        demo.print("Example #%d :", "4\\ d", "(?x)\\ d"); // так не работает
    }
}
