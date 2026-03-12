package Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Advantages:
 * 1. The JVM guarantees that enum values are instantiated only once
 *    The initialization happens in a thread-safe manner automatically
 * 2. Reflection-proof (cannot instantiate enum via reflection)
 * 3. Simple and concise code
 * 4. No need for volatile, synchronized, or double-checked locking
 */
enum ConnectionPoolEnum {
    INSTANCE; // This is the singleton instance
    
    private final List<String> availableConnections = new ArrayList<>();
    private final List<String> usedConnections = new ArrayList<>();
    private final int poolSize;
    
    // Constructor is called only once when enum is initialized
    ConnectionPoolEnum() {
        this.poolSize = 5;
        System.out.println(Thread.currentThread().getName()
                + " → Creating pool of " + poolSize + " connections...");
        
        // Simulate expensive initialization
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        for (int i = 0; i < poolSize; i++) {
            availableConnections.add("Connection-" + i);
        }
        System.out.println("✅ Pool created with " + poolSize + " connections\n");
    }
    
    // Business methods
    public synchronized String borrowConnection() {
        if (availableConnections.isEmpty()) {
            System.out.println("⚠️  " + Thread.currentThread().getName()
                    + " → No connections available! Waiting...");
            return null;
        }
        
        String connection = availableConnections.remove(0);
        usedConnections.add(connection);
        System.out.println("✓ " + Thread.currentThread().getName()
                + " → Borrowed " + connection
                + " (Available: " + availableConnections.size() + ")");
        return connection;
    }
    
    public synchronized void returnConnection(String connection) {
        if (usedConnections.remove(connection)) {
            availableConnections.add(connection);
            System.out.println("↩ " + Thread.currentThread().getName()
                    + " → Returned " + connection
                    + " (Available: " + availableConnections.size() + ")");
        }
    }
    
    public synchronized int getAvailableCount() {
        return availableConnections.size();
    }
    
    public synchronized int getUsedCount() {
        return usedConnections.size();
    }
}

public class Method3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("🚀 Starting 10 threads to access ConnectionPoolEnum...\n");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // Step 1: Get the singleton instance (only created once!)
                ConnectionPoolEnum pool = ConnectionPoolEnum.INSTANCE;

                // Step 2: Borrow a connection from the pool
                String connection = pool.borrowConnection();

                if (connection != null) {
                    try {
                        // Step 3: Simulate using the connection (e.g., database query)
                        Thread.sleep(500);

                        // Step 4: Return the connection back to the pool
                        pool.returnConnection(connection);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Thread-" + (i + 1)).start();
        }

        // Wait for all threads to complete
        Thread.sleep(3000);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("✅ All threads completed!");
        System.out.println("Final pool status:");
        ConnectionPool pool = ConnectionPool.getInstance();
        System.out.println("  Available: " + pool.getAvailableCount());
        System.out.println("  In use: " + pool.getUsedCount());
    }
}

