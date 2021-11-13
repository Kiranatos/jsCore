package net.kiranatos.e17regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kiranatos.OzoHelper;

public class Demo03GroupReplace {
    private static int index = 1;
    
    public static void main(String[] args) {
        // Groips can be used in String.replace via "$"       
        String result = "EditPad Lite : EditPad Pro".replaceAll("EditPad (Lite|Pro)", "$1 version"); //1. //Lite version : Pro version        
        result = "EditPad Lite".replaceAll("EditPad (Lite|Pro)", "$0 version"); //whole regex
        
        primerIzInterneta();
        
        replace("Example #%d. (\\d) - group №1 (\\D+) - group №2 (\\d) - group №3",
                "6 example input 4","(\\d)(\\D+)(\\d)", "number $3$1");
        replace("Example #%d.",
                "666 demonstration 444","(\\d{3})(\\D+)(\\d{3})", "(group:$0) \t(group:$1) \t(group:$2) \t(group:$3)");
        replace("Example #%d. $0 is all RegEx",
                "EditPad Lite : EditPad Pro","EditPad (Lite|Pro)", "$0 version");
        replace("Example #%d.",
                "111abc222abc333","(a)(b)(c)", "z$3y$2x$1");
        replace("Example #%d. ",
                "EditPad Lite : EditPad Pro","EditPad (Lite|Pro)", "\\F1 version");
        replace("Example #%d. ",
                "the the", "\\b(\\w+)\\s+\\1\\b", "one word: $1");
        replace("Example #%d. ",
                "abch", "(?<nameOfGroup>h)", "defg${nameOfGroup}ijkl");
        replace("Example #%d. Replace using numbers of groups",
                "abcd", "(a)(?<x>b)(c)(?<y>d)", "$4$3$2$1");
        replace("Example #%d. Replace using numbers and names of groups",
                "abcd", "(a)(?<x>b)(c)(?<y>d)", "${y}$3${x}$1");
        
        

    }
    
    private static void replace(String message, String str, String regex, String replacement) {      
        Pattern pattern = Pattern.compile(regex);
        System.out.printf(message, index++);
        System.out.println("\n[input]: " + str);
        System.out.println("[pattern]: " + pattern.pattern());
        System.out.println("[replacement regex]: " + replacement);        
        Matcher matcher = pattern.matcher(str);
        int count = matcher.groupCount();
        System.out.println("[groups]: " + count);
        while (matcher.find()) {
            for (int i = 0; i <= count; i++) {
                // .group() тоже что и .group(0) - всё выражение целиком
                // .group(N) - группа № N
                System.out.printf("[group %d]: %s%n", i, matcher.group(i));
            }
            System.out.println("[result]: " + matcher.replaceAll(replacement));
        }   
        matcher.reset();
        OzoHelper.lineSeparator(60, 1, '*');
    }
    
    /*************************************************************************/
    private static void primerIzInterneta(){
        // replace with "%" what was matched by group 1 
        // input: aaa123ccc
        // output: %123ccc
        System.out.println(replaceGroup("([a-z]+)([0-9]+)([a-z]+)", "aaa123ccc", 1, "%"));

        // replace with "!!!" what was matched the 4th time by the group 2
        // input: a1b2c3d4e5
        // output: a1b2c3d!!!e5
        System.out.println(replaceGroup("([a-z])(\\d)", "a1b2c3d4e5", 2, 4, "!!!"));
    }
    
    public static String replaceGroup(String regex, String source,
            int groupToReplace, String replacement) {
        return replaceGroup(regex, source, groupToReplace, 1, replacement);
    }

    public static String replaceGroup(String regex, String source,
            int groupToReplace, int groupOccurrence, String replacement) {
        Matcher m = Pattern.compile(regex).matcher(source);
        for (int i = 0; i < groupOccurrence; i++) {
            if (!m.find()) {
                return source; // pattern not met, may also throw an exception here
            }
        }
        return new StringBuilder(source)
                .replace(m.start(groupToReplace), m.end(groupToReplace), replacement)
                .toString();
    }
}

/* 
backreferences
*/