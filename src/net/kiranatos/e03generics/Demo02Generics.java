package net.kiranatos.e03generics;

import java.util.Date;
import java.util.Random;
public class Demo02Generics {
    public static void main(String[] args) {
        System.out.println("Пример дженериков в классах, class G1:");
        //Возможные варианты объявления:
        G1 g1_1 = new G1(new Integer(25), new StringBuilder("FIRE!"), new Double(5.0)); // №1
        System.out.println(g1_1);		
        G1<Date,Random,Integer,P1> g1_2 = new G1<Date,Random,Integer,P1>(new Date(), new Random(), new Integer(25)); // №2
        System.out.println(g1_2);
        G1<Date,Random,Long,P2> g1_3 = new G1<>(new Date(), new Random(), new Long(12)); // №3
        System.out.println(g1_3);
        
        System.out.println("Пример дженериков в методах:");
        printSt(new G3<Integer>(12));
        printSt(new G3(new String("test")));
        printGt(new Integer(12));
        printGt(new String("test"));
        Demo02Generics.<Integer>printGt("text");
        
        System.out.println("Тест супера, Ошибка в супере? О_о :");        
        printSuper( new G4(new P5()) );
        /* <T extends Person> - Т може бути классом Персон або його нащадком
        <? super T> - підставляються класи Т і його нащадки, а також батьки. 
        Object підходить, а от String уже ні.        */
        
        isIn(2, 10); //Происходит автоупаковка в объект Integer        
        /*               TO BE CONTINUED ...                   */
    }
    
    //Generic Methods:
    public static void printSt(G3<?> g2){ System.out.println("printSt[" + g2 + "]"); }
    public static <T> void printGt(Object ob){  
        T t = (T)ob;
        System.out.println("printGt[" + t.getClass().getSimpleName().toString() + "]");
    }
    public static void printSuper(G4<? super P2> g4) { System.out.println("printSuper[" + g4 + "]"); }
    public static <T extends Comparable<T>, V extends T> void isIn(T t, V v) {  }
}
/******************************** G1 ***********************************/
class G1<A1, A2, A3 extends Number, A4> {
    A1 a1; A2 a2; A3 a3; int index;
    G1(A1 a1, A2 a2, A3 a3) { 
        this.a1 = a1; this.a2 = a2; this.a3 = a3;
        index = a3.intValue(); //т.к. а3 наследуются от Number, то он может вызывать методы родителя
    }
    @Override
    public String toString() {		
        return "G1[" + a1.getClass().getSimpleName().toString() + " : " + 
                a2.getClass().getSimpleName().toString() + " : " +
                a3.getClass().getSimpleName().toString() + "]";
    }
}

/********************************** Z1 *********************************/
class Test1 {}
interface NewInt1 {}
interface NewInt2 {}
class Z1<A1 extends Test1 & NewInt1 & NewInt2>{  //наследует один класс и множество интерфейсов
	A1 a1;
	Z1(A1 a1) { this.a1 = a1; }
	@Override
	public String toString() { return "G2[" + a1.getClass().getSimpleName().toString() + "]"; }
}

/************************************G3*******************************/
//класс для примера работы методов-дженериков
class G3<A1>{
	A1 a1;
	G3(A1 a1) { this.a1 = a1; }
	@Override
	public String toString() { return "G3[" + a1.getClass().getSimpleName().toString() + "]"; }
}


//Для теста super-а
class G4<A1 extends P2>{
	A1 a1;
	G4(A1 a1) { this.a1 = a1; System.out.println("This is G4 constructor"); }
	@Override
	public String toString() {		
		return "G4[" + a1.getClass().getSimpleName().toString() + "]";
	}
}

/********************************************************** Тестовые классы: родитель-потомок **********415*/
class P1 {
	String p1 = "Class P1";
	P1() { System.out.println("This is P1 constructor"); }
	@Override
	public String toString() { return "It's a " + p1; }
}

class P2 extends P1 {
	String p2 = "Class P2";
	P2() { System.out.println("This is P2 constructor"); }
	@Override
	public String toString() { return "It's a " + p2; }
}

class P3 extends P2 {
	String p3 = "Class P3";
	P3() { System.out.println("This is P3 constructor"); }
	@Override
	public String toString() { return "It's a " + p3; }
}

class P4 extends P3 {
	String p4 = "Class P4";
	P4() { System.out.println("This is P4 constructor"); }
	@Override
	public String toString() { return "It's a " + p4; }
}

class P5 extends P4 {
	String p5 = "Class P5";
	P5() { System.out.println("This is P5 constructor"); }
	@Override
	public String toString() { return "It's a " + p5; }
}