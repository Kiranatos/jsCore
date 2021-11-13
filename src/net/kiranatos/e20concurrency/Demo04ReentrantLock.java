package net.kiranatos.e20concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo04ReentrantLock {
    public static void main(String[] args) {
        System.out.println("ReentrantLock and Condition:\n"
                + "- .lock() и .unlock() аналогичны synchronized блоку\n"
                + "- .await(), .signal() и .signalAll() аналогичны wait(), notify(), notifyAll()\n"
                + "- при .await() может быть dead lock если где-то нету .signal()\n"
                + "- .unlock(), .signal() и .signalAll() лучше использовать в finally-блоке, чтоб они выполнились наверняка\n"                
                + "- шаблон: try{ rt.lock(); }finally{ rt.unlock(); } \n\n");
        
        Adam adam = new Adam();        
        Thread a = new Thread(() -> adam.process1(1, ""),"A");        
        a.start();
        Thread b = new Thread(() -> adam.process2(1, "\t"),"B");
        b.start();
        Thread c = new Thread(() -> adam.process3(1, "\t\t"),"C");
        c.start();
        Thread d = new Thread(() -> adam.process1(3, "\t\t\t"),"D");
        d.start();
        Thread e = new Thread(() -> adam.process4(3, "\t\t\t\t"),"E");
        e.start();
        Thread f = new Thread(() -> adam.process5(13, "\t\t\t\t\t"),"F");
        f.start();
    }    
}

class Adam {    
    ReentrantLock rt = new ReentrantLock(true); // если много потоков стоят на .lock-е, то при true они будут unlock в порядке очереди, при false - в случайном порядке
    Condition con = rt.newCondition();
    
    void process1(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "thread %s before .lock in method 1%n", current.getName());        
        try { 
            rt.lock();
            System.out.printf(tab + "thread %s after .lock, will be sleep %d sec. %n", current.getName(), timeToSleep);
            TimeUnit.SECONDS.sleep(timeToSleep);
            System.out.printf(tab + "thread %s woke up! And will be wait others %n", current.getName());
            con.await();
            System.out.printf(tab + "thread %s not waiting any more & continue work %n", current.getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            System.out.printf(tab + "thread %s in [finnally]. Unlock.%n", current.getName());
            rt.unlock();
        }
        
        System.out.printf(tab + "thread %s in method 1 finished!%n", current.getName());
    }
    
    void process2(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "thread %s before .lock in method 2%n", current.getName());
        try { 
            rt.lock();
            System.out.printf(tab + "thread %s after .lock, will be sleep %d sec. %n", current.getName(), timeToSleep);
            TimeUnit.SECONDS.sleep(timeToSleep);
            System.out.printf(tab + "thread %s woke up! And continue work %n", current.getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            System.out.printf(tab + "thread %s in [finnally]. Signal and Unlock.%n", current.getName());
            con.signal();
            rt.unlock();
        }
        
        System.out.printf(tab + "thread %s in method 2 finished!%n", current.getName());
    }
    
    void process3(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "thread %s before .lock in method 3%n", current.getName());
        try { 
            rt.lock();
            System.out.printf(tab + "thread %s after .lock, will be sleep %d sec. %n", current.getName(), timeToSleep);
            TimeUnit.SECONDS.sleep(timeToSleep);
            System.out.printf(tab + "thread %s woke up! And continue work %n", current.getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            System.out.printf(tab + "thread %s in [finnally]. Signal All and Unlock.%n", current.getName());
            con.signalAll();
            rt.unlock();
        }
        
        System.out.printf(tab + "thread %s in method 3 finished!%n", current.getName());
    }
    
    void process4(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "thread %s before .tryLock in method 4%n", current.getName());
        try { 
            while ( ! rt.tryLock()) {
                System.out.printf(tab + "thread %s tried to lock, but has failed. Going to sleep for %d sec. %n", current.getName(), timeToSleep);
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.printf(tab + "thread %s woke up! And will try again!%n", current.getName());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            System.out.printf(tab + "thread %s in [finnally]. Signal someONE and Unlock.%n", current.getName());
            con.signal();
            rt.unlock();
        }
        
        System.out.printf(tab + "thread %s in method 4 finished!%n", current.getName());
    }
    
    void process5(long timeToSleep, String tab){
        Thread current = Thread.currentThread();
        System.out.printf(tab + "thread %s before .tryLock in method 5. Go to sleep for %d sec %n", current.getName(), timeToSleep/2);
        try {             
            TimeUnit.SECONDS.sleep(timeToSleep/4);
            System.out.printf(tab + "thread %s woke up! And will try to lock during next %d seconds...%n", current.getName(), timeToSleep);
            if (rt.tryLock(timeToSleep, TimeUnit.SECONDS)) {
                System.out.printf(tab + "thread %s was success to lock. Going to sleep for %d sec. %n", current.getName(), timeToSleep);
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.printf(tab + "thread %s woke up! And will continue!%n", current.getName());
            } else 
                System.out.println(tab + "TREAD WASN'T SUCCESS TO LOCK DURING ITS TIME SO NOW WILL FALL WITH EXCEPTION because of methods in finally block!!! ");
        } catch (InterruptedException ex) {
            Logger.getLogger(Eva.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            System.out.printf(tab + "thread %s in [finnally]. Signal someONE and Unlock.%n", current.getName());
            con.signal();
            rt.unlock();
        }
        
        System.out.printf(tab + "thread %s in method 4 finished!%n", current.getName());
    }
}
