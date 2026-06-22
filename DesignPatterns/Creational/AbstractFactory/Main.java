package Creational.AbstractFactory;

// It provides an interface to create families of related objects without specifying their concrete classes.
// It ensures that the objects created are compatible
public class Main {
    public static void main(String[] args) {
        DatabaseClient client = new DatabaseClient(new MySqlFactory());
        client.executeQuery("SELECT * FROM users");
    }
}
