package Creational.AbstractFactory;

public class PostgreSqlFactory implements DatabaseFactory{
    @Override
    public Connection createConnection() {
        return new PostgreSqlConnection();
    }

    @Override
    public QueryExecutor createQueryExecutor() {
        return new PostgreSqlQueryExecutor();
    }
}
