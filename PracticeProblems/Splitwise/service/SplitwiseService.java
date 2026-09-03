package Splitwise.service;

import Splitwise.factory.ExpenseFactory;
import Splitwise.models.ExpenseMetadata;
import Splitwise.models.ExpenseType;
import Splitwise.models.Group;
import Splitwise.models.User;
import Splitwise.models.expenses.Expense;
import Splitwise.models.splits.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SplitwiseService {
    private final UserService userService;
    private final GroupService groupService;

    // Map of user -> (Map of owedUser -> Amount)
    private final Map<String, Map<String, Double>> globalBalanceSheet;

    public SplitwiseService(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
        this.globalBalanceSheet = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        userService.addUser(user);
        globalBalanceSheet.put(user.getId(), new ConcurrentHashMap<>());
    }

    public User getUser(String userId) {
        return userService.getUser(userId);
    }

    public void createGroup(String groupId, String name, String desc) {
        groupService.createGroup(groupId, name, desc);
    }

    public void addUserToGroup(String groupId, String userId) {
        User user = getUser(userId);
        groupService.addUserToGroup(groupId, user);
    }

    public void addExpense(String groupId, ExpenseType type, double amount, String paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        User user = getUser(paidBy);
        Expense expense = ExpenseFactory.createExpense(type, amount, user, splits, expenseMetadata);

        if(!expense.validate()){
            System.out.println("Invalid expense");
            return;
        }

        // validate and add expense to group
        if(groupId != null && groupService.containsGroup(groupId)) {
            Group group = groupService.getGroup(groupId);

            for(Split split : splits) {
                if(!group.getMembers().contains(split.getUser())) {
                    System.out.println("Error: User " + split.getUser().getName() + " is not member of group " + group.getName());
                    return;
                }
            }
            group.addExpense(expense);
        }

        // update global balances
        for(Split split : splits) {
            String paidTo = split.getUser().getId();
            double oweAmount = split.getAmount();

            if(!paidBy.equals(paidTo)) {
                globalBalanceSheet.get(paidBy).compute(paidTo, (k, v) -> (v == null ? 0 : v) +  oweAmount);
                globalBalanceSheet.get(paidTo).compute(paidBy, (k, v) -> (v == null ? 0 : v) -  oweAmount);
            }
        }

        System.out.println("Successfully added expenses");
    }

    public void showBalances() {
        boolean isEmpty = true;
        System.out.println("Global balances");

        for(Map.Entry<String, Map<String, Double>> allBalances : globalBalanceSheet.entrySet()) {
            for(Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if(userBalance.getValue() > 0){
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
        if(isEmpty) System.out.println("Global balances is empty");
    }

    private void printBalance(String user1, String user2, double amount) {
        String name1 = userService.getUser(user1).getName();
        String name2 = userService.getUser(user2).getName();
        System.out.println(name2 + " owes " + name1 + ": $" + Math.abs(amount));
    }
}
