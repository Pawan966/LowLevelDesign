
/*
* Make fields private and provide helper methods to access them.
* This ensures security and control over how data is accessed and modified.
* */

class Account{
    private String accName;
    private int amount;

    public Account(String accName){
        this.accName = accName;
    }

    public String getAccName(){
        return this.accName;
    }

    public int getAmount(){
        return this.amount;
    }

    public void deposit(int amount){
        if(amount > 0){
            this.amount += amount;
        }
    }

    public void withdraw(int amount){
        if(amount > 0 && this.amount >= amount){
            this.amount -= amount;
        }
    }
}
public class Encapsulation {
    public static void main(String[] args) {
        Account acc = new Account("Bank-1");
        acc.deposit(100);
        acc.withdraw(50);
        System.out.println(acc.getAccName() + " " + acc.getAmount());
    }
}
