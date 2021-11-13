package net.kiranatos.e20concurrency;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;

public class Demo11SynchronizedVariants {
    
    // метод demonstration1, то же самое что и метод demonstration2
    private synchronized void demonstration1() { }
    private void demonstration2() { synchronized(this){ } }
    
    // static метод demonstration3, то же самое что и static метод demonstration4
    private static synchronized void demonstration3() { }
    private static void demonstration4() { synchronized(Demo11SynchronizedVariants.class){ } }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Робота ниток без модіфікаторів:");
        OuterInterface.InnerClass v = new V1();
        Thread t_1 = new Thread(new RunImp_1(v), "1st");
        Thread t_2 = new Thread(new RunImp_2(v), "2nd");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Робота ниток з volatile модіфікаторoм:");
        v = new V2();
        t_1 = new Thread(new RunImp_1(v), "3rd");
        t_2 = new Thread(new RunImp_2(v), "4th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Робота ниток з обома synchronized методами:");
        v = new V3();
        t_1 = new Thread(new RunImp_1(v), "5th");
        t_2 = new Thread(new RunImp_2(v), "6th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Робота ниток з одним synchronized методом:");
        v = new V4();
        t_1 = new Thread(new RunImp_1(v), "7th");
        t_2 = new Thread(new RunImp_2(v), "8th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Робота ниток з synchronized блоками:");
        v = new V5();
        t_1 = new Thread(new RunImp_1(v), "9th");
        t_2 = new Thread(new RunImp_2(v), "10th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Різні обєкти в synchronized блоках:");
        v = new V6();
        t_1 = new Thread(new RunImp_1(v), "11th");
        t_2 = new Thread(new RunImp_2(v), "12th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Один з методів статичний(не синхронізований):");
        v = new V7();
        t_1 = new Thread(new RunImp_1(v), "13th");
        t_2 = new Thread(new RunImp_2(v), "14th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("Один з методів статичний(синхронізований) :");
        v = new V8();
        t_1 = new Thread(new RunImp_1(v), "15th");
        t_2 = new Thread(new RunImp_2(v), "16th");
        t_1.start();
        t_2.start();
        t_1.join();
        t_2.join();
        
        System.out.println("\nПотокобезопасный StringBuffer :");
        StringBuffer sb = new StringBuffer();
        Thread thread03 = new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (sb){
                    System.out.println("\t Begin of thread with sync block & StringBuffer object");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) { System.err.println("Error"); }
                    System.out.println("\t End of thread with sync block & StringBuffer object");
                }                        
            }
        });
        thread03.start();
        thread03.join(1000);
        System.out.println("\t Main thread before StringBuffer object");
        sb.append(" *** ");        
        System.out.println("\t Main thread after StringBuffer object");
    } // end of main method    
} // end of TestSynchronized class

class RunImp_1 implements Runnable{
    OuterInterface.InnerClass v;    
    public RunImp_1(OuterInterface.InnerClass v) {  this.v = v; }    

    @Override
    public void run() {
        String s = " " + Thread.currentThread().getName() + ", inc ";
        v.inc(s);
    }
} // end of RunImp_1 class

class RunImp_2 implements Runnable{
    OuterInterface.InnerClass v;    
    public RunImp_2(OuterInterface.InnerClass v) {  this.v = v; }    

    @Override
    public void run() {
        String s = " \t" + Thread.currentThread().getName() + ", dec ";
        v.dec(s);
    }
} // end of RunImp_2 class
	
interface OuterInterface {    
    abstract class InnerClass {
        static int a = 0;
        volatile static int b = 0;
        Demo11SynchronizedVariants testSync = new Demo11SynchronizedVariants();
        abstract void inc(String name);
        abstract void dec(String name);            
  } // end of InnerClass class
} // end of OuterInterface interface

class V1 extends OuterInterface.InnerClass {    
    void inc(String name){ 
        for (int i = 0; i < 10; i++) { 
            System.out.println(name + " a = " + (++a)); 
        }
    }
    void dec(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (--a));            
        }
    }    
}

class V2 extends OuterInterface.InnerClass {    
    void inc(String name){ 
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " b = " + (++b));            
        }
    }
    
    void dec(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " b = " + (--b));            
        }
    }    
}

class V3 extends OuterInterface.InnerClass {    
    synchronized void inc(String name){ 
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (++a));            
        }
    }
    
    synchronized void dec(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (--a));            
        }
    }    
}

class V4 extends OuterInterface.InnerClass {    
    synchronized void inc(String name){ 
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (++a));            
        }
    }
    
    void dec(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (--a));            
        }
    }    
}

class V5 extends OuterInterface.InnerClass {   
    //private TestSync testSync = new TestSync();
    void inc(String name){ 
        synchronized (testSync){
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " a = " + (++a));            
            }
        }
    }
    
    void dec(String name){        
        synchronized (testSync){
            for (int i = 0; i < 10; i++) {            
                System.out.println(name + " a = " + (--a));            
            }
        }
    }    
}

class V6 extends OuterInterface.InnerClass {   
    private Demo11SynchronizedVariants testSync1 = new Demo11SynchronizedVariants();
    private Demo11SynchronizedVariants testSync2 = new Demo11SynchronizedVariants();
    void inc(String name){ 
        synchronized (testSync1){
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " a = " + (++a));            
            }
        }
    }
    
    void dec(String name){        
        synchronized (testSync2){
            for (int i = 0; i < 10; i++) {            
                System.out.println(name + " a = " + (--a));            
            }
        }
    }    
}

class V7 extends OuterInterface.InnerClass {    
    synchronized void inc(String name){ 
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (++a));            
        }
    }
    
    void dec(String name){ V7.decS(name); } 
    
    static void decS(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (--a));            
        }
    } 
}

class V8 extends OuterInterface.InnerClass {    
    synchronized void inc(String name){ 
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (++a));            
        }
    }
    
    void dec(String name){ V8.decS(name); } 
    
    static synchronized void decS(String name){
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " a = " + (--a));            
        }
    } 
}