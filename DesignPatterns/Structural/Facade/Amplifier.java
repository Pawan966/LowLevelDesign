package Structural.Facade;

// Subsystem class
public class Amplifier {
    public void on() {
        System.out.println("Amplifier: Turning on");
    }

    public void off() {
        System.out.println("Amplifier: Turning off");
    }

    public void setVolume(int level) {
        System.out.println("Amplifier: Setting volume to " + level);
    }

    public void setSurroundSound() {
        System.out.println("Amplifier: Setting surround sound mode");
    }
}
