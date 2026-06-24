package Structural.Adapter;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {
    public void writeToFile(String message) {
        try(FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
