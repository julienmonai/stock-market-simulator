package model;

import java.util.Random;

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
        this.shares+= shares;
    }

    //REQUIRES: shares > 0, shares < this.shares
    //MODIFIES: this
    //EFFECTS: subtracts shares from this.shares
    public void sellShares(int shares) {
        this.shares-= shares;
    }

    //MODIFIES: this
    //EFFECTS: randomly changes the value of stock according to volativity level
    //         if vol = 1, change per tick is +/- 2.5%
    //         if vol = 2, change per tick is +/- 10%
    //         if vol = 3, change per tick is +/- 20% 
    public void updateValue() {
        Random rand = new Random();
        Boolean addOrMinus = rand.nextBoolean();
        double change;
        
        if (volatility == 1) {
            change = (rand.nextDouble() * (0.025 * value));

        } else {
            if (volatility == 2) {
               change = (rand.nextDouble() * (0.1 * value));
           
            } else {
                change = (rand.nextDouble() * (0.2 * value));
            }
        }
        if (addOrMinus) {
            value += change;
        } else {
            value -= change;
        }
    }

    public double getValue() {
        return this.value;
    }

    //REQUIRES: newValue > 0
    public void setValue(double newValue) {
        value = newValue;
    }

    public String getName() {
        return this.name;
    }

    public int getShares() {
        return this.shares;
    }

    public int getVolatility() {
        return this.volatility;
    }
}
