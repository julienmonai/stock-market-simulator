package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestStock {
    private Stock apple;
    private Stock micro;
    private Stock tsla;
    private Stock amd;

    @BeforeEach
    void setUp() {
        apple = new Stock("apple", 1, 120);
        micro = new Stock("micro", 2, 250);
        tsla = new Stock("tsla", 3, 400);
        amd = new Stock("amd", 2, 60);
    }

    
    @Test
    void testConstructor() {
        Stock testStock = new Stock("xyz", 2, 230);
        assertEquals("xyz", testStock.getName());
        assertEquals(2, testStock.getVolatility());
        assertEquals(230, testStock.getValue(), 0.01);
        
    }
    
    @Test
    void testBuyShares() {
        assertEquals(0, apple.getShares());
        apple.buyShares(400);
        assertEquals(400, apple.getShares());
        apple.buyShares(200);
        assertEquals(600, apple.getShares());
        apple.buyShares(20);
        assertEquals(620, apple.getShares());
        apple.buyShares(1);
        assertEquals(621, apple.getShares());
    }

    @Test
    void testSellShares() {
        apple.buyShares(400);
        assertEquals(400, apple.getShares());
        apple.sellShares(200);
        assertEquals(200, apple.getShares());
        apple.sellShares(1);
        assertEquals(199, apple.getShares());
        apple.sellShares(199);
        assertEquals(0, apple.getShares());
    }

    @Test
    void testUpdateValueLowVolatility() {
        double max = (1.025 * apple.getValue());
        double min = (0.975 * apple.getValue());
        apple.updateValue();
        assertTrue(max > apple.getValue() && apple.getValue() > min);
        double max2 = (1.025 * apple.getValue());
        double min2 = (0.975 * apple.getValue());
        apple.updateValue();
        assertTrue(max2 > apple.getValue() && apple.getValue() > min2);
    }

    @Test
    void testUpdateValueMidVolatility() {
        double max = (1.1 * micro.getValue());
        double min = (0.9 * micro.getValue());
        micro.updateValue();
        assertTrue(max > micro.getValue() && micro.getValue() > min);
        double max2 = (1.1 * micro.getValue());
        double min2 = (0.9 * micro.getValue());
        micro.updateValue();
        assertTrue(max2 > micro.getValue() && micro.getValue() > min2);
    }

    @Test
    void testUpdateValueHighVolatility() {
        double max = (1.2 * tsla.getValue());
        double min = (0.8 * tsla.getValue());
        tsla.updateValue();
        assertTrue(max > tsla.getValue() && tsla.getValue() > min);
        double max2 = (1.2 * tsla.getValue());
        double min2 = (0.8 * tsla.getValue());
        tsla.updateValue();
        assertTrue(max2 > tsla.getValue() && tsla.getValue() > min2);
    }
}
