package persistence;

import model.Stock;
import model.Portfolio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads portfolio from JSON data stored in file
public class JsonReaderPortfolio {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReaderPortfolio(String source) {
        this.source = source;
    }

    // EFFECTS: reads portfolio from file and returns it;
    public Portfolio read() {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) {
        return "";
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: port
    // EFFECTS: parses stocks from JSON Object and adds them to Portfolio
    private void addStocks(Portfolio port, JSONObject jsonObject) {

    }

    // MODIFIES: port
    // EFFECTS: parses stock from JSON object and adds it to portfolio
    private void addStock(Portfolio port, JSONObject jsonObject) {

    }
}
