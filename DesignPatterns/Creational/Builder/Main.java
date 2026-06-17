package Creational.Builder;

// Builder Pattern is a way to create complex objects step by step, in a readable and controlled way.

/* Where to use:
   When we have a complex object and we don't want to initialize all the fields in one go then we can use builder pattern.
   Why not use other ways:
   1. Constructor overloading would create too many constructors.
   2. Using setter methods will make the object mutable.
 */
public class Main {
    public static void main(String[] args) {
        Invoice invoice = Invoice.Builder.getInstance(1, "Google")
                .amount(100.0)
                .currency("USD")
                .dueDate("2025-01-01")
                .build();
    }
}
