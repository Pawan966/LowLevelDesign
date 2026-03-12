package Singleton;

import java.util.ArrayList;
import java.util.List;

/*
*
* State changes PER-REQUEST (different for each user/request)     → ❌ NOT Singleton
  State changes GLOBALLY (shared across all requests)             → ✅ Singleton is FINE

  ❌ BAD — Cart is PER-USER, not global
public class ShoppingCart {

    private static volatile ShoppingCart instance;

    private String userId;
    private List<String> items = new ArrayList<>();
    private double total;

    public static ShoppingCart getInstance() {  }

public void setUserId(String userId)  { this.userId = userId; }
public void addItem(String item, double price) {
    items.add(item);
    total += price;
}
}
* */

class ConnectionPool {

    // No need for volatile when using synchronized method
    private static ConnectionPool instance;

    private final List<String> availableConnections = new ArrayList<>();
    private final List<String> usedConnections = new ArrayList<>();
    private final int poolSize;

    private ConnectionPool(int poolSize) {
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

    // synchronized keyword is used to ensure that only one thread can execute this method at a time.
    /*
    * Time     Thread         Action
═════════════════════════════════════════════════════════════

0ms      Thread-1       Enters getInstance(), acquires LOCK 🔒
                        instance == null? YES
                        Creating pool... (takes 1000ms)

0ms      Thread-2       Tries to enter getInstance()
                        ⏳ BLOCKED — Thread-1 holds lock

0ms      Thread-3       Tries to enter getInstance()
                        ⏳ BLOCKED — Thread-1 holds lock

0ms      Thread-4       ⏳ BLOCKED
0ms      Thread-5       ⏳ BLOCKED
0ms      Thread-6       ⏳ BLOCKED
0ms      Thread-7       ⏳ BLOCKED
0ms      Thread-8       ⏳ BLOCKED
0ms      Thread-9       ⏳ BLOCKED
0ms      Thread-10      ⏳ BLOCKED

                        ⏳ ALL 9 threads WAITING for 1 second...

1000ms   Thread-1       Pool created! Releases LOCK 🔓

1000ms   Thread-2       Acquires LOCK 🔒
                        instance == null? NO
                        return instance
                        Releases LOCK 🔓

                        ❌ Lock was UNNECESSARY — instance already existed!

1001ms   Thread-3       Acquires LOCK 🔒
                        instance == null? NO
                        return instance
                        Releases LOCK 🔓

                        ❌ Lock was UNNECESSARY again!
    * */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(5);
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

public class Method1 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("🚀 Starting 10 threads to access ConnectionPool...\n");

        // Create 10 threads that will try to get connections
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // Step 1: Get the singleton instance (only created once!)
                ConnectionPool pool = ConnectionPool.getInstance();

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
