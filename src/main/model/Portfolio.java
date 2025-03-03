package model;

import java.util.ArrayList;

public class Portfolio {
    private double initialFunds;
    private double funds;
    private ArrayList<Stock> stocks;
    //private double totalValue;
    //private double currentValue;

    //REQUIRES: initialFunds > 0
    //EFFECTS:  constructs a portfolio with no funds and no stocks
    public Portfolio(double initialFunds) {
        
        this.initialFunds = initialFunds;
        this.funds = initialFunds;
        stocks = new ArrayList<Stock>();
    }

    //EFFECTS: returns the total value of all shares of stocks plus current funds
    public double getTotalValue() {
        double total = funds;
        for (Stock stock : stocks) {
            total += (stock.getValue() * stock.getShares());
        }
        return total;
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: removes (shares * Stock.value()) from funds and adds shares to stock()
    //         if stock is not currently in stocks, add stock to stocks
    public void buyShares(int shares, Stock stock) {
        funds -= (shares * stock.getValue());
        stock.buyShares(shares);
        if (!stocks.contains(stock)) {
            stocks.add(stock);
        }
    }

    //REQUIRES: shares > 0, shares < this.shares
    //MODIFIES: this
    //EFFECTS: adds (shares * Stock(name) value) to funds and removes shares from Stock(name)
    //         if new stock value is 0, remove stock from stocks
    public void sellShares(int shares, Stock stock) {
        funds += (shares * stock.getValue());
        stock.sellShares(shares);
        if (stock.getShares() == 0) {
            stocks.remove(stock);
        } 
    }

    //REQUIRES: funds > 0
    //MODIFIES: this
    //EFFECTS: adds funds to this.funds
    public void addFunds(int funds) {
        this.funds += funds;
    }

    //REQUIRES: funds > 0 && funds < this.funds
    //MODIFIES: this
    //EFFECTS: removes funds from this.funds
    public void withdrawFunds(int funds) {
        this.funds -= funds;
    }

    //REQUIRES: shares > 0 (shares * stock(name) value) < funds
    //MODIFIES: this
    //EFFECTS: call sellShares or buyShares of shares if value of stock passes point according to action
    //public void setAutoTrade(int shares, Stock stock, int point, String action, int days) {
    //    for (int i = 0; i < days; i++) {
    //        stock.updateValue();
    //        if (stock.getValue() < point) {
    //            stock.sellShares(shares);
    //        }
    //    }
    //}

    //EFFECTS: returns current stocks
    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    //EFFECTS: returns current funds
    public double getFunds() {
        return this.funds;
    }

    public double getInitialFunds() {
        return initialFunds;
    }
    
}
