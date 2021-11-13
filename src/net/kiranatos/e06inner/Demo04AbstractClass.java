package net.kiranatos.e06inner;

public abstract class Demo04AbstractClass {    
    public static void main(String[] args) {
        System.out.println("I am Abstract Class");
    }
    
    public void nonStaticMethod_1() {
        nonStaticMethod_2();
        int val  = nonStaticAbstractMethod(777);
    }
    
    public void nonStaticMethod_2() {}    
    public abstract int nonStaticAbstractMethod(int someValue);
}
