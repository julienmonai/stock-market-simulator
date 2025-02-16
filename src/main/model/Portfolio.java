package model;

import java.util.ArrayList;

public class Portfolio {
    private double initialFunds;
    private double funds;
    private ArrayList<Stock> stocks;
    private double totalValue;

    //REQUIRES: initialFunds > 0
    //EFFECTS:  constructs a portfolio with no funds and no stocks
    public Portfolio(double initialFunds) {
        //TODO
        this.initialFunds = initialFunds;
        this.funds = initialFunds;
        stocks = new ArrayList<Stock>();
    }

    //EFFECTS: returns the total value of all shares of stocks plus current funds
    public double getTotalValue() {
        //TODO
        return 0;
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: removes (shares * Stock.value()) from funds and adds shares to stock()
    //         if stock is not currently in stocks, add stock to stocks
    public void buyShares(int shares, Stock stock) {
        //TODO
    }

    //REQUIRES: shares > 0, shares < this.shares
    //MODIFIES: this
    //EFFECTS: adds (shares * Stock(name) value) to funds and removes shares from Stock(name)
    public void sellShares(int shares, Stock stock) {
        //TODO
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
    public void withdrawFunds(int funds) {
        //TODO
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: call sellShares or buyShares of shares if value of stock passes point according to action
    public void setAutoTrade(int shares, Stock stock, int point, String action) {
        //TODO
    }

    //EFFECTS: returns current stocks
    public ArrayList<Stock> getStocks() {
        //TODO
        return new ArrayList<Stock>();
    }

    //EFFECTS: returns current funds
    public double getFunds() {
        //TODO
        return 0;
    }
    
}
