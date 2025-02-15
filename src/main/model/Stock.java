package model;

public class Stock {
    private int value;
    private String name;
    private int shares;
    private int volatility; // 1 for low, 2 for moderate, 3 for high

    public Stock(int volatility) {
        value = 0;
        name = "";
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
    public void updateValue() {
        //TODO
    }
}
