package net.kiranatos.e17regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kiranatos.OzoHelper;

/* Documentation:
https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html */

public class Demo01 {    
    private static int index = 1;    
    public static void main(String[] args) {
        Demo01 dse = new Demo01();
        
        dse.print("Example #%d", "Алла", "А.+а");
        dse.print("Example #%d", "Егор Алла Александр", "А.+а");
        dse.print("Example #%d", "aaa a aaa a aaa", "a{3}$");
        dse.print("Example #%d", "gruy grey griy gray", "gr[ea]y");
        dse.print("Example #%d", "queue. Iraq is a country qu qk Iraq", "q[^u]");
        dse.print("Example #%d", "abcde&fkxz", "[a-z&&[^c-k]]");
        dse.print("Example #%d", "Testing <B><I>bold italic</I></B> text", "<([A-Z][A-Z0-9]*)[^>]*>.*?<\\/\\1>");
        dse.print("Example #%d", "mail@i.ua", "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");        
        dse.print("Example #%d", "expression (1+1)^2=4", "\\Q(1+1)^2\\E");
        dse.print("Example #%d", "f[a-d]t", "[\\Q[-]\\E]");
        dse.print("Example #%d", "abc", "[a-z]");
        dse.print("Example #%d", "screen in bracets ][ \\ ^ -", "[a\\-z]");
        dse.print("Example #%d", "screen in bracets ][ \\ ^ -", "[-]");
        dse.print("Example #%d", "screen in bracets ][ \\ ^ -", "[\\[\\]\\^\\-\\\\]");        
        dse.print("Example #%d", "dont screen in bracets . $ { * ( + ) | ? < > ^", "[${*()+|?>]");
        dse.print("Example #%d", "D 02/12/03 D 02512703 D grey 1.2.3 01.02.03 01 02 0333", "\\d\\d.\\d\\d.\\d\\d");
        dse.print("Example #%d", "D 02/12/03 D 02512703 D grey 1.2.3 01.02.03 01 02 0333", "\\d\\d[- /.]\\d\\d[- /.]\\d\\d");
        dse.print("Example #%d", "Houston, we have a problem with \"string one\" and \"string two\".", "\".*\"");
        dse.print("Example #%d", "Houston, we have a problem with \"string one\" and \"string two\".", "\".*?\"");
        dse.print("Example #%d", "Houston, we have a problem with \"string one\" and \"string two\".", "\"[^\"\r\n]*\""); //. - [^\n] unix - [^\r\n] windows
        dse.print("Example #%d", "This island is beautiful", "\\bis\\b");
        dse.print("Example #%d", "1985", "^\\d+$");        
        dse.print("Example #%d", "color colour", "colou?r");
        dse.print("Example #%d", "November Nov", "Nov(ember)?");  // Greedy жадный
        dse.print("Example #%d", "November Nov", "Nov(ember)??"); // Reluctant нежадный
        dse.print("Example #%d", "February 23rd, February 23, Feb 23rd, Feb 23", "Feb(ruary)? 23(rd)?");
        dse.print("Example #%d", "This is a <EM>first</EM> test", "<.+>");  // Greedy
        dse.print("Example #%d", "This is a <EM>first</EM> test", "<.+?>"); // Reluctant
        dse.print("Example #%d", "This is a <EM>first<r>2nd</r>3rd</EM> test", "<[^>]+>");
        dse.print("Example #%d", "*\\d+*******\\d+*", "\\Q*\\d+*\\E+"); // Не ясно, почему оно захватывает повторяющийся символ?
        dse.print("Example #%d", "word worddd wor", "\\Qword\\E+");
        dse.print("Example #%d", "word worddd wor", "\\Qword wor\\E+");
        dse.print("Example #%d", "word worddd wor", "\\Qword word\\E+");
        dse.print("Example #%d", "*\\d+**\\d+*", "\\Q*\\d+\\E*+");
        dse.print("Example #%d", "*\\d+**\\d+*", "(?:\\Q*\\d+*\\E)+");
        dse.print("Example #%d", "abaaaba", "ab");
        dse.print("Example #%d", "abababa", "aba");
        dse.print("Example #%d", "a12c3e456f", "\\d");
        dse.print("Example #%d", "a12c3e456f", "\\d+");
        dse.print("Example #%d", "a 1 56 _Z", "\\w+");
        dse.print("Example #%d", "adbc", "[a-c]");
        dse.print("Example #%d", "proj3.txt,proj1sched.pdf,proj1,proj2,proj1.java", "proj1([^,])*");
        dse.print("Example #%d", "1234 567-1234-567", "\\d+([-\\s])?\\d+");
        dse.print("Example #%d", "ac abc a c", "a.c");
    }
    
    void print(String message, String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        print(message, str, regex, pattern);
    }
    
    void print(String message, String str, String regex, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        print(message, str, regex, pattern);
    }
    
    private void print(String message, String str, String regex, Pattern pattern) {        
        System.out.printf(message, index++);
        System.out.println("\n[input]: " + str);
        System.out.println("[pattern]: " + pattern.pattern());
        System.out.println("[matches]: " + Pattern.matches(regex, str)); // true: str matches regex wholly, no extra     
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) { //аналог метода .next у итератора
            // matcher.group(); // получить совпадение
            System.out.println("[position]: " + matcher.start() + ", [result]: " + matcher.group());            
        }   
        matcher.reset();
        OzoHelper.lineSeparator(60, 1, '*');
    }
}
