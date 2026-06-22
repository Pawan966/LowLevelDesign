package Creational.AbstractFactory;

public class MySqlFactory implements  DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new MySqlConnection();
    }

    @Override
    public QueryExecutor createQueryExecutor() {
        return new MySqlQueryExecutor();
    }
}
