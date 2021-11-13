package net.kiranatos.e20concurrency;

import java.lang.Thread.State;
import java.security.SecureRandom;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;

public class Demo06SynchronizerExchanger {
    public static void main(String[] args) {
        System.out.println("Синхронайзер  Exchanger<V>:");/*
        - Обмен данными между 2 нитями.
        (Коли 3, - зайва висить. При 4-х неможливо визначити між якими проходе обмін)"
                + " - Чтобы обмен был успешен нужно отправить своё значение .exchange(myValue)\n"
                + " - B .exchange() поток блокируется ожидая партнера\n"
                + " - Не использовать в синхр.блоке/методе */
        
        System.out.println("Example #1 (static Exchanger inside class):");
        Dorororo doro = new Dorororo();
        new Thread(()-> doro.processIchi(new Item("itemA"), "string A"), "thread a) ").start();
        new Thread(()-> doro.processIchi(new Item("itemB"), "string B"), "thread b) ").start();
                
        OzoHelper.sleep(1, OzoHelper.Time.SECONDS);
        System.out.println("\nExample #2 (set Exchanger from outside using generics):");
        doro.setExchanger(new Exchanger<Integer>());
        new Thread(()-> doro.processNi(111), "thread c) ").start();
        new Thread(()-> doro.processNi(666), "thread d) ").start();
        OzoHelper.sleep(1, OzoHelper.Time.SECONDS);
        System.out.println(" ********* ********* ");
        doro.setExchanger(new Exchanger<Item>());
        new Thread(()-> doro.processNi(new Item("itemE")), "thread e) ").start();
        new Thread(()-> doro.processNi(new Item("itemF")), "thread f) ").start();
        
        OzoHelper.sleep(1, OzoHelper.Time.SECONDS);
        System.out.println(" ++++++++++ ++++++++++ ");
        doro.setExchanger(new Exchanger());
        new Thread(()-> doro.processNi(new StringBuffer("stringBufferG")), "thread G) ").start();        
        new Thread(()-> doro.processNi(3.02), "thread H) ").start();
        new Thread(()-> doro.processNi(new Item("itemI")), "thread I) ").start();
        new Thread(()-> doro.processNi(new StringBuffer("stringBufferJ")), "thread J) ").start();
        
        OzoHelper.sleep(1, OzoHelper.Time.SECONDS);
        System.out.println("\nExample #3 (Using Exchanger for get data from thread to main method):");
        Exchanger<String> mediator = new Exchanger<String>();
        doro.setExchanger(mediator);
        new Thread(()-> doro.processNi("Data from K thread"), "thread K) ").start();
        try {
            String dataFromThread = mediator.exchange("Data from main method to thread K");
            System.out.println("Получена информация из нити: " + dataFromThread);
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo06SynchronizerExchanger.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
}

class Dorororo {    
    private static final Exchanger<Item> exchangerItem = new Exchanger<>();
    private static final Exchanger<String> exchangerString = new Exchanger<>();  
    private Exchanger exchanger;

    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }
    
    /**
     * for Example 1
     * @param it
     * @param str 
     */
    void processIchi(Item it, String str){        
        System.out.printf("Begin of %s my values:[%s and %s]%n", 
                Thread.currentThread().getName(), 
                str, it.name);
        try {            
            Item it2 = exchangerItem.exchange(it);
            String str2 = exchangerString.exchange(str, 5, TimeUnit.SECONDS); /* waiting for 
                    the exchange 5 seconds or will go ahead and throw exceptions: 
                    InterruptedException, TimeoutException */
            
            System.out.printf("After exchange in %s my values:[%s and %s], alien values:[%s and %s]%n", 
                    Thread.currentThread().getName(), 
                    str, it.name,
                    str2, it2.name);            
        } catch (InterruptedException | TimeoutException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.printf("End of %s%n", 
                Thread.currentThread().getName());
    }
    
    /**
     * for Example 2 and 3
     * @param <T>
     * @param obj 
     */
    <T> void processNi(T obj){
        System.out.printf("Begin of %s my values:[%s]%n", 
                Thread.currentThread().getName(), 
                obj.toString());        
        T obj2 = (T)exchange(exchanger, obj);
        System.out.printf("After exchange in %s my values:[%s], alien values:[%s]%n", 
                Thread.currentThread().getName(), 
                obj.toString(),
                obj2.toString());            
        System.out.printf("End of %s%n", 
                Thread.currentThread().getName());
    }
    
    /**
     * for Example 2 and 3
     * @param <T>
     * @param exc
     * @param obj
     * @return 
     */
    private <T> T exchange(Exchanger<T> exc, T obj){
        T t = obj;
        try {
            t = exc.exchange(obj);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dorororo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}

class Item {
    String name;
    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + '}';
    }    
}

/* тройной dead-lock:
Exchanger<String> mediatorLM = new Exchanger<>();
Exchanger<String> mediatorMN = new Exchanger<>();
Exchanger<String> mediatorNL = new Exchanger<>();
new Thread(()-> doro.processSan(mediatorNL, mediatorLM), "thread L) ").start();
new Thread(()-> doro.processSan(mediatorLM, mediatorMN), "thread M) ").start();
new Thread(()-> doro.processSan(mediatorMN, mediatorNL), "thread N) ").start();

void processSan(Exchanger<String> prev, Exchanger<String> next){
        String strPrevAlien = "", 
                strNextAlien = "", 
                myString = "Я нить " + Thread.currentThread().getName() + "!" ;
        System.out.printf("Begin of %s my value:[%s]%n", 
                Thread.currentThread().getName(), myString);        
        try {            
            strPrevAlien = prev.exchange(myString);
            strNextAlien = next.exchange(myString);                                     
        } catch (InterruptedException ex) { }
        System.out.printf("After exchanges in %s my values:[%s], aliens values:[%s and %s]%n", 
                Thread.currentThread().getName(), myString, strPrevAlien, strNextAlien);
        System.out.printf("End of %s%n", 
                Thread.currentThread().getName());
} */