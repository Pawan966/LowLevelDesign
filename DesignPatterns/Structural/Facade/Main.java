package Structural.Facade;

// Provides a simplified interface to complex system of classes, libraries, or frameworks.

/*
* Use when:
* 1. You want to simplify client code complexity
* 2. You want to decouple the client code with the sub-systems
* */
public class Main {
    public static void main(String[] args) {
        // Create subsystem components
        Amplifier amp = new Amplifier();
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();

        // Create facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector, lights);

        // Without facade, client would need to call all these methods:
        // lights.dim(10);
        // projector.on();
        // projector.wideScreenMode();
        // amplifier.on();
        // amplifier.setSurroundSound();
        // amplifier.setVolume(5);
        // dvdPlayer.on();
        // dvdPlayer.play("Inception");

        // With facade - simple one-line call!
        homeTheater.watchMovie("Inception");

        // Some time passes...
        System.out.println("... watching the movie ...\n");

        // End the movie with one simple call
        homeTheater.endMovie();
    }
}
