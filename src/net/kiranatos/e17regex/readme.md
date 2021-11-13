Проработать:
https://www.exlab.net/tools/sheets/regexp.html

***************************************************************
java.util dont suppport (?ifthen|else) RegEx syntax

(?ifthen|else)
(?ifthen)
(?(?=regex)then|else)
(?(?=condition)(then1|then2|then3)|(else1|else2|else3))

need jRegex or RegExodus libraries for it
        
"bd".matches("(a)?b(?(1)c|d)"));
Pattern p = new Pattern("(a)?b(?(1)c|d)");  // ?(1)c|d это ?ifthen|else
Matcher m=p.matcher("bd");
Matcher m=p.matcher("abc");
Matcher m=p.matcher("bc");
Matcher m=p.matcher("abd");

Урок 263: RegExp 18: If Then Else
https://www.youtube.com/watch?v=poUahvbg8Hw&list=PL786bPIlqEjQsE_WAcKpkZ-Q4T-A8j1D2&index=19
https://github.com/hacker85/JavaLessons/blob/master/src/regexp/IfThenElseLesson.java