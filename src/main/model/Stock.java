package model;

public class Stock {
    private double value;
    private String name;
    private int shares;
    private int volatility; // 1 for low, 2 for moderate, 3 for high

    public Stock(String name, int volatility, double initialvalue) {
        value = initialvalue;
        this.name = name;
        shares = 0;
        this.volatility = volatility;
    }
    //REQUIRES: shares > 0
    //MODIFIES: this
    //EFFECTS: adds shares to this.shares
    public void buyShares(int shares) {
        //TODO
    }

    //REQUIRES: shares > 0, shares < this.shares
    //MODIFIES: this
    //EFFECTS: subtracts shares from this.shares
    public void sellShares(int shares) {
        //TODO
    }

    //MODIFIES: this
    //EFFECTS: randomly changes the value of stock according to volativity level
    //         if vol = 1, change per tick is +/- 2.5%
    //         if vol = 2, change per tick is +/- 10%
    //         if vol = 3, change per tick is +/- 20% 
    public void updateValue() {
        //TODO
    }

    public double getValue() {
        //TODO
        return 0.0;
    }

    public String getName() {
        //TODO
        return "";
    }

    public int getShares() {
        //TODO
        return 0;
    }

    public int getVolatility() {
        //TODO
        return 0;
    }
}
