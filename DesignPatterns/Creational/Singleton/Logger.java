package Creational.Singleton;

public class Logger {
    /* Way1: here jvm takes care that only one instance is created upon class loading
       But problem is Instance created even if never used (wasteful) -> not lazy initialization
    */
    private static final Logger INSTANCE = new Logger();

    private static volatile Logger instance;
    private Logger(){}

    /* way2: This is not a thread safe way to implement singleton
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    */


    /* way3: This is a thread safe way to implement singleton but every call is synchronized → slow performance
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    */

    /* way4: This is a double-checked locking mechanism which is thread safe and also lazy initialized.
       It has synchronization overhead
    * public static Logger getInstance() {
        if (instance == null) {                  // First check (no lock)
            synchronized (Logger.class) {
                if (instance == null) {          // Second check (with lock)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    */

    /* way5: Bill Pugh (Static Inner Class)
    * Loaded only when getInstance() is called -> Thread-safe (JVM handles class loading)
    * Since this is a static class it will be initialized only once and will be thread safe.
    */
    private static class SingletonHolder {
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void log(String message) {
        System.out.println("Logging: " + message);
    }
}
