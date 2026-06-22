package Creational.AbstractFactory;

public interface DatabaseFactory {
    Connection createConnection();
    QueryExecutor createQueryExecutor();
}
