package net.kiranatos.e01core;

/* Конструктори не переопределяются и не наследуются, а только перегружаются.
 */
public class Inheritance_001 {
    public static void main(String[] args) {
        System.out.println("I. Example of non-static methods:");
        System.out.println("\t" + new A1().methodNonStatic_1().getClass().getTypeName());
        System.out.println("\t" + new B1().methodNonStatic_1().getClass().getTypeName());
        System.out.println("\t" + new C1().methodNonStatic_1().getClass().getTypeName());
        System.out.println("\t" + new D1().methodNonStatic_1().getClass().getTypeName());
        A1 test1 = new C1();
        System.out.println("При вызове метода будет использоваться механизм позднего связывания,\n" +
                "поэтому будет вызван метод не на основе типа ссылочной переменной,\n" +
                "а на конкретном объекте, т.е будет вызван метод класса C");
        System.out.println("\t" + test1.methodNonStatic_1().getClass().getTypeName());
        
        // Перегрузка методов:
        System.out.println("\n - " + new C1().methodNonStatic_1(10));
        C1 test2 = new D1();
        System.out.println("\n - " + test2.methodNonStatic_1(10) + " (check code in class D1)");
        
        System.out.println("#############################################################");
        
        System.out.println("\nII. Example of static methods via reference variable:");
        test1 = new B1();
        test1.methodStatic_2();
        new B1().methodStatic_2();
        System.out.println("Компилятор не помешает переопределить статические методы в подклассах, \n"
                + "однако переопределение не сработает, т.к. при вызове используется\n"
                + "механизм раннего связывания, поэтому будет вызван метод на основе \n"
                + "типа ссылочной переменной. Аннотацию @Override не применяется. \n"
                + "Ст.методы можно перегружать в подклассах, их доступность "
                + "зависит от типа ссылки и атрибута доступа. \n"
                + "При динамическом связывании версия вызываемого метода определяется \n"
                + "на этапе выполнения.");
        
        System.out.println("\nIII. Example of child methods:");
        //test.methodOnlyInChild(); //нет доступа к методу т.к. ссылочная переменная класса А
        new B1().methodOnlyInChild();
        
        System.out.println("\nIV. Example of non-static variables: \n" +
                "Поля не являются полиморфными, поэтому будет обращение к полю на основе типа ссылочной переменной");
        System.out.println("class A:    non-st.val = " + new A1().field_1);
        System.out.println("class B:    non-st.val = " + new B1()._value_1);
        System.out.println("class C:    non-st.val = " + new C1()._value_1);
        System.out.println("class A(B): non-st.val = " + test1.field_1);
        
        System.out.println("\nV. Example of static variables:");
        System.out.println("class A:    st.val = " + new A1().field_2);
        System.out.println("class B:    st.val = " + new B1()._value_2);
        System.out.println("class C:    st.val = " + new C1()._value_2);
        System.out.println("class A(B): st.val = " + test1.field_2);
    }    
}

class A1 {
    public String field_1 = "field_1 non-st.";
    public static String field_2 = "filed_2 st.";
    public Number methodNonStatic_1() {
        System.out.println("class A, non-st.method, " + field_1 + ", " + field_2);
        return Short.valueOf("50");
    }
    public static void methodStatic_2() {
        System.out.println("class A, static method, " + field_2);
    }
}

class B1 extends A1 {
    public String _value_1 = "B1 non-st.";
    public static String _value_2 = "B1 st.";
    public Integer methodNonStatic_1() {
        System.out.println("class B, non-st.method, " + _value_1 + ", " + _value_2);
        return Integer.valueOf("560");
    }
    public static void methodStatic_2() {
        System.out.println("class B, static method, " + _value_2);
    }
    public void methodOnlyInChild() {
        System.out.println("class B, child method which is absent in parent class");
    }
}

class C1 extends A1 {
    public String _value_1 = "C1 non-st.";
    public static String _value_2 = "C1 st.";
    public Float methodNonStatic_1() {
        System.out.println("class C, non-st.method, " + _value_1 + ", " + _value_2);
        System.out.println("class C, non-st.method, super.val = " + super.field_1 + ", super.val = " + super.field_2);
        return Float.valueOf("220");
    }
    public String methodNonStatic_1(Object r5) {
        return "Класс С1: Возможно сделать перегрузку метода с другим возвращаемым типом ";
    }
}

class D1 extends C1 {
    public Float methodNonStatic_1() {
        System.out.println("class D1, non-st.method, " + _value_1 + ", " + _value_2);
        System.out.println("class D1, non-st.method, super.val = " + super._value_1 + ", super.val = " + super._value_2);
        return Float.valueOf("220");
    }
    
    public String methodNonStatic_1(Integer r5) {
        return "Класс D1: в данном случае метод перегружается, а не переопределяется, "
                + "так как изменяется тип аргумента, поэтому вызывается метод класса С1";
    }
}



/*
public class Quest{
    public void method(Number obj) {
        System.out.print ("1");
    }
    public void method(Character obj) {
        System.out.print ("2");
    }
    private static void method(Integer obj) {
        System.out.print ("3");
    }
    public void method(int i) {
        System.out.print ("4");
    }
    public void method(double d) {
        System.out.print ("5");
    }
    public static void main(String [] args) {
        Quest quest = new Quest();
        Number n = 67;
        Integer i = 78;
        quest.method(n);
        quest.method(i);
    }
}


public class Main {
    public static void main(String[] args) {
        A a = new B();
        a.method(42);
    }
}
class A {
    public void method(Object obj) {
        System.out.println("A");
    }
}
class B extends A {
    public void method(Integer obj) {
        System.out.println("B");
    }
}
*/