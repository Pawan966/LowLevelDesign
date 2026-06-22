package Creational.AbstractFactory;

public class DatabaseClient {
    private final Connection connection;
    private final QueryExecutor queryExecutor;

    public DatabaseClient(DatabaseFactory factory) {
        this.connection = factory.createConnection();
        this.queryExecutor = factory.createQueryExecutor();
    }

    public void executeQuery(String query) {
        connection.connect();
        queryExecutor.execute(query);
        connection.disconnect();
    }
}
