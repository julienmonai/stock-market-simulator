package model;

import java.util.ArrayList;

public class Portfolio {
    private int funds;
    private ArrayList<Stock> stocks;

    public Portfolio(int initialFunds) {
        this.funds = initialFunds;
        stocks = new ArrayList<Stock>();
    }

    
}
