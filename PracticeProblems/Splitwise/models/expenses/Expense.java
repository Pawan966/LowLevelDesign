package Splitwise.models.expenses;

import Splitwise.models.ExpenseMetadata;
import Splitwise.models.User;
import Splitwise.models.splits.Split;

import java.util.List;

public abstract class Expense {
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final ExpenseMetadata expenseMetadata;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseMetadata = expenseMetadata;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public abstract boolean validate();
}

