package net.kiranatos.e20concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo05ReentrantReadWriteLock {
    public static void main(String[] args) {
        System.out.println("ReentrantReadWriteLock:\n"
                + " - блокировка кода на запись и чтение. Читать могут несколько потоков одновременно, писать только один\n"
                + "Пишущий не может войти, пока хоть хотя бы один читает. Когда один write вошёл - остальные ждут.\n\n");
        
        Zetto zetto = new Zetto();        
             
        Thread a = new Thread(() -> { zetto.read(1, "");            zetto.write(1, ""); },          "A");        
        a.start();
        Thread b = new Thread(() -> { zetto.read(1, "\t");          zetto.write(1, "\t"); },        "B");
        b.start();
        Thread c = new Thread(() -> { zetto.read(1, "\t\t");        zetto.write(1, "\t\t"); },      "C");
        c.start();
        Thread d = new Thread(() -> { zetto.read(3, "\t\t\t");      zetto.write(3, "\t\t\t"); },    "D");
        d.start();
        Thread e = new Thread(() -> { zetto.read(3, "\t\t\t\t");    zetto.write(3, "\t\t\t\t"); },  "E");
        e.start();
        Thread f = new Thread(() -> { zetto.write(10, "\t\t\t\t\t"); zetto.read(10, "\t\t\t\t\t"); },"F");
        f.start();
    }    
}

class Zetto {    
    ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
    Lock readLock = rrwl.readLock();
    Lock writeLock = rrwl.writeLock();
    //Condition readCondi = readLock.newCondition(); //Есть подозрения, что read не поддерживает Condition
    Condition writeCondi = writeLock.newCondition();
    int value = 0;   
    
    void read(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "%s-thread[READ] before .lock%n", current.getName());
        try {
            readLock.lock();
            System.out.printf(tab + "%s-thread[READ] after .lock, will be sleep %d sec. VALUE = %d %n", current.getName(), timeToSleep, value);            
            TimeUnit.SECONDS.sleep(timeToSleep);
            System.out.printf(tab + "%s-thread[READ] woke up!%n", current.getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo05ReentrantReadWriteLock.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            System.out.printf(tab + "%s-thread[READ] in finnally block. Unlock.%n", current.getName());
            readLock.unlock();
        }        
    }
    
    void write(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "%s-thread[WRITE] before .lock%n", current.getName());
        try {
            writeLock.lock();
            System.out.printf(tab + "%s-thread[WRITE] after .lock, will be sleep %d sec. VALUE = %d %n", current.getName(), timeToSleep, value);            
            TimeUnit.SECONDS.sleep(timeToSleep);
            value++;
            System.out.printf(tab + "%s-thread[WRITE] woke up! VALUE = %d%n", current.getName(), value);            
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo05ReentrantReadWriteLock.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            System.out.printf(tab + "%s-thread[WRITE] in finnally block. Unlock.%n", current.getName());
            writeLock.unlock();
        }        
    }
}
