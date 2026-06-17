package Creational.Builder;

public class Invoice {

    // All fields final — immutable object
    private final int id;
    private final String vendorName;
    private final double amount;
    private final String currency;
    private final String dueDate;
    private final boolean isPaid;
    private final String paymentTerms;

    // Private constructor — only Builder can create
    private Invoice(Builder builder) {
        this.id          = builder.id;
        this.vendorName  = builder.vendorName;
        this.amount      = builder.amount;
        this.currency    = builder.currency;
        this.dueDate     = builder.dueDate;
        this.isPaid      = builder.isPaid;
        this.paymentTerms = builder.paymentTerms;
    }

    // Getters only (no setters — immutable)
    public int getId()             { return id; }
    public String getVendorName()  { return vendorName; }
    public double getAmount()      { return amount; }
    public String getCurrency()    { return currency; }
    public String getDueDate()     { return dueDate; }
    public boolean isPaid()        { return isPaid; }
    public String getPaymentTerms(){ return paymentTerms; }

    // ===== Static Builder Class =====
    public static class Builder {

        // Required fields
        private final int id;
        private final String vendorName;

        // Optional fields with defaults
        private double amount    = 0.0;
        private String currency  = "USD";
        private String dueDate   = "";
        private boolean isPaid   = false;
        private String paymentTerms = "NET30";

        private Builder(int id, String vendorName) {
            this.id = id;
            this.vendorName = vendorName;
        }

        // Constructor takes only REQUIRED fields
        public static Builder getInstance(int id, String vendorName) {
            return new Builder(id, vendorName);
        }

        // Each setter returns 'this' for chaining
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder isPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Builder paymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
            return this;
        }

        // Validation + build
        public Invoice build() {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }
            if (vendorName == null || vendorName.isBlank()) {
                throw new IllegalArgumentException("Vendor name is required");
            }
            return new Invoice(this);
        }
    }
}

