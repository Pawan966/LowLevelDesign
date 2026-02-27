package SolidPrinciples;

// A child class must be usable wherever parent class is used without breaking behavior.

// violation class
class PaymentWrong {
    void refund(double amount) {
        System.out.println("Refunding amount: " + amount);
    }
}

class CreditCardPayment extends PaymentWrong {
    void refund(double amount) {
        System.out.println("Refunding to credit card");
    }
}

class NonRefundablePayment extends PaymentWrong {
    void refund(double amount) {
        throw new UnsupportedOperationException("Refund not allowed");
    }
}

// correct method
class PaymentCorrect {
    void pay(double amount) {}
}
interface Refundable {
    void refund(double amount);
}

class CreditCardPaymentCorrect extends PaymentCorrect implements Refundable {
    public void refund(double amount) {
        System.out.println("Refunding to credit card");
    }
}

class NonRefundablePaymentCorrect extends PaymentCorrect {
}

public class LSP {
    public static void main(String[] args) {

    }
    void processRefund(Refundable payment) {
        payment.refund(1000);
    }
}
