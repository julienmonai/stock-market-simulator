package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import model.Portfolio;
import model.Stock;

public class TradingAppUI extends JFrame {
    public static final int PORTFOLIO_TAB_INDEX = 0;
    public static final int MARKET_TAB_INDEX = 1;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private JTabbedPane sidebar;
    private TradingApp tradingApp;
    private Portfolio userPort;

    public TradingAppUI() {
        super("TradingApp Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initialize();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        
        loadTabs();
        add(sidebar);
        
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the trading app and portfolio
    private void initialize() {
        tradingApp = new TradingApp();
        userPort = tradingApp.getUserPortfolio();
    }
    
    // MODIFIES: this
    // EFFECTS: loads all tabs into the sidebar
    private void loadTabs() {
        JPanel startingTab = new StartingTab(this);
        JPanel portfolioTab = new PortfolioTab(this);
        JPanel marketTab = new MarketTab(this);
        
        sidebar.add(portfolioTab, PORTFOLIO_TAB_INDEX);
        sidebar.setTitleAt(PORTFOLIO_TAB_INDEX, "Portfolio");
        sidebar.add(marketTab, MARKET_TAB_INDEX);
        sidebar.setTitleAt(MARKET_TAB_INDEX, "Market");
    }
    
    // EFFECTS: returns the trading app controlled by this UI
    public TradingApp getTradingApp() {
        return tradingApp;
    }
    
    // EFFECTS: returns the user portfolio
    public Portfolio getUserPort() {
        return userPort;
    }
    
    // EFFECTS: returns the tabbed pane
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to my project!");
        new TradingAppUI();
    }
}
