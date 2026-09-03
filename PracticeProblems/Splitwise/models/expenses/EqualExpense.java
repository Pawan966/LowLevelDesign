package Splitwise.models.expenses;

import Splitwise.models.ExpenseMetadata;
import Splitwise.models.User;
import Splitwise.models.splits.EqualSplit;
import Splitwise.models.splits.Split;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        double totalSplitAmount = 0;

        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) return false;
            totalSplitAmount += split.getAmount();
        }

        return Math.abs(getAmount() - totalSplitAmount) < 0.0001;
    }
}
