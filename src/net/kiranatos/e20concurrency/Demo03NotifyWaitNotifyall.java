package net.kiranatos.e20concurrency;

import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo03NotifyWaitNotifyall {
    public static void main(String[] args) {/*
        - wait, notify, notifyAll должны быть внутри synchronized метода или блока
        - монитором должен быть объект, которому они пренадлежат иначе exception
        - остороженее с wait, можно сделать dead lock, когда нить будут ждать друг друга        
        - wait(): освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор, пока другой поток не вызовет метод notify()
        - notify(): продолжает работу потока, у которого ранее был вызван метод wait()
        - notifyAll(): возобновляет работу всех потоков, у которых ранее был вызван метод wait()
        - notify, notifyAll не отдает синхр.блок, просто сообщает другим, что скоро закончит работу и код будет свободен             */
        Eva eva = new Eva();        
        Thread a = new Thread(() -> eva.process1(1),"A");
        eva.someoneWait = a;
        a.start();
        Thread b = new Thread(() -> eva.process2(1),"B");
        b.start();
        Thread c = new Thread(() -> eva.process3(1),"C");
        c.start();
        Thread d = new Thread(() -> eva.process1(3),"D");
        d.start();
    }    
}

class Eva {
    Thread someoneWait;
    
    void process1(long timeToSleep){
        Thread current = Thread.currentThread();
        System.out.printf("thread %s before sync block in method 1%n", current.getName());
        synchronized (this){
            System.out.printf("\tthread %s in sync block, will be sleep %d sec. %n", current.getName(), timeToSleep);
            try {
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.printf("\tthread %s in sync block woke up! And will be wait others %n", current.getName());                
                if(State.BLOCKED.equals(someoneWait.getState())){
                    System.out.printf("\t\tNo, thread %s have %s status, thread %s will not be waiting%n", someoneWait.getName(), someoneWait.getState(), current.getName());
                } else
                    wait();                
                System.out.printf("\tthread %s continue work %n", current.getName());
            } catch (InterruptedException ex) {
                Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.printf("thread %s after sync block in method 1 [END]%n", current.getName());
    }
    
    void process2(long timeToSleep){
        Thread current = Thread.currentThread();
        System.out.printf("thread %s before sync block in method 2%n", current.getName());
        synchronized (this){
            System.out.printf("\tthread %s in sync block, will be sleep %d sec. %n", current.getName(), timeToSleep);
            try {
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.printf("\tthread %s in sync block woke up! And notify someONE %n", current.getName());
                notify();
                System.out.printf("\tthread %s continue work after notify %n", current.getName());
            } catch (InterruptedException ex) {
                Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.printf("thread %s after sync block in method 2 [END]%n", current.getName());
    }
    
    void process3(long timeToSleep){
        Thread current = Thread.currentThread();
        System.out.printf("thread %s before sync block in method 3%n", current.getName());
        synchronized (this){
            System.out.printf("\tthread %s in sync block, will be sleep %d sec. %n", current.getName(), timeToSleep);
            try {
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.printf("\tthread %s in sync block woke up! And notify ALL %n", current.getName());
                notifyAll();
                System.out.printf("\tthread %s continue work after notifyAll %n", current.getName());
            } catch (InterruptedException ex) {
                Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.printf("thread %s after sync block in method 3 [END]%n", current.getName());
    }
}