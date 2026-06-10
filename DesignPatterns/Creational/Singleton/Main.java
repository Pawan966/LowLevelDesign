package Creational.Singleton;

// Singleton pattern make sure only instance of a class is created.

/*
* Where to use:
* If you have a resource that will be accessed from multiple parts of your code like log file,
* then you should use singleton.
*
* Use case:
* In the logging system, the logger class should be singleton.
* */
public class Main {
    public static  void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("This is a test log message");
    }
}
