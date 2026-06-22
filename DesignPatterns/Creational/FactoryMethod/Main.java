package Creational.FactoryMethod;

/* It provides a interface for creating objects without specifying there concrete classes.

   Superclass (abstract class or interface) defines a method for creating an object.
   At compile time which class object is needed is not known.
*/

/*
* To implement factory method patter, there are two ways:
* 1. Single factory class to create different objects -> use when subclasses are limited
* 2. Separate factory classes to create different objects -> preferred as it follows open close principle.
* */
public class Main {
    public static void main(String[] args) {
        Notification emailNotification = new EmailNotificationFactory().createNotification();
        emailNotification.sendNotification("Hello World");
    }
}
