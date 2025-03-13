package testPersistence;

import model.Stock;
import persistence.JsonReader;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderPortfolioTest {

    @Test
    void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        Portfolio portfolio = reader.readPortfolio();
        assertEquals(0, portfolio.getStocks().size());
        assertEquals(100, portfolio.getFunds());
    }

    @Test
    void testReaderGeneralPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        Portfolio portfolio = reader.readPortfolio();
        assertEquals(2, portfolio.getStocks().size());
        assertEquals(5, portfolio.getStocks().get(1).getShares());
        assertEquals(248.0, portfolio.getStocks().get(1).getValue());
    }

    @Test
    void testReaderNewStockMarket() {
        JsonReader reader = new JsonReader("./data/testReaderNewStockMarket.json");
        ArrayList<Stock> stockMarket = reader.readStockMarket();
        assertEquals(5, stockMarket.size());
        assertEquals("amd", stockMarket.get(3).getName());
        assertEquals(60, stockMarket.get(3).getValue());
        assertEquals("apple", stockMarket.get(0).getName());
        assertEquals("edr", stockMarket.get(4).getName());

    }

    @Test
    void testReaderGeneralStockMarket() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStockMarket.json");
        ArrayList<Stock> stockMarket = reader.readStockMarket();
        assertEquals(5, stockMarket.size());
        assertEquals("amd", stockMarket.get(3).getName());
        assertEquals(25.0, stockMarket.get(3).getValue());
        assertEquals("apple", stockMarket.get(0).getName());
        assertEquals(1, stockMarket.get(0).getVolatility());
        assertEquals("edr", stockMarket.get(4).getName());
        assertEquals(10, stockMarket.get(4).getShares());
    }
}