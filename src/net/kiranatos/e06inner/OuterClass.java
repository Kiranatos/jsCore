package net.kiranatos.e06inner;

import java.io.Serializable;

public class OuterClass {
    private String outNonStField1;
    public int outNonStField2 = 33;
    private static String outStField10;
    public static int outStField11 = 7000;
    
    public InnerClass outNonStField3 = new InnerClass();
    // внешний класс не имеет доступа к fields внутреннего класса, но может добраться по ссылке даже к private field:
    { outNonStField3.inNonStField2 = 22; } 
            
    public void messageOut() { System.out.println("Метод внешнего класса"); }
    
    // Внутренний нестатический класс / Inner class :
    public class InnerClass{
        public String inNonStField1;
        private int inNonStField2 = outNonStField2;
        public void messageInNonSt() { System.out.println("Метод внутреннего не-статического класса" + inNonStField2); }
    }
    
    // Внутренний cтатический класс / Nested class :
    static class InnerStaticClass{
        
        public static String str = "It's static field inside Nested (Inner Static) class " + outStField11;
        private int kkk = 10 + outStField11;
    }
    
    // Внутренний interface (static by default) :
    interface InterfaceInsideClass {}
}

// Вариант 1: outer и inner классы наследуются от др.классов
class A{}
class B{}
class OuterClassSecond extends A {
    public class InnerClassSecond extends B implements Serializable{}
}

// Вариант 2: наследование от inner класса
class C extends OuterClass.InnerClass {
    public C(){
        new OuterClass().super(); // без этого super-а на outer класс, наследование на inner класс работать не будет
    }
    public C(OuterClass oc){
        oc.super();
    }
}

/******************* Как обращаться к полям ************************************************/
class OuterClassThird {
    private int id; // 1
    public class InnerClassThird{
        private int id; // 2
        public void main(int id) { // 3
            id = 3; //3
            this.id = 2; // 2
            InnerClassThird.this.id = 2; // 2
            OuterClassThird.this.id = 1; // 1         
        }
    }
}