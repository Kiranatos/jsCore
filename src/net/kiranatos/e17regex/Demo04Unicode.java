package net.kiranatos.e17regex;

import java.util.regex.Pattern;
import net.kiranatos.OzoHelper;

public class Demo04Unicode {
    private static Demo01 demo = new Demo01();
    private static String randTextLow = OzoHelper.getRandomText(15, OzoHelper.RandomText.LATIN_LOWERCASE, OzoHelper.RandomText.SPACE, OzoHelper.RandomText.NUMBERS);
    private static String randTextUpp = OzoHelper.getRandomText(5, OzoHelper.RandomText.LATIN_UPPERCASE, OzoHelper.RandomText.LATIN_LOWERCASE);
    
    public static void main(String[] args) {
        System.out.println("\u0061 + \u0300 = \u0061\u0300"); // one symbol composed of two
        System.out.println("\u00E0"); // one symbol
        OzoHelper.lineSeparator(60, 1, 'X');        
        
        String str = "à à \u0061 \u0300 \u0061\u0300 \u00E0";
        demo.print("Example #%d", str, "\u00E0");
        demo.print("Example #%d", str, "\\u00E0");
        demo.print("Example #%d", str, "\\x{00E0}");
        demo.print("Example #%d", str, "à");
        demo.print("Example #%d", str, "à", Pattern.CANON_EQ); // CANON_EQ - Позволяет искать похожие символы, даже если их кодировки отличаются
        
        /* For more information read:
        https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        Goyvaerts Jan - Regular Expressions (2007-197) 13. Unicode Regular Expressions (page 33)  */
        demo.print("Example #%d", "Bla5 bla_bla", "\\p{Alpha}"); // = [\p{Lower}\p{Upper}]
        demo.print("Example #%d", randTextLow, "\\p{Digit}");
        
        demo.print("Example #%d", randTextUpp, "\\p{Upper}"); // = \\p{Lu} = \\p{IsUppercase}
        demo.print("Example #%d", randTextUpp, "\\p{Ll}"); // = \\p{IsLowercase} = \\p{Lower}
        
                        
    }
}

/* \p{_}   
L - unicode letter
M - ascii letter and symbol of new line  
Z - any space
S - symbol(dollar etc.)   
N-number    
P-punctuation     
C-other, invisible character, unused code point

Pattern p2 = Pattern.compile("\\p{Z}");
Pattern p2 = Pattern.compile("\\pZ");
Pattern p2 = Pattern.compile("(\\p{L}\\p{M})*");
*/