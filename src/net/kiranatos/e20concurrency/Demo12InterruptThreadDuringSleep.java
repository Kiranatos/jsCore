package net.kiranatos.e20concurrency;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
InterruptedException - if any thread has interrupted the current thread. 
The interrupted status of the current thread is cleared when this exception is thrown.
https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/lang/Thread.html#sleep(long)

Пояснення:
- .interrupt() - не зупиняє нитку, а тільки ставить прапорець на .isInterrupted() -> true
якщо не робити перевірку .isInterrupted() в коді, то нитка не зупиниться
- якщо переривання сталось під час Thread.sleep(milliseconds)
та було перехоплено і оброблено - то прапорець .isInterrupted() -> false знову
Метод Thread.sleep(milliseconds) має внутрішню перевірку на .isInterrupted().
Тому після пробудження нитка продовжить працювати.
І навіть перевірка після сну на .isInterrupted() не зупинить нитку.
Проте якщо переривання сталось до сна то Thread.sleep(milliseconds) викине InterruptedException */

public class Demo12InterruptThreadDuringSleep {
    public static void main(String[] args) {        
        Thread t_1 = new Thread(new ThreadUno());
        Thread t_2 = new Thread(new ThreadDos());        
        System.out.println("Has ThreadOne interrupted? " + t_1.isInterrupted());
        System.out.println("Has ThreadTwo interrupted? " + t_2.isInterrupted());
        t_1.start();
        t_2.start();
        System.out.println("Main going to sleep for 3 seconds");
        sleep(3, "main method\n");        
        System.out.println("Start interrupt ThreadOne");
        t_1.interrupt();
        System.out.println("Start interrupt ThreadTwo");
        t_2.interrupt();        
        try {
            t_1.join();
            t_2.join();
            System.out.println("Has ThreadOne interrupted? " + t_1.isInterrupted());
            System.out.println("Has ThreadTwo interrupted? " + t_2.isInterrupted());
        } catch (InterruptedException ex) { 
            Logger.getLogger(Demo12InterruptThreadDuringSleep.class.getName()).log(Level.SEVERE, null, ex);
            //Thread.currentThread().interrupt();
        }        
    }
    
    static void sleep(int seconds, String message){
        try {
            Thread.sleep(seconds * 1000);            
        } catch (InterruptedException ex) { System.out.println("\n\tTimeSleepErorr: " + message); }
    }
}

class ThreadUno implements Runnable {
    @Override
    public void run() {
        String s = "s";
        Date d = new Date();
        System.out.println("ThreadOne has started. Doing some action. Approximatly time 12 seconds.");
        for (int i = 0; i < 200000; i++) {
            s = s + i;        
            Thread current = Thread.currentThread();            
            if (current.isInterrupted()) {
                System.out.println("ThreadOne geting out!!! " + current.isInterrupted());
                return;
            }            
        }
        System.out.println("ThreadOne: Action ended. Time: " + (new Date().getTime() - d.getTime())/1000 + " sec.");
    }    
}

class ThreadDos implements Runnable {
    @Override
    public void run() {
        int sec = 9;
        System.out.println("ThreadTwo has started. I will sleep " + sec + " sec.");
        Demo12InterruptThreadDuringSleep.sleep(sec, "run method in class ThreadDos\n");        
        Thread current = Thread.currentThread();
        if (current.isInterrupted()) {
            System.out.println("ThreadTwo geting out!!! " + current.isInterrupted());
            return;
        }
        System.out.println("ThreadTwo: Did I interrupt? " + current.isInterrupted());
        System.out.println("ThreadTwo has woke up and go out");
    }    
}
