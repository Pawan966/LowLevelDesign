package Splitwise;

import Splitwise.models.ExpenseMetadata;
import Splitwise.models.ExpenseType;
import Splitwise.models.User;
import Splitwise.models.splits.EqualSplit;
import Splitwise.models.splits.ExactSplit;
import Splitwise.models.splits.Split;
import Splitwise.service.GroupService;
import Splitwise.service.SplitwiseService;
import Splitwise.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseApplication {
    public static void main(String[] args) {
        SplitwiseService service = new SplitwiseService(new UserService(), new GroupService());

        service.addUser(new User("u1", "Alice", "alice@test.com"));
        service.addUser(new User("u2", "Bob", "bob@test.com"));
        service.addUser(new User("u3", "Charlie", "charlie@test.com"));
        service.addUser(new User("u4", "David", "david@test.com"));

        service.createGroup("g1", "Goa Trip", "New Year Trip");
        service.addUserToGroup("g1", "u1");
        service.addUserToGroup("g1", "u2");
        service.addUserToGroup("g1", "u3");

        // scenario A: Alice pays 900, split equally
        List<Split> groupSplits = new ArrayList<>();
        groupSplits.add(new EqualSplit(service.getUser("u1")));
        groupSplits.add(new EqualSplit(service.getUser("u2")));
        groupSplits.add(new EqualSplit(service.getUser("u3")));
        service.addExpense("g1", ExpenseType.EQUAL, 900, "u1", groupSplits, new ExpenseMetadata("Hotel Booking", null, "Goa"));

        // scenario B: Bob pays 500, exact for Bob and Charlie
        List<Split> expenseSplits = new ArrayList<>();
        expenseSplits.add(new ExactSplit(service.getUser("u2"), 100));
        expenseSplits.add(new ExactSplit(service.getUser("u3"), 400));
        service.addExpense("g1", ExpenseType.EXACT, 500, "u2",  expenseSplits, new ExpenseMetadata("Food", null, "Goa"));

        service.showBalances();
    }
}
