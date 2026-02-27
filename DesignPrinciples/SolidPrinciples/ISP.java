package SolidPrinciples;

// Clients should not be forced to implement methods they don’t use.

// Violation
interface PaymentProcessor {
    void pay(double amount);
    void refund(double amount);
    void generateInvoice();
    void validateKyc();
}
class CashOnDelivery implements PaymentProcessor {
    public void pay(double amount) {}

    public void refund(double amount) {
        throw new UnsupportedOperationException();
    }

    public void generateInvoice() {}

    public void validateKyc() {
        throw new UnsupportedOperationException();
    }
}

// correct method
interface PaymentISP {
    void pay(double amount);
}

interface RefundableISP {
    void refund(double amount);
}

interface InvoiceGenerator {
    void generateInvoice();
}

interface KycValidator {
    void validateKyc();
}
class CashOnDeliveryCorrect implements PaymentISP, InvoiceGenerator {
    public void pay(double amount) {}
    public void generateInvoice() {}
}

public class ISP {
}
