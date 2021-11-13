package net.kiranatos.e17regex;

public class Demo08Assertions {
    private static Demo02Groups demo = new Demo02Groups();    
    public static void main(String[] args) {      
        // ?= Вперед смотрящее
        demo.print("Example #%d Lookahead", "cat", "c(?=a)"); // if (nextAfterC == a) return c
        demo.print("Example #%d Lookahead", "cat", "(?=a)t"); // dont work this way
        demo.print("Example #%d Lookahead", "cat", "(?=a)a"); // if (nextAfterC == a) return a
        demo.print("Example #%d Lookahead", "cat", "c(?=b)at"); // if (nextAfterC != b) return null
        demo.print("Example #%d Lookahead", "cat", "c(?=a)at"); // if (nextAfterC == a) return cat
        demo.print("Example #%d Lookahead", "cat", "c(?=(a))\\1t"); // добавить "()" чтобы можно было ссылаться на группу
        demo.print("Example #%d Lookahead", "23x123", "(?=(\\d+))\\w+\\1"); //
        
        // ?! Отрицательное вперед смотрящее
        demo.print("Example #%d Negative Lookahead", "cat", "c(?!b)at"); // if (nextAfterC != b) return cat
        demo.print("Example #%d Negative Lookahead", "cat", "c(?!a)at"); // if (nextAfterC == a) return null
        demo.print("Example #%d Negative Lookahead", "cat", "(?!c)at"); // dont work this way
        
        // ?<= Назад смотрящее
        demo.print("Example #%d Lookbehind", "dog", "d(?<=o)"); // dont work this way
        demo.print("Example #%d Lookbehind", "dog", "(?<=o)g"); // if (beforeG == o) return g
        demo.print("Example #%d Lookbehind", "dog", "d(?<=d)og"); // if (beforeO == d) return dog
        demo.print("Example #%d Lookbehind", "dog", "d(?<=b)og"); // if (beforeO != b) return null
        
        // ?<! Отрицательное назад смотрящее (или ?!= - но оно не работает почему-то)
        demo.print("Example #%d Negative Lookbehind", "dog", "(?<!t)g"); // if (beforeG != t) return g        
        demo.print("Example #%d Negative Lookbehind", "dog", "(?<!o)g"); // if (beforeG == o) return null
        demo.print("Example #%d Negative Lookbehind", "dog", "o(?<!g)"); // dont work this way
        demo.print("Example #%d Negative Lookbehind", "dog", "do(?<!g)g"); // if (beforeG != g) return dog
        
        // Пример из видео, сомневаюсь в его корректности и правельности:
        demo.print("Example #%d John's", "John's", "\\b\\w+\\b");       //John s
        demo.print("Example #%d John's", "John's", "\\b\\w+[^s]\\b");   //John'
        demo.print("Example #%d John's", "John's", "\\b\\w*[^s\\W]\\b");//John
        demo.print("Example #%d John's", "John's", "\\b\\w+(?<!s)\\b"); //John
        demo.print("Example #%d John's", "Johns", "\\b\\w+(?<!s)s\\b"); //Johns
        
        // Task: find word with 6 letters and "cat" inside.        
        demo.print("Example #%d cat task", "wecate wedoge iamcat", "cat\\w{3}|\\wcat\\w{2}|\\w{2}cat\\w|\\w{3}cat");
        /* задачу можно разбить на 2 части:
        b\w{6}\b - слово из 6-ти букв и
        \b\w*cat\w*\b - слово cat внутри */
        demo.print("Example #%d cat task", "wecate wedoge iamcat", "(?=\\b\\w{6}\\b)\\b\\w*cat\\w*\\b"); // сначала проверяется условие есть ли 6 букв, потом наличие cat внутри
        demo.print("Example #%d cat task", "wecate wedoge iamcat", "(?=\\b\\w{6}\\b)\\w*cat\\w*"); // упрощаем
        demo.print("Example #%d cat task", "wecate wedoge iamcat", "\\b(?=\\w{6}\\b)\\w*{0,3}cat\\w*"); // ещё вариант

        // Task: find word with 6-12 letters and "cat" or "dog" or "mouse" inside.        
        demo.print("Example #%d cat task", "wecate wedoge iamcat wearemouses", "\\b(?=\\w{6,12}\\b)\\w{0,9}(cat|dog|mouse)\\w*");        
    }
}