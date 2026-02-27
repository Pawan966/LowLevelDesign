package SolidPrinciples;

/*
* This principle states that Software entities (classes, modules, functions, etc.)
  should be open for extension, but closed for modification which means you should be able to extend a class behavior, without modifying it.
* */

// violation class
class PaymentService {

    void pay(String type) {
        if (type.equals("CARD")) {
            System.out.println("Card payment");
        } else if (type.equals("UPI")) {
            System.out.println("UPI payment");
        }
    }
}

// correct method
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment");
    }
}

class PaymentServiceCorrect {
    void processPayment(Payment payment) {
        payment.pay();
    }
}

public class OpenClose {
}
