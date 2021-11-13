package net.kiranatos.e20concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;

public class Demo02Executors {
    
    Future t = Executors.newFixedThreadPool(10).submit(() -> new String("Это плохой вариант, т.к. нить обработчика не остановить"));
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Примеры создания потоков:");
        long time = System.currentTimeMillis();
                
        System.out.println(" --5-- Нить №5 -  запуск через ExecutorService с указанием кол-ва пулов нитей");
        System.out.println("\t Начальное время : " + (System.currentTimeMillis() - time) + " milliseconds");
        ExecutorService es = Executors.newFixedThreadPool(3);
                                es.submit(new A(5.1));
        Future<?> future52 =    es.submit(new A(5.2));
                                es.submit(new CCallable(5.3));        
        es.execute(new A(5.5));
        // difference: .submit() returns Future<?>, execute is void 
        
        Future<String>  future54 =  es.submit(new CCallable(5.4));
        System.out.println("Результат из Callable:" + future54.get());
        
        List<Callable<String>> list = new ArrayList<Callable<String>>();
        list.add(new CCallable(5.6));
        list.add(new CCallable(5.7));
        list.add(new CCallable(5.8));        
        
        // Выполняет все нити и возвращает результати в листе
        List<Future<String>> listCall = es.invokeAll(list); 
        
        // Возвращает результат одной нити, которая была успешна. Если другие не завершились - обрывает их (возможны Exceptions)
        String s = es.invokeAny(list); 
        System.out.println(s);
                
        es.shutdown(); /* БЕЗ void shutdown(); НИТЬ ОБРАБОТЧИКА НЕ ОСТАНОВИТСЯ. Прекращение работы обрабочика ExecutorService. 
        Дает возможность доработать старым потокам, но новые сабмитить нельзя*/
        /*List<Runnable> = ex.shutdownNow(); Прекращение работы обрабочика ExecutorService и всех потоков.
        Возвращает список прерванных потоков */
                
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.submit(new A(5.9));
        es2.submit(new CCallable(5.11));
        es2.shutdown();
        // условия ставить после shutdown
        if (es2.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("Это условие не выполнится т.к. нити работают дольше 5 секунды, а это условие ждать больше 5 сек не будет");
        } 
        if (es2.awaitTermination(1, TimeUnit.DAYS)) {
            System.out.println("Это условие .awaitTermination выполнится т.к. оно ждёт пока нити закончат работу и готово ждать сутки");
            System.out.println("\t Прошло времени : " + (System.currentTimeMillis() - time) + " milliseconds");            
        }        
    }       
}

class CCallable implements Callable <String>{
    private double n;
    CCallable(double n) { this.n = n;}
    @Override
    public String call() throws Exception {
        System.out.println("[" + n + "] Запуск call-нитки C № " + n);        
        OzoHelper.sleep(10, OzoHelper.Time.SECONDS);
        System.out.println("[" + n + "] End of call-нитки C № " + n);
        return "return from Call-нитки C № " + n;
    }
}