package Creational.AbstractFactory;

public class PostgreSqlQueryExecutor implements  QueryExecutor {
    @Override
    public void execute(String query) {
        System.out.println("PostgreSQL Query: " + query);
    }
}
