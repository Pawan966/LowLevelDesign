package Structural.Facade;

// Facade class - provides simplified interface to complex subsystems
public class HomeTheaterFacade {
    private final Amplifier amplifier;
    private final DVDPlayer dvdPlayer;
    private final Projector projector;
    private final Lights lights;

    public HomeTheaterFacade(Amplifier amp, DVDPlayer dvd, Projector proj, Lights lights) {
        this.amplifier = amp;
        this.dvdPlayer = dvd;
        this.projector = proj;
        this.lights = lights;
    }

    // Simplified method to watch a movie - hides complexity of multiple subsystems
    public void watchMovie(String movie) {
        System.out.println("\n=== Get ready to watch a movie... ===");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setSurroundSound();
        amplifier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
        System.out.println("=== Enjoy your movie! ===\n");
    }

    // Simplified method to end the movie
    public void endMovie() {
        System.out.println("\n=== Shutting down movie theater... ===");
        dvdPlayer.stop();
        dvdPlayer.off();
        amplifier.off();
        projector.off();
        lights.on();
        System.out.println("=== Movie theater is off ===\n");
    }
}
