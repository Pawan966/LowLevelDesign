package Creational.AbstractFactory;

public class MySqlConnection implements  Connection {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnected from MySQL database");
    }
}
