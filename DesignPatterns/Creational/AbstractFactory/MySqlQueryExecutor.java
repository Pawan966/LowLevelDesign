package Creational.AbstractFactory;

public class MySqlQueryExecutor implements  QueryExecutor {
    @Override
    public void execute(String query) {
        System.out.println("Executing MySQL query: " + query);
    }
}
