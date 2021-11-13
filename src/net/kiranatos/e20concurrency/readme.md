Переработать черновик:

Executors.newFixedThreadPool(10, new ThreadFactory() { //фабрика, которая предоставляет свои нити, а .submit-ые игнорирует
            @Override
            public Thread newThread(Runnable r) {
                 return new Thread();
            }
        });        
        
        Executors.newFixedThreadPool(10, runnable -> {
            return new Thread(runnable);
        });    
        
        Executors.newFixedThreadPool(10, Thread::new);    
        *****
        As the alternative for the volatile field AtomicBoolean could be used, 
        where the changes made there have the same memory visibility guarantee, 
        but also provides more rich features (known as CAS - Compare-And-Swap, 
        which is also the base for the "lock-free" concurrency).
        ****        
        There are mainly two types of the explicit locks available:
            Lock and
            ReadWriteLock          
        ****
        lock has to be always unlocked, otherwise it can lead to deadlock
       
        /*
        Lock lock = new Lock();
        lock.lock();
        try {
            // do some actions
        } finally { lock.unlock(); } 
        */
        
        /*
        Java provides set of concurrent data structures which could be found 
        in the java.util.concurrent package. The most notable ones are:
                ConcurrentMap
                BlockingQueue
                CopyOnWriteArrayList    and many many others*/

/*
 Проблеми многопоточности:
- DeadLock
- LiveLock
- Race Condition
- Starvation
 */

/*
Просмотреть задачи на многопоточность в: 
- stepik
- javarush
*/


/*
To start a thread just call the corresponding start() method of the Thread, i.e. 
        new Theory1Thread().start();
or
        new Thread(new Theory1Runnable()).start();
If you are using Java 8, you can use lambda to provide inline Runnable implementation:
        new Thread(() -> System.out.println("Running")).start();

Also an Executor could be used to start a new thread 
(which is the preferred/recommended way to manage threads in Java starting from JDK 1.5):
        Executors.newFixedThreadPool(10).submit(() -> System.out.println("Running"));
submit() method supports both Runnable and Callable.

To stop the Executor one of the following methods could be used:
shutdown() - Initiates an orderly shutdown in which previously submitted
tasks are executed, but no new tasks will be accepted. 
Invocation has no additional effect if already shut down
shutdownNow() - Attempts to stop all actively executing tasks, 
halts the processing of waiting tasks, and returns a list of the 
tasks that were awaiting execution

By default newly started thread is running in so called no "daemon" mode. 
The difference between "daemon" and non "daemon" mode is in that JVM will
terminate as soon as there are no more non "daemon" threads left.

To set the thread to be a "daemon" thread call setDaemon(true) on the 
thread instance before ﻿starting the thread. 

If using the Executor(s) it allows to provide the ThreadFactory which will be 
used create every thread for corresponding executor, i.e.
        Executors.newFixedThreadPool(10, runnable -> {
            return new Thread(runnable);
        }); 
In the example above nothing happens for the newly created thread, 
but you can set the priority, name, "daemon"-ize, etc.
(also for the example above the lambda could be replaced with method reference Thread::new).

There is no reliable way to stop the running thread in Java. 
The recommended way is to use interruption policy, which is the agreed contract 
between the coordinating threads (only in the case when the "policy" is documented and known).

To interrupt the thread call interrupt() method on the Thread instance.

To check the thread interrupted status isInterrupted() method can be used. 
There is although the alternative way to check the interruption status of the 
current thread, which is Thread.interrupted() (but be aware the interruption 
status is cleared! by this method, so use it with caution).

Also many of the waiting/blocking methods throw InterruptedException 
and also reset the interruption status, so unless you are handling 
InterruptedException properly reset the interruption status back 
(so never swallow it) by calling Thread.currentThread().interrupt().

There are multiple ways to synchronize the threads with each other. 
When we say synchronize most of the time we mean synchronize the memory state between 
the threads, so they could see the corresponding changes done by each other.

The basic mechanism to coordinate threads with each other is to use join method, 
to allow one thread to wait until that other thread "dies" (i.e. completes). 
So in another word that another thread joins the execution flow of the thread which called join.

Example:

final Thread thread = new Thread(() -> {
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (final InterruptedException ex) {
        // Reset the interruption status.

        Thread.currentThread().interrupt();
    }
    System.out.println("Exiting");
});
thread.start();
// It is recommended, whenever is appropriate and possible, to use timed out version of the available JDK API around concurrency.

thread.join(TimeUnit.SECONDS.toMillis(10)); // So wait thread to complete and then the current thread resumes.

Also Object wait/notify/notifyAll could be used to coordinate the threads, although the lock has to be acquired before calling those methods (which is done by using the synchronized ﻿keyword).

One more low level synchronization mechanism is volatile keyword. When the field is marked as volatile any changes made to this field are guaranteed to be visible by another thread when reading the value of this field (although it doesn't prevent multiple threads update the volatile field at the same time, i.e. it doesn't provide the exclusive access, but simply guarantees the memory visibility).

The typical use case for the volatile field is to use as the boolean flag to cancel/terminate the thread.

As the alternative for the volatile field AtomicBoolean could be used, where the changes made there have the same memory visibility guarantee, but also provides more rich features (known as CAS - Compare-And-Swap, which is also the base for the "lock-free" concurrency).

Apart from the low level built-in synchronization mechanism, Java provides more high level constructs from the java.util.concurrent ﻿package. Such as:

    CountDownLatch
    CyclicBarrier
    Exchanger
    Semaphore
    and others
	
	

Java has built-in mechanism to synchronize the access to the methods called by multiple threads by using the synchronized keyword.

Although starting from JDK 1.5 Java offers alternative somewhere more flexible and powerful approach by using explicit Lock-s from the java.util.concurrent.locks package.

There are mainly two types of the explicit locks available:

    Lock and
    ReadWriteLock

Where the first one is implemented by the ReentrantLock and the second one by the ReentrantReadWriteLock correspondingly.

Reentrant part means is that you can call lock() (so "reenter") on the critical 
section for the same thread, if the current thread is already holding the lock 
(otherwise it would not be possible to call other current thread's methods where lock is obtained as well).

The difference between Lock and ReadWriteLock is that the later offers more 
granular control over read vs write ﻿locks. I.e. you could have multiple 
threads reading the values, and only one thread writing the value, in which case all read locks will wait.

One of the important consequences of the explicit lock, is that it has to 
be always unlocked, otherwise it can easily lead to the deadlock.

To ensure the explicit lock is always unlocked, try/finally ﻿idiom is used:

lock.lock();
try {
    // do some actions
} finally {
    lock.unlock();
}



Java provides set of concurrent data structures which could be found in the java.util.concurrent package. The most notable ones are:

    ConcurrentMap
    BlockingQueue
    CopyOnWriteArrayList
    and many many others

For the complete list and details refer the API for the java.util.concurrent ﻿package.