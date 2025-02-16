package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPortfolio {
    private Portfolio portfolio;
    private Stock apple;
    private Stock micro;
    private Stock tsla;
    private Stock amd;
    
    @BeforeEach
    void runBefore() {
        portfolio = new Portfolio(50000);
        apple = new Stock("apple", 1, 120);
        micro = new Stock("micro", 2, 250);
        tsla = new Stock("tsla", 3, 400);
        amd = new Stock("amd", 2, 60);
    }

    @Test
    void testConstructor() {
        assertEquals(0, portfolio.getStocks().size());
        assertEquals(50000, portfolio.getFunds(), 0.01);
    }

    @Test
    void testGetTotalValue() {
        //with no stocks
        assertEquals(50000, portfolio.getTotalValue(), 0.01);
        portfolio.buyShares(100, apple);
        //with funds and one stock
        assertEquals(50000, portfolio.getTotalValue(), 0.01);
        portfolio.addFunds(100000);
        //with more funds than just initial and one stock
        assertEquals(150000, portfolio.getTotalValue(), 0.01);
        portfolio.withdrawFunds(138000);
        //with no funds and one stock
        assertEquals(12000, portfolio.getTotalValue(), 0.01);
        portfolio.addFunds(138000);
        portfolio.buyShares(1000, amd);
        portfolio.withdrawFunds(78000);
        //with no funds and 2 stocks
        assertEquals(72000, portfolio.getTotalValue(), 0.01);
        portfolio.addFunds(100000);
        portfolio.buyShares(100, tsla);
        portfolio.withdrawFunds(60000);
        //with no funds and 3 stocks
        assertEquals(112000, portfolio.getTotalValue(), 0.01);
        portfolio.addFunds(1);
        //with funds and 3 stocks
        assertEquals(112001, portfolio.getTotalValue(), 0.01);
    }

    @Test
    void testBuyShares() {
        //one share one stock
        portfolio.buyShares(1, amd);
        ArrayList<Stock> test = new ArrayList<Stock>();
        assertEquals(50000, portfolio.getTotalValue(), 0.01);
        assertEquals(1, amd.getShares());
        assertEquals(test, portfolio.getStocks());
        assertEquals(49940, portfolio.getFunds(), 0.01);
        portfolio.buyShares(100, amd);
        //multiple shares second transaction, one stock
        assertEquals(50000, portfolio.getTotalValue(), 0.01);
        assertEquals(101, amd.getShares());
        assertEquals(test, portfolio.getStocks());
        assertEquals(43940, portfolio.getFunds(), 0.01);
        portfolio.buyShares(75, tsla);
        //multiple shares third transaction, 3 stocks
        test.add(tsla);
        assertEquals(50000, portfolio.getTotalValue(), 0.01);
        assertEquals(75, tsla.getShares());
        assertEquals(test, portfolio.getStocks());
        assertEquals(13940, portfolio.getFunds(), 0.01);
    }

    @Test
    void testSellShares() {
        ArrayList<Stock> test = new ArrayList<Stock>();
        test.add(amd);
        portfolio.buyShares(100, amd);
        portfolio.sellShares(25, amd);
        // one stock portion sold, funds increase
        assertEquals(45500, portfolio.getFunds(), 0.01);
        assertEquals(test, portfolio.getStocks());
        assertEquals(75, amd.getShares());
        // 2 stocks, one partial and other added then removed
        portfolio.buyShares(10, tsla);
        test.add(tsla);
        assertEquals(test, portfolio.getStocks());
        portfolio.sellShares(10, tsla);
        test.remove(tsla);
        assertEquals(test, portfolio.getStocks());
        assertEquals(0, tsla.getShares());
    }

    @Test
    void testAddFunds() {
        portfolio.addFunds(1);
        assertEquals(50001, portfolio.getFunds(), 0.01);
        portfolio.addFunds(1500);
        assertEquals(51501, portfolio.getFunds(), 0.01);
    }

    @Test
    void testWithdrawFunds() {
        portfolio.withdrawFunds(1);
        assertEquals(49999, portfolio.getFunds(), 0.01);
        portfolio.withdrawFunds(1500);
        assertEquals(48499, portfolio.getFunds(), 0.01);
        portfolio.withdrawFunds(48499);
        assertEquals(0, portfolio.getFunds(), 0.01);
    }

    @Test
    void testSetAutoTradeSell() {
        //multiple shares, sell 1 share at increase
        portfolio.buyShares(50, tsla);
        portfolio.setAutoTrade(1, tsla, 450, "sell");
        tsla.setValue(500); // updateValue will do this to a random value therefore it is not guaranteed setAutoTrade will result in a trade
        assertEquals(49, tsla.getShares());
        assertEquals(30500, portfolio.getFunds(), 0.01);
        portfolio.setAutoTrade(10, tsla, 550, "sell");
        tsla.setValue(550);
        // multiple shares, sell multiple at increase and exact trade value point
        assertEquals(39, tsla.getShares());
        assertEquals(36000, portfolio.getFunds(), 0.01);
        portfolio.setAutoTrade(39, tsla, 600, "sell");
        tsla.setValue(800);
        assertEquals(67200, portfolio.getFunds(), 0.01);
    }

    @Test
    void testSetAutoTradeBuy() {
        //multiple shares, buy 1 share at decrease
        portfolio.buyShares(50, tsla);
        portfolio.setAutoTrade(1, tsla, 350, "buy");
        tsla.setValue(300); // updateValue will do this to a random value therefore it is not guaranteed setAutoTrade will result in a trade
        assertEquals(51, tsla.getShares());
        assertEquals(29650, portfolio.getFunds(), 0.01);
        portfolio.setAutoTrade(10, tsla, 300, "buy");
        tsla.setValue(300);
        // multiple shares, buy multiple at decrease and exact trade value point
        assertEquals(61, tsla.getShares());
        assertEquals(26650, portfolio.getFunds(), 0.01);
        portfolio.withdrawFunds(150);
        portfolio.setAutoTrade(105, tsla, 250, "buy");
        tsla.setValue(250);
        assertEquals(0, portfolio.getFunds(), 0.01);
    }

}
