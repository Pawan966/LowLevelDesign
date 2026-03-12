package Singleton;

import java.util.ArrayList;
import java.util.List;

class ConnectionPoolDouble {

    // volatile ensures visibility of changes across threads in double-checked locking
    private static volatile ConnectionPoolDouble instance;

    private final List<String> availableConnections = new ArrayList<>();
    private final List<String> usedConnections = new ArrayList<>();
    private final int poolSize;

    private ConnectionPoolDouble(int poolSize) {
        this.poolSize = poolSize;
        System.out.println(Thread.currentThread().getName()
                + " → Creating pool of " + poolSize + " connections...");

        // Simulate expensive initialization (e.g., opening DB connections)
        try {
            Thread.sleep(1000); // Simulates connection setup time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < poolSize; i++) {
            availableConnections.add("Connection-" + i);
        }
        System.out.println("✅ Pool created with " + poolSize + " connections\n");
    }

    // this method ensures that unnecessary locking is avoided when the instance is already created.
    public static ConnectionPoolDouble getInstance() {
        // First check (no locking) - for performance
        // if multiple threads enters here then use volatile to ensure all threads see the latest value of instance
        if (instance == null) {
            // Synchronize only when instance is null
            synchronized (ConnectionPoolDouble.class) {
                // Second check (with locking) - for thread safety
                if (instance == null) {
                    instance = new ConnectionPoolDouble(5);
                }
            }
        }
        return instance;
    }

    // Borrow a connection from the pool
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

    // Return a connection back to the pool
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

public class Method2 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("🚀 Starting 10 threads to access ConnectionPool...\n");

        // Create 10 threads that will try to get connections
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // Step 1: Get the singleton instance (only created once!)
                ConnectionPoolDouble pool = ConnectionPoolDouble.getInstance();

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


/*
* Problems with double-checked locking:
* a. Requires careful implementation with volatile keyword
* b. Someone can use reflection to access the private constructor and create multiple instances
* c. Complex implementation
* */