package net.kiranatos.e04lambda;

import net.kiranatos.OzoHelper;

public class Demo01Lambda {
    private static int a = 10;    
    
    @FunctionalInterface
    interface Operationable             { int calculate(int x, int y); }
    interface ClassOperationable        { ClassA getMyClass(String str); }
    interface GenericOperationable<T>   { T calculate(T x, T y); }
    
    public static void main(String[] args) {                        
        OzoHelper.printMe(null,
                "::: ::: Примеры лямбда-выражений ::: :::\n",
                "I) параметров->тело выражения",
                "    [ x->a+b ]    ",
                "Пример №1:",
                "\t Лямбда-выражение реализует метод функциональныого интерфейса.",
                "\t Нереализованный метод в интерфейсе должен быть один!");
        Operationable op_1 = (x,y)-> x+y+a;
        System.out.println(op_1.calculate(20, 25));
        System.out.println(op_1.calculate(30, 35));
        System.out.println(op_1.calculate(45, 45));
        
        System.out.println("\nПример №2(Лямбда - тот же внутренний класс):");        
        Operationable op_2 = new Operationable(){
            public int calculate(int x, int y){             
                return x + y;
            }
        };
        System.out.println(op_2.calculate(20, 25));
        System.out.println(op_2.calculate(30, 35));
        System.out.println(op_2.calculate(45, 45));
        
        OzoHelper.printMe(null,
                "Пример №3:",              
                "\t- Lambda может использовать или изменять переменные на уровне класса",
                "\t- Lambda может передавать в качестве параметров в методы");
        op_1 = (int x, int y) -> {
            a = 500;
            if (a==10) return x*y*a;
            else return a-x-y;
        };
        System.out.println(op_1.calculate(20, 25));
        System.out.println(op_1.calculate(30, 35));
        System.out.println(op_1.calculate(45, 45));
        System.out.println(sum(13, op_1));
        System.out.println(sum(14, (h,k)-> h*k+a));      
        
        Operationable func = getLambda(101);
        int a = func.calculate(6, 5);
        System.out.println(a);        
        System.out.println(getLambda(101).calculate(33, 66));        
                
        OzoHelper.printMe(null,
                "Пример №4 Использование дженериков:",
                "\t- Функциональный интерфейс может быть обобщенным,",
                "\t однако в лямбда-выражении использование обобщений не допускается.",
                "\t- Объект надо типизировать при объявлении лямбда-выражения.");
        GenericOperationable<Integer> op_3 = (x, y)-> x + y;
        GenericOperationable<String> op_4 = (x, y) -> x + y;        
        
        OzoHelper.printMe(null,                
                "II) имя_класса::имя_статического_метода",
                "    [ NameClass::nameStaticMethod ]    ",
                "Пример №6:",
                "\t- реализовывать интерфейс для класса НЕ НУЖНО",
                "\t- методов у класса может быть много",
                "\t главное чтобы СОВПАДАЛ контракт метода");
        System.out.println(sum(15, ClassA::methodA1));
        System.out.println(sum(16, ClassA::methodA2));
        System.out.println(sum(17, Demo01Lambda::methodMainProgram));
        
        OzoHelper.printMe(null,                
                "III) объект_класса::имя_метода",
                "    [ objectClass::nameNONStaticMethod ]    ",
                "Пример №:7");
        ClassA nnn = new ClassA("");
        System.out.println(sum(18, nnn::methodA3));
        System.out.println(sum(19, new ClassA("")::methodA3));
        System.out.println(sum(19, new Demo01Lambda()::methodMainProgram2));
        System.out.println("Также возможен вариант: this::nonStaticMethod; в не статических методах");
        
        OzoHelper.printMe(null,
                "IV) название_класса::new",
                "    [ NameInterface h = NameClass::NameClass ]    ",
                "    [ NameClass n = h.interfaceMethod() ]    ",
                "Пример №:8",
                "\t- Сигнатура конструктора должна совпадать с сигнатурой методы функциональных интерфейсов(другие конструкторы также могут присутствовать)",
                "\t- Метод функционального интерфейса должен возвращать объект класса");
        ClassOperationable classA = ClassA::new;
        ClassA jojo_01 = classA.getMyClass("");
        System.out.println("Класс создан: " + jojo_01.toString());        
    } // End of public 'static void main(String[] args)'
    
    /* Обратить внимание, не забывать! вызывается метод интерфейса! 
    Ключевой момент для понимания где связь параметров методов !!!
    x, y - подставляются сдесь!!!    */
    private static int sum(int owo, Operationable op){
        return op.calculate(owo, a+2) + 1000; 
    }
    
    private static Operationable getLambda(int number){
        if (number > 100) return (x, y) -> x + y;
        else return (x, y) -> x * y;        
    }
    
    private static int methodMainProgram(int x, int y) { return 58; }
    private int methodMainProgram2(int x, int y) { return 158; }
    
    private static class ClassA {
        public ClassA(String str) {        }        
        private static int methodA1(int x, int y) { return 5; }
        private static int methodA2(int x, int y) { return 6; }
        private int methodA3(int x, int y) { return 6; }
    }
}

 
                