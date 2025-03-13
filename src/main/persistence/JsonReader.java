package persistence;

import model.Stock;
import model.Portfolio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads portfolio from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads stockMarket from file and returns it;
    public ArrayList<Stock> readStockMarket() {
        return null;
    }
    
    // EFFECTS: reads portfolio from file and returns it;
    public Portfolio readPortfolio() {
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

    // EFFECTS: parses stockmarket from JSON object and returns it
    private ArrayList<Stock> parseStockMarket(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: port
    // EFFECTS: parses stocks from JSON Object and adds them to Portfolio
    private void addStocksPortfolio(Portfolio port, JSONObject jsonObject) {

    }

    // MODIFIES: stockMarket
    // EFFECTS: parses stocks from JSON Object and adds them to stockMarket
    private void addStocksStockMarket(ArrayList<Stock> stockMarket, JSONObject jsonObject) {

    }

    // MODIFIES: port
    // EFFECTS: parses stock from JSON object and adds it to portfolio
    private void addStockPortfolio(Portfolio port, JSONObject jsonObject) {

    }

    // MODIFIES: stockMarket
    // EFFECTS: parses stock from JSON object and adds it to stockMarket
    private void addStockStockMarket(ArrayList<Stock> stockMarket, JSONObject jsonObject) {

    }
}
