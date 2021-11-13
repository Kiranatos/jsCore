package net.kiranatos.e17regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kiranatos.OzoHelper;

public class Demo02Groups {
    private static int index = 1;
    private static Demo02Groups dg = new Demo02Groups();
    
    public static void main(String[] args) {    
        dg.print("Example #%d", "EditPad Lite version", "EditPad (Lite|Pro)");
        dg.print("Example #%d", "Set or SetValue", "Set(Value)?");
        dg.print("Example #%d", "Set or SetValue", "Set(?:Value)?"); //?: Пассивная группа. Не хранит ссылку на эту группу.
        dg.print("Example #%d backreferece to group", "title<h>text</h>", "<(h)>.*</\\1>");
        OzoHelper.lineSeparator(60, 1, '#');
        String htmlStr = "tags <a t=\"1\">1st<a>2nd</a>3rd</a> text <a>4th</a>";
        dg.print("Example #%d", htmlStr, "<([a-z][a-z0-9]*)>.*?</\\1>");
        dg.print("Example #%d", htmlStr, "<([a-z][a-z0-9]*)>.*</\\1>");
        dg.print("Example #%d", htmlStr, "<([a-z][a-z0-9]*)[^>]*>.*</\\1>");
        dg.print("Example #%d", htmlStr, "<([a-z][a-z0-9]*)[^>]*>.*?</\\1>");
        OzoHelper.lineSeparator(60, 1, '#');
        /*группа хранит первое пришедшее в неё значение, поэтому ссылки \1 не проверяют 
        повторно на [abc], а сразу подставлюят значение [a]*/
        dg.print("Example #%d", "axaxa", "([a-c])x\\1x\\1"); // = [a-c]xaxa
        dg.print("Example #%d", "axbxc", "([a-c])x\\1x\\1"); // = [a-c]xaxa
        dg.print("Example #%d", "Testing <B><I>bold italic</I></B> text", "<([A-Z][A-Z0-9]*)[^>]*>.*?</\\1>"); // = <B>.*?</B>
        dg.print("Example #%d", "cab", "([abc]+)"); // = 1 group: (cab)
        dg.print("Example #%d", "cab", "([abc])+"); // = 3 in 1 group: (c)(a)(b)
        dg.print("Example #%d", "cab=b", "([abc]+)=\\1"); //
        dg.print("Example #%d", "cab=b", "([abc])+=\\1"); //
        dg.print("Example #%d Нельзя ссылаться на саму себя", "axaxa", "([a-c]\\1)");
        dg.print("Example #%d Group cant be inside brackets. Parenthesises is just a symbols", "abc()\\1", "[(a)b\u0031]");
        dg.print("Example #%d How to use name of group", "text1<h>text2</h>text3", "<(?<nameOfGroup>h)>.*</\\k<nameOfGroup>>"); //
        dg.print("Example #%d", htmlStr, "<(?<nameOfGroup>[a-z][a-z0-9]*)[^>]*>.*?</\\k<nameOfGroup>>"); //
    }

    void print(String message, String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        System.out.printf(message, index++);
        System.out.println("\n[input]: " + str);
        System.out.println("[pattern]: " + pattern.pattern());
        System.out.println("[matches]: " + Pattern.matches(regex, str)); // true: str matches regex wholly, no extra     
        Matcher matcher = pattern.matcher(str);
        int count = matcher.groupCount();
        System.out.println("[groups]: " + count);
        while (matcher.find()) {
            for (int i = 0; i <= count; i++) {
                System.out.printf("\t[group %d]: %s%n", i, matcher.group(i));
            }
            System.out.println("\t\t[position]: " + matcher.start() + "\n\t\t[result]: " + matcher.group());
        }
        matcher.reset();
        OzoHelper.lineSeparator(60, 1, '*');
    }
}
