package persistence;
import org.json.JSONObject;

import model.Stock;

import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representation of StockMarket to file
public class JsonWriterStockMarket {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterStockMarket(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer
    public void open() {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of portfolio to file
    public void write(ArrayList<Stock> stockMarket) {

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
