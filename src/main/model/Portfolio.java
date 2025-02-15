package model;

import java.util.ArrayList;

public class Portfolio {
    private int funds;
    private ArrayList<Stock> stocks;
    private int totalValue;
    //REQUIRES: initialFunds > 0
    //EFFECTS:  constructs a portfolio with no funds and no stocks
    public Portfolio(int initialFunds) {
        //TODO
        this.funds = initialFunds;
        stocks = new ArrayList<Stock>();
    }

    //EFFECTS: returns the total value of all shares of stocks plus current funds
    public int gettotalValue() {
        //TODO
        return 0;
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: removes (shares * Stock(name) value) from funds and adds shares to Stock(name)
    //         if stock(name) is not currently in stocks, add stock(name) to stocks
    public void buyShares(int shares, String name) {
        //TODO
    }

    //REQUIRES: shares > 0, shares < this.shares
    //MODIFIES: this
    //EFFECTS: adds (shares * Stock(name) value) to funds and removes shares from Stock(name)
    public void sellShares(int shares, String name) {
        //TODO
    }

    //EFFECTS: returns current stocks
    public ArrayList<Stock> getStocks() {
        //TODO
        return new ArrayList<Stock>();
    }

    //EFFECTS: returns list of individual stock values
    public ArrayList<Integer> getStockValues() {
        //TODO
        return new ArrayList<Integer>();
    }

    //REQUIRES: funds > 0
    //MODIFIES: this
    //EFFECTS: adds funds to this.funds
    public void addFunds(int funds) {
        //TODO
    }

    //REQUIRES: funds > 0 && funds < this.funds
    //MODIFIES: this
    //EFFECTS: removes funds from this.funds
    public void withdrawFunds() {
        //TODO
    }

    //EFFECTS: returns the percentage of initial funds in total value
    public double getGainLossPercent() {
        //TODO
        return 0.0;
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: call sellShares or buyShares of shares if value of stock passes point
    public void setAutoTrade(int shares, String name, int point) {
        //TODO
    }


    
}
