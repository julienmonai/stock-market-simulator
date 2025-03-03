package persistence;
import model.Portfolio;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of portfolio to file
public class JsonWriterPortfolio {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterPortfolio(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer
    public void open() {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of portfolio to file
    public void write(Portfolio port) {

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {

    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
