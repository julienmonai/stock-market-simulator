package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import model.Portfolio;
import model.Stock;

public class TradingAppUI extends JFrame {
    //public static final int STARTING_TAB_INDEX = 0;
    public static final int HOME_TAB_INDEX = 0;
    public static final int PORTFOLIO_TAB_INDEX = 1;
    public static final int MARKET_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private JTabbedPane sidebar;
    private ArrayList<Stock> stockMarket;
    private Portfolio userPort;
    private PortfolioTab portTab;
    private MarketTab marketTab;

    // TradingApp GUI
    public TradingAppUI() {
        super("TradingApp Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new StartingTab(this), BorderLayout.CENTER);

        setVisible(true);

        initStockMarket();
    }

    // MODIFIES: this
    // EFFECTS: initializes stock market
    public void initStockMarket() {
        stockMarket = new ArrayList<Stock>();
        Stock apple = new Stock("apple", 1, 120);
        Stock micro = new Stock("micro", 2, 250);
        Stock tsla = new Stock("tsla", 3, 400);
        Stock amd = new Stock("amd", 2, 60);
        Stock edr = new Stock("edr", 2, 30);
        stockMarket.add(apple);
        stockMarket.add(micro);
        stockMarket.add(tsla);
        stockMarket.add(amd);
        stockMarket.add(edr);
    }
    
    // MODIFIES: this
    // EFFECTS: declares and loads all tabs into the sidebar
    private void loadTabs() {
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        sidebar.addTab("Home", new HomeTab(this));
        portTab = new PortfolioTab(this);
        sidebar.addTab("Portfolio", portTab);
        marketTab = new MarketTab(this);
        sidebar.addTab("Stock Market", marketTab);
        add(sidebar, BorderLayout.CENTER);

        sidebar.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane source = (JTabbedPane)e.getSource();
                int selectedIndex = source.getSelectedIndex();
                if (selectedIndex == PORTFOLIO_TAB_INDEX) {
                    portTab.updateDisplay();
                } else if (selectedIndex == MARKET_TAB_INDEX) {
                    marketTab.updateDisplay();
                }
            }
        });
    }
    
    public Portfolio getUserPort() {
        return userPort;
    }

    public PortfolioTab getPortTab() {
        return portTab;
    }

    public MarketTab getMarketTab() {
        return marketTab;
    }

    public ArrayList<Stock> getStockMarket() {
        return stockMarket;
    }
    
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    //MODIFIES: this
    //EFFECTS: sets userPort to given portfolio
    public void setUserPortfolio(Portfolio p) {
        userPort = p;

    }

    //MODIFIES: this
    //EFFECTS: sets stock market to given stock market
    public void setStockMarket(ArrayList<Stock> sm) {
        stockMarket = sm;
    }

    //MODIFIES: this
    //EFFECTS: updates stock values
    public void completeDay() {
        for (Stock stock : stockMarket) {
            stock.updateValue();
        }
        portTab.updateDisplay();
        marketTab.updateDisplay();
    }

    //MODIFIES: this
    //EFFECTS: switches from UI with only startingTab to UI with sidebar and all other tabs
    public void switchUI() {
        getContentPane().removeAll();
        loadTabs();
        revalidate();
    }

    public static void main(String[] args) throws Exception {
        //System.out.println("Welcome to my project!");
        new TradingAppUI();
    }
}
