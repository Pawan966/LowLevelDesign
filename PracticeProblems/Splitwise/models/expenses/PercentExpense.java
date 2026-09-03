package Splitwise.models.expenses;

import Splitwise.models.ExpenseMetadata;
import Splitwise.models.User;
import Splitwise.models.splits.PercentSplit;
import Splitwise.models.splits.Split;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        double totalPercent = 0;
        for(Split split:getSplits()){
            if(!(split instanceof PercentSplit)) return false;
            totalPercent += ((PercentSplit)split).getPercent();
        }
        return Math.abs(100.0 - totalPercent) < 0.0001;
    }
}
