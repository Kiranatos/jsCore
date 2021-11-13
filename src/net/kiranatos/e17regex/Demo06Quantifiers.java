package net.kiranatos.e17regex;

import net.kiranatos.OzoHelper;

public class Demo06Quantifiers {
    private static Demo01 demo = new Demo01();
    public static void main(String[] args) {
        /*
        Reluctant(нежадные) quantifiers - ищут необходимое условие и останавливаются на нём.
        Possessive(притяжательные) quantifiers - подобно жадным идут до конца, но не помнят найденные условия и не возвращаются
        Greedy(жадные) quantifiers - находят условия, но продолжают идти до конца, а потом возвращаются к найденным условиям, благодаря наличию backtracking
        */
        
        demo.print("Example #%d Reluctant", "\"abc\"x",     "\".*?\"");
        demo.print("Example #%d Possessive", "\"abc\"x",    "\".*+\"");
        demo.print("Example #%d Greedy", "\"abc\"x",        "\".*\"");
        
        OzoHelper.lineSeparator(60, 3, '#');
        String htmlStr = "begin <K>out one<K>in one</K>out two</K> text <K>separ</K> end";
        demo.print("Example #%d Reluctant", htmlStr, "<(K)>.*?</\\1>");
        demo.print("Example #%d Possessive", htmlStr, "<(K)>.*+</\\1>");
        demo.print("Example #%d Greedy", htmlStr, "<(K)>.*</\\1>");        
        
        demo.print("Example #%d Reluctant", "Fx", "[A-Z]*?x");
        demo.print("Example #%d Possessive", "Fx", "[A-Z]*+x");
        demo.print("Example #%d Greedy", "Fx", "[A-Z]*x");
        
        demo.print("Example #%d Reluctant", "FX", "[A-Z]*?X");
        demo.print("Example #%d Possessive", "FX", "[A-Z]*+X");
        demo.print("Example #%d Greedy", "FX", "[A-Z]*X");
                
        demo.print("Example #%d Reluctant", "yyxxxyxx", ".*?xx");
        demo.print("Example #%d Possessive", "yyxxxyxx", ".*+xx");
        demo.print("Example #%d Greedy", "yyxxxyxx", ".*xx");
    }
}
