package SolidPrinciples;

/*
* Need for SOLID Principles in Object-Oriented Design:
Below are some of the main reasons why SOLID principles are important in object-oriented design:
  • Scalability: Adding new features becomes straightforward.
  • Maintainability: Changes in one part of the system have minimal impact on others.
  • Testability: Decoupled designs make unit testing easier.
  • Readability: Clear separation of concerns improves code comprehension.
* */

/*
* This principle states that A class should have only one reason to change which means every class should have a single responsibility or single job or single purpose.
  In other words, a class should have only one job or purpose within the software system.
* */

// violation class
class Booking {
    void createBooking() {
        // booking logic
    }
    void saveToDatabase() {
        // DB logic
    }
    void sendEmail() {
        // email logic
    }
}

// correct method
class BookingService {
    void createBooking() {
        // booking logic
    }
}

class BookingRepository {
    void save() {
        // DB logic
    }
}

class EmailService {
    void sendEmail() {
        // email logic
    }
}

public class SRP {
}
