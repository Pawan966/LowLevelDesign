package Creational;

/*
 * Telescoping Constructor Pattern: Immutability
 * Problem with telescoping constructor pattern:
 *   1. It leads to a lot of constructors with different parameters.
 *   2. It leads Parameter Confusion
 *   3. Adding ONE new field = modifying ALL constructors
 *   4. 4 optional fields = theoretically 2^4 = 16 constructor combinations
 *
 * Setter method: better readability
 * Problem with setter method:
 *   1. Object is in an incomplete/invalid state between new and the last set()
 *   2. Anyone can call any setter method later and change a finalized object -> no immutability
 *   3. You can forget to set some field and only discover it at runtime -> No Validation at Build Time
 * */

// Creating a Bank Account -> An account must never exist in an invalid state. If validation fails, the object should never be created.

import java.util.ArrayList;
import java.util.List;

// final here is optional, if you want immutability then use final else not
final class BankAccount {

    // these fields are not optional - they're all declared as final, which means they must be
    // initialized when the object is constructed, and should remain immutable.
    private final String accountNumber;
    private final String ifscCode;
    private final String holderName;
    private final double balance;
    private final String accountType;

    private BankAccount(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.ifscCode = builder.ifscCode;
        this.holderName = builder.holderName;
        this.balance = builder.balance;
        this.accountType = builder.accountType;
    }

    // Only getters — no setters
    public String getIfscCode() {
        return ifscCode;
    }
    // ... other getters

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private String accountNumber;
        private String ifscCode;
        private String holderName;
        private double balance;
        private String accountType;

        public Builder accountNumber(String val) {
            this.accountNumber = val;
            return this;
        }

        public Builder ifscCode(String val) {
            this.ifscCode = val;
            return this;
        }

        public Builder holderName(String val) {
            this.holderName = val;
            return this;
        }

        public Builder balance(double val) {
            this.balance = val;
            return this;
        }

        public Builder accountType(String val) {
            this.accountType = val;
            return this;
        }

        // 🔒 ALL validation happens HERE — before object creation
        public BankAccount build() {

            List<String> errors = new ArrayList<>();

            if (accountNumber == null || accountNumber.isBlank())
                errors.add("Account number is required");

            if (ifscCode == null || !ifscCode.matches("^[A-Z]{4}0[A-Z0-9]{6}$"))
                errors.add("Valid IFSC code is required (e.g., SBIN0001234)");

            if (holderName == null || holderName.isBlank())
                errors.add("Holder name is required");

            if (balance < 0)
                errors.add("Balance cannot be negative");

            if (accountType == null ||
                    (!accountType.equals("SAVINGS") && !accountType.equals("CURRENT")))
                errors.add("Account type must be SAVINGS or CURRENT");

            if (accountType != null && accountType.equals("CURRENT") && balance < 10000)
                errors.add("Current account requires minimum balance of 10000");

            // ❌ If ANY validation fails → object is NEVER created
            if (!errors.isEmpty()) {
                throw new IllegalStateException(
                        "Cannot create BankAccount:\n  - " +
                                String.join("\n  - ", errors)
                );
            }

            return new BankAccount(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        // ❌ ATTEMPT 1: Current account with low balance + bad IFSC
        try {
            BankAccount bad = BankAccount.builder()
                    .accountNumber("987654321")
                    .holderName("Tekion India")
                    .ifscCode("INVALID")
                    .balance(500)
                    .accountType("CURRENT")
                    .build();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("─────────────────────────────");

        // ✅ ATTEMPT 2: Valid account
        BankAccount good = BankAccount.builder()
                .accountNumber("123456789")
                .holderName("Tekion Corp")
                .ifscCode("SBIN0001234")
                .balance(50000)
                .accountType("CURRENT")
                .build();

        System.out.println("✅ Account created: " + good.getIfscCode());
    }
}
