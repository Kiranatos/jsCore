package net.kiranatos.e20concurrency;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;


public class Demo01Thread {
    
    private static void sleepExample(long milliseconds){
        try { // two ways for pause:
            Thread.sleep(milliseconds);
            TimeUnit.SECONDS.sleep(milliseconds);
            System.out.println(TimeUnit.MINUTES.toHours(122)); // min -> hours
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo01Thread.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt(); // Reset/Wake Up the interruption status.
        }        
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Приклади створення ниток в багатопотоковісті");        
        
        System.out.println("\n\t I) Нитка №1 запуск через Thread, класса который импелментирует Runnable");
        System.out.println(" =========== [1.1] =========== ");
        Thread thread_1_1 = new Thread(new A(1.1));
        thread_1_1.start();
        // подождать 5 секунд завершения нити, если нить продолжает работать - то перестает ждать
        thread_1_1.join(TimeUnit.SECONDS.toMillis(5));        
        
        System.out.println("Можна створити декілька ниток на основі одного обєкту:");
        System.out.println("[1.2]");
        A a1 = new A(1.2);
        Thread thread_1_2_1 = new Thread(a1);
        Thread thread_1_2_2 = new Thread(a1);
        Thread thread_1_2_3 = new Thread(a1);
        Thread thread_1_2_4 = new Thread(a1);
        thread_1_2_1.start();
        thread_1_2_2.start();
        thread_1_2_3.start();
        thread_1_2_4.start();
        
        System.out.println("Або створити декілька ниток з окремих обєктiв:");
        System.out.println("[1.3]");
        A aa1 = new A(1.31);
        A aa2 = new A(1.32);
        A aa3 = new A(1.33);
        A aa4 = new A(1.34);
        Thread thread_1_3_1 = new Thread(aa1);
        Thread thread_1_3_2 = new Thread(aa2);
        Thread thread_1_3_3 = new Thread(aa3);
        Thread thread_1_3_4 = new Thread(aa4);
        thread_1_3_1.start();
        thread_1_3_2.start();
        thread_1_3_3.start();
        thread_1_3_4.start();
        
        System.out.println("Mожна запустити неявно нитку з конструктора (або іншого методу):");
        System.out.println("[1.4]");
        new A(1.4, "\u00AB 1.4 \u00BB Start in A constructor \u00AB 1.4 \u00BB");
        
        System.out.println("\n\t II) Нитка № 2 -  Унаследоваться от Thread, который реализует интерфейс Runnable, и переопределить метод run");
        System.out.println(" =========== [2] =========== ");
        /* Недостатки метода № 2 :
        - унаследовавшись от Thread, не сможем добавить еще одиного класса-родителя к своему классу
        - может понадобиться запустить несколько нитей на основе одного единственного объекта, как это сделано в примере [1.2] */
        B b1 = new B(2);
        B b2 = new B(2);
        b1.start();        
        b2.start();
        
        System.out.println("Також можна запустити неявно нитку з конструктора (або іншого методу):");
        System.out.println("[2.1]");
        new B(2.1, "\u00AB 2.1 \u00BB Start in B constructor \u00AB 2.1 \u00BB");
        
        System.out.println("\n\t III) --- Нить № 3 - анонимный класс наследуемый от класса Thread");
        System.out.println(" =========== [3] =========== ");
        Thread thread_3 = new Thread() {
            @Override
            public void run() {
                System.out.println("\u00AB 3 \u00BB Запуск нитки № 3");
                OzoHelper.sleep(10, OzoHelper.Time.SECONDS);
                System.out.println("\u00AB 3 \u00BB End of нитки № 3");
            }
        };        
        thread_3.start();
        
        System.out.println("\n\t IV) --- Нить № 4 - анонимный класс по интерфейсу Runnable");
        System.out.println(" =========== [4] =========== ");
        Runnable thread_4 = new Runnable() {
            @Override
            public void run() {
                System.out.println("\u00AB 4 \u00BB Запуск нитки № 4");
                OzoHelper.sleep(10, OzoHelper.Time.SECONDS);                
                System.out.println("\u00AB 4 \u00BB End of нитки № 4");
            }
        };
        new Thread(thread_4).start();
        //thread_4.run(); - не является запуском отдельного потока. Это продолжение main-потока
        
        System.out.println("\n\t V) --- Нить №5 -  запуск через Лямбду (аналог Нити № 4)");
        System.out.println(" =========== [5] =========== ");
        new Thread(() -> System.out.println("Running Нить №5")).start();
        C c = new C();
        new Thread(() -> c.demonstrate()).start();        
    }    
}

class A implements Runnable {
    private double n;
    A(double n) { this.n = n;}
    A(double n, String s) { this.n = n; System.out.println(s);  new Thread(this).start(); }
    @Override
    public void run() {        
        System.out.println("[" + n + "] Запуск run-нитки A № " + n);
        OzoHelper.sleep(10, OzoHelper.Time.SECONDS);
        System.out.println("[" + n + "] End of run-нитки A № " + n);
    }
}

class B extends Thread {
    private double n;
    B(double n) { this.n = n;}
    B(double n, String s) { this.n = n; System.out.println(s); start(); }
    @Override
    public void run() {
        System.out.println("\u00AB" + n + "\u00BB Запуск thr-нитки B № " + n);
        OzoHelper.sleep(10, OzoHelper.Time.SECONDS);        
        System.out.println("\u00AB" + n + "\u00BB End of thr-нитки B № " + n);
    }    
}

class C {
    void demonstrate(){
        System.out.println("\tI am C class in thread #5 without implementation Runnable interface");
    }
}