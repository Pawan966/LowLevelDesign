package Structural.Adapter;

// Allows objects with incompatible interfaces to work together.

/*
* Use it when:
* 1. You want to use an existing class, but its interface is not compatible with the rest of your code.
* 2. you are using 3rd party library and it's interface is not compatible with your code.
* */
public class Main {
    public  static void main(String[] args) {
        Logger logger = new FileLoggerAdapter(new FileLogger());
        logger.log("This is a test log message");
    }
}
