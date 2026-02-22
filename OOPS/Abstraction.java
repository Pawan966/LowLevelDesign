
/*
* Abstraction in java can be achieved using abstract class and interface.
* Use abstract class when you want to maintain some state/common functionality between your related classes
* and use interface when you want to define a contract for unrelated classes.
* */

interface AnimalKingdom {
    void sound();
    /*
        default methods are introduced in java 8 to provide default implementation to the interface methods in order
        to maintain backward compatibility.
        For example: forEach is a default method in Iterable interface.
                     This iterable interface is the parent interface of Collection interface and existed since java 1.2.
    */
    default void commonMethod() {
        System.out.println("Animal common method.");
    }
}

interface Pet {
    void play();
    default void commonMethod() {
        System.out.println("Pet common method.");
    }
}

class DogAnimal implements AnimalKingdom, Pet {
    @Override
    public void sound() {
        System.out.println("Dog barks.");
    }
    @Override
    public void play() {
        System.out.println("Dog plays fetch.");
    }
    @Override
    public void commonMethod() {
        System.out.println("Dog's own common method.");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        DogAnimal dog = new DogAnimal();
        dog.sound();
        dog.play();
        dog.commonMethod();
    }
}
