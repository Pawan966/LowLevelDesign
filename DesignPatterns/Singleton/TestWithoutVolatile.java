package Singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * This test demonstrates the ACTUAL PROBLEMS that occur without volatile
 * in double-checked locking.
 */

// WITHOUT volatile - BROKEN!
class BrokenSingleton {
    private static BrokenSingleton instance; // ❌ NO volatile
    private final long creationTime;
    private final String threadName;
    
    private BrokenSingleton() {
        this.threadName = Thread.currentThread().getName();
        this.creationTime = System.nanoTime();
        
        // Simulate some initialization work
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static BrokenSingleton getInstance() {
        if (instance == null) {              // First check - NO LOCK
            synchronized (BrokenSingleton.class) {
                if (instance == null) {      // Second check
                    instance = new BrokenSingleton();
                }
            }
        }
        return instance;
    }
    
    public long getCreationTime() {
        return creationTime;
    }
    
    public String getThreadName() {
        return threadName;
    }
}

// WITH volatile - CORRECT!
class CorrectSingleton {
    private static volatile CorrectSingleton instance; // ✅ WITH volatile
    private final long creationTime;
    private final String threadName;
    
    private CorrectSingleton() {
        this.threadName = Thread.currentThread().getName();
        this.creationTime = System.nanoTime();
        
        // Simulate some initialization work
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static CorrectSingleton getInstance() {
        if (instance == null) {              // First check - NO LOCK
            synchronized (CorrectSingleton.class) {
                if (instance == null) {      // Second check
                    instance = new CorrectSingleton();
                }
            }
        }
        return instance;
    }
    
    public long getCreationTime() {
        return creationTime;
    }
    
    public String getThreadName() {
        return threadName;
    }
}

public class TestWithoutVolatile {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Testing Double-Checked Locking WITHOUT volatile ===\n");
        
        // Run the test multiple times to increase chance of seeing the bug
        for (int run = 1; run <= 5; run++) {
            System.out.println("Run #" + run + ":");
            testBrokenSingleton();
            testCorrectSingleton();
            System.out.println();
        }
    }
    
    private static void testBrokenSingleton() throws InterruptedException {
        // Reset instance for each test (using reflection)
        try {
            java.lang.reflect.Field field = BrokenSingleton.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int numThreads = 100;
        CountDownLatch latch = new CountDownLatch(1);
        Set<Integer> hashCodes = new HashSet<>();
        Set<Long> creationTimes = new HashSet<>();
        Set<String> threadNames = new HashSet<>();
        
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    latch.await(); // Wait for all threads to be ready
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                BrokenSingleton singleton = BrokenSingleton.getInstance();
                synchronized (hashCodes) {
                    hashCodes.add(System.identityHashCode(singleton));
                    creationTimes.add(singleton.getCreationTime());
                    threadNames.add(singleton.getThreadName());
                }
            }, "BrokenThread-" + i);
            threads[i].start();
        }
        
        latch.countDown(); // Release all threads at once
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("  WITHOUT volatile:");
        System.out.println("    Unique instances: " + hashCodes.size() + 
                         (hashCodes.size() > 1 ? " ❌ MULTIPLE INSTANCES!" : " ✓"));
        System.out.println("    Unique creation times: " + creationTimes.size());
        System.out.println("    Created by threads: " + threadNames);
    }
    
    private static void testCorrectSingleton() throws InterruptedException {
        // Reset instance for each test
        try {
            java.lang.reflect.Field field = CorrectSingleton.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int numThreads = 100;
        CountDownLatch latch = new CountDownLatch(1);
        Set<Integer> hashCodes = new HashSet<>();
        Set<Long> creationTimes = new HashSet<>();
        Set<String> threadNames = new HashSet<>();
        
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                CorrectSingleton singleton = CorrectSingleton.getInstance();
                synchronized (hashCodes) {
                    hashCodes.add(System.identityHashCode(singleton));
                    creationTimes.add(singleton.getCreationTime());
                    threadNames.add(singleton.getThreadName());
                }
            }, "CorrectThread-" + i);
            threads[i].start();
        }
        
        latch.countDown();
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("  WITH volatile:");
        System.out.println("    Unique instances: " + hashCodes.size() + " ✓");
        System.out.println("    Unique creation times: " + creationTimes.size());
        System.out.println("    Created by threads: " + threadNames);
    }
}

