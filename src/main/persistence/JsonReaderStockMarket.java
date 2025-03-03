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

//Represents a reader that reads Stock market from JSON data stored in file
public class JsonReaderStockMarket {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReaderStockMarket(String source) {
        this.source = source;
    }

    // EFFECTS: reads stock market from file and returns it;
    public Portfolio read() {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) {
        return "";
    }

    // EFFECTS: parses stockMarket from JSON object and returns it
    private Portfolio parseStockMarket(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: port
    // EFFECTS: parses stocks from JSON Object and adds them to StockMarket
    private void addStocks(ArrayList<Stock> stockMarket, JSONObject jsonObject) {

    }

    // MODIFIES: port
    // EFFECTS: parses stock from JSON object and adds it to stockMarket
    private void addStock(ArrayList<Stock> stockMarket, JSONObject jsonObject) {
        
    }
}
