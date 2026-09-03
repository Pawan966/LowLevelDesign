package Splitwise.factory;

import Splitwise.models.ExpenseMetadata;
import Splitwise.models.ExpenseType;
import Splitwise.models.User;
import Splitwise.models.expenses.EqualExpense;
import Splitwise.models.expenses.ExactExpense;
import Splitwise.models.expenses.Expense;
import Splitwise.models.expenses.PercentExpense;
import Splitwise.models.splits.PercentSplit;
import Splitwise.models.splits.Split;

import java.util.List;

public class ExpenseFactory {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        switch (expenseType) {
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double)Math.round(amount * 100 /  totalSplits)) / 100.0;
                for(Split split : splits) {
                    split.setAmount(splitAmount);
                }
                splits.getFirst().setAmount(splitAmount + (amount - splitAmount * totalSplits));
                return new EqualExpense(amount, paidBy, splits, expenseMetadata);

            case EXACT:
                return new ExactExpense(amount, paidBy, splits, expenseMetadata);

            case PERCENT:
                for(Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit)split;
                    split.setAmount((amount * percentSplit.getPercent()) / 100.0);
                }
                return new PercentExpense(amount, paidBy, splits, expenseMetadata);

            default:
                throw new IllegalArgumentException("Invalid expense type");
        }
    }
}
