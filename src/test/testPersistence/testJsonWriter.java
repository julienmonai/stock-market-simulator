package testPersistence;

import model.Stock;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class testJsonWriter{

    @Test
    void testWriterEmptyPortfolio() {
        Portfolio port = new Portfolio(100);
        JsonWriter writer = new JsonWriter("./data/testWriterNewPortfolio.Json");
        writer.open();
        writer.writePortfolio(port);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterNewPortfolio.Json");
        port = reader.readPortfolio();
        assertEquals(0, port.getStocks().size());
        assertEquals(100, port.getFunds());
    }

    @Test
    void testWriterNewStockMarket() {
        ArrayList<Stock> stockMarket = new ArrayList<Stock>();
        // init stockMarket
        Stock apple = new Stock("apple", 1, 120);
        Stock micro = new Stock("micro", 2, 250);
        Stock tsla = new Stock("tsla", 3, 400);
        Stock amd = new Stock("amd", 2, 60);
        Stock edr = new Stock("edr", 2, 30);
        stockMarket.add(apple);
        stockMarket.add(micro);
        stockMarket.add(tsla);
        stockMarket.add(amd);
        stockMarket.add(edr);
        //
        JsonWriter writer = new JsonWriter("./data/testWriterNewStockMarket.Json");
        writer.open();
        writer.writeStockMarket(stockMarket);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterNewStockMarket.Json");
        stockMarket = reader.readStockMarket();
        assertEquals(5, stockMarket.size());
        assertEquals("amd", stockMarket.get(3).getName());
        assertEquals(60, stockMarket.get(3).getValue());
        assertEquals("apple", stockMarket.get(0).getName());
        assertEquals("edr", stockMarket.get(4).getName());
    }

    @Test
    void testWriterGeneralPortfolio() {
        Portfolio port = new Portfolio(2000000);
        // init stocks
        Stock apple = new Stock("apple", 1, 120);
        Stock micro = new Stock("micro", 2, 250);
        Stock tsla = new Stock("tsla", 3, 400);
        Stock amd = new Stock("amd", 2, 60);
        Stock edr = new Stock("edr", 2, 30);
        //
        edr.setValue(201.5);
        port.buyShares(100, edr);
        tsla.setValue(104.3);
        port.buyShares(100, tsla);
        port.buyShares(1, amd);
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.Json");
        writer.open();
        writer.writePortfolio(port);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.Json");
        port = reader.readPortfolio();
        assertEquals(3, port.getStocks().size());
        assertEquals(2000000, port.getInitialFunds());
        assertEquals(60, port.getStocks().get(2).getValue());
        assertEquals(1, port.getStocks().get(2).getShares());
    }

    @Test
    void testWriterGeneralStockMarket() {
        ArrayList<Stock> stockMarket = new ArrayList<Stock>();
        Portfolio port = new Portfolio(200000);
        // init stockMarket
        Stock apple = new Stock("apple", 1, 1500);
        Stock micro = new Stock("micro", 2, 12);
        Stock tsla = new Stock("tsla", 3, 20.5);
        Stock amd = new Stock("amd", 2, 50);
        Stock edr = new Stock("edr", 2, 20);
        stockMarket.add(apple);
        stockMarket.add(micro);
        stockMarket.add(tsla);
        stockMarket.add(amd);
        stockMarket.add(edr);
        //
        edr.setValue(201.5);
        port.buyShares(100, edr);
        tsla.setValue(104.3);
        port.buyShares(100, tsla);
        port.buyShares(1, amd);


        JsonWriter writer = new JsonWriter("./data/testWriterGeneralStockMarket.Json");
        writer.open();
        writer.writeStockMarket(stockMarket);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterGeneralStockMarket.Json");
        stockMarket = reader.readStockMarket();
        assertEquals(5, stockMarket.size());
        assertEquals(1, stockMarket.get(4).getShares());
        assertEquals(104.3, stockMarket.get(2).getValue());
    }
}
