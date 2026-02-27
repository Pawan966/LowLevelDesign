package SolidPrinciples;

// Depend on abstractions, not on concrete classes.

// violation
class MySQLDatabase {
    void save() {
        System.out.println("Saving to MySQL");
    }
}

class BookingServiceWrong {
    private MySQLDatabase db = new MySQLDatabase();

    void saveBooking() {
        db.save();
    }
}

// correct method
interface Database {
    void save();
}

class MySQLDatabaseCorrect implements Database {
    public void save() {
        System.out.println("Saving to MySQL");
    }
}

class MongoDatabase implements Database {
    public void save() {
        System.out.println("Saving to Mongo");
    }
}

class BookingServiceCorrect {

    private Database db;

    public BookingServiceCorrect(Database db) {
        this.db = db;
    }

    void saveBooking() {
        db.save();
    }
}

public class DIP {
}
