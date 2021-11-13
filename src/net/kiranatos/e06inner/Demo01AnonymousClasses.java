package net.kiranatos.e06inner;

import java.util.ArrayList;
import java.util.List;

public class Demo01AnonymousClasses {    
    public static void main(String[] args) {
        System.out.println("Anonymous Class never can be abstract");
        anonym1.method2();
        //anonym1.method3(); // вызвать снаружи невозможно
        
        String anonym2 = new R1(){
            @Override
                void method2() { method3(); }
                public String method3() { return "string"; }
        }.method3();
        
        R1 anonym3 = new R1(){
        @Override
        void method2() { method3(); }
        public void method3() { } };        
        //anonym3.method3(); // вызвать снаружи невозможно
    }
    
    static R1 anonym1 = new R1(){
        @Override
        void method2() { method3(); }
        public void method3() {}
    };
}

class R1 {
    void method1() {}
    void method2() {}
}

// Example  #2
class Car01 {
    public List<Car01> createMachine(int count){
        List<Car01> result = new ArrayList<Car01>();
        for (int i = 0; i < count; i++) {
            final int number = i;
            result.add(new Car01(){ 
                // могут использовать локальные переменные на чтение
                int policeNum = number; 
            });
        }
        return result;
    }
}

// Как это видит компилятор:
class Car02 {
    public List<Car02> createMachine(int count){
        List<Car02> result = new ArrayList<Car02>();
        for (int i = 0; i < count; i++) {
            final int number = i;
            result.add(new Anonymous(number));
        }
        return result;
    }
}
class Anonymous extends Car02 {
    final int number;
    public Anonymous(int number) { this.number = number; }    
}