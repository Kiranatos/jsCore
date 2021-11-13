package net.kiranatos.e01core;

public class Inheritance_002 {
    public static void main(String[] args) {        
        System.out.println("Initialization of Constructors:");
        A2 test = new B2();
        System.out.println("\nЗакрытый final-метод не переопределяется открытым методом:");
        test.methodOne();
        new B2(1).methodOne();
        
        System.out.println("#########################");
        A21 _a21 = new B21(20);
       
        
    }   
    
    static class A2 {
        A2()                        { System.out.println("class A2: without argument"); }
        A2(String args)             { System.out.println("class A2: with String argument"); }
        private final void methodOne()    { System.out.println("A2: private method"); }
    }
    
    static class B2 extends A2 {
        B2(){
            //super(""); нельзя совместно использовать вызовы super() и this(), поскольку такой вызов должен быть всегда первым оператором
            //super();
            this("");
            System.out.println("class B2: without argument");
        }
        
        B2(String args) {
            super("");
            System.out.println("class B2: with String argument");
        }
        
        B2(int a) { System.out.println("class B2: with int argument"); }        
        public void methodOne()    { System.out.println("B2: public method"); }
    }   
}

class A21{
    private A21(){ System.out.println("class A21 private"); }
    public A21(String str){ System.out.println("class A21"); }
}

class B21 extends A21 {    
    public B21(int a) {        
        super("");
        System.out.println("class B21");
    }    
}