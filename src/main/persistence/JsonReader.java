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
    public ArrayList<Stock> readStockMarket() throws IOException {
        ArrayList<Stock> stockMarket = new ArrayList<Stock>();
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray stocksArray = jsonObject.getJSONArray("stocks");

        for (int i = 0; i < stocksArray.length(); i++) {
            JSONObject stockJson = stocksArray.getJSONObject(i);
            Stock stock = new Stock(stockJson.getString("name"),
                                    stockJson.getInt("volatility"),
                                    stockJson.getDouble("value"));
            stock.buyShares(stockJson.getInt("shares"));                        
            stockMarket.add(stock);
        }
        return stockMarket;
    }
    
    // EFFECTS: reads portfolio from file and returns it;
    public Portfolio readPortfolio() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        Double initialFunds = jsonObject.getDouble("initialFunds");
        Double funds = jsonObject.getDouble("funds");
        Portfolio port = new Portfolio(initialFunds);
        port.withdrawFunds(initialFunds - funds);
        addStocksPortfolio(port, jsonObject);
        return port;
    }

    // // EFFECTS: parses stockmarket from JSON object and returns it
    // private ArrayList<Stock> parseStockMarket(JSONObject jsonObject) {
    //     ArrayList<Stock> stockMarket = new ArrayList<Stock>();
    //     JSONArray jsonArray = jsonObject.getJSONArray("stocks");
    //     for (Object json : jsonArray) {
    //         JSONObject nextStock = (JSONObject) json;
    //         addStockStockMarket(stockMarket, jsonObject);
    //     }
    //     return stockMarket;
    // }

    // MODIFIES: port
    // EFFECTS: parses stocks from JSON Object and adds them to Portfolio
    private void addStocksPortfolio(Portfolio port, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stocks");
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStockPortfolio(port, nextStock);
        }
    }

    // MODIFIES: stockMarket
    // EFFECTS: parses stocks from JSON Object and adds them to stockMarket
    //private void addStocksStockMarket(ArrayList<Stock> stockMarket, JSONObject jsonObject) {
    //
    //}

    // MODIFIES: port
    // EFFECTS: parses stock from JSON object and adds it to portfolio
    private void addStockPortfolio(Portfolio port, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int vol = jsonObject.getInt("volatility");
        double value = jsonObject.getDouble("value");
        int shares = jsonObject.getInt("shares");
        Stock stock = new Stock(name, vol, value);
        port.buyShares(shares, stock);
    }

    // // MODIFIES: stockMarket
    // // EFFECTS: parses stock from JSON object and adds it to stockMarket
    // private void addStockStockMarket(ArrayList<Stock> stockMarket, JSONObject jsonObject) {
    //     String name = jsonObject.getString("name");
    //     int vol = jsonObject.getInt("volatility");
    //     double value = jsonObject.getDouble("value");
    //     int shares = jsonObject.getInt("shares");
    //     Stock stock = new Stock(name, vol, value);
    //     stockMarket.add(stock);
    // }
}
