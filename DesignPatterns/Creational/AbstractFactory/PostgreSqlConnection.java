package Creational.AbstractFactory;

public class PostgreSqlConnection implements  Connection {
    @Override
    public void connect() {
        System.out.println("PostgreSQL connection started");
    }

    @Override
    public void disconnect() {
        System.out.println("PostgreSQL connection closed");
    }
}
