package ui;



import java.util.ArrayList;
import java.util.Scanner;

import model.Portfolio;
import model.Stock;

//The UI for the trading application
public class TradingApp {
    private Portfolio userPort;
    private Scanner input;
    private ArrayList<Stock> stockMarket = new ArrayList<Stock>();

    //EFFECTS: runs the trading application
    public TradingApp() {
        ArrayList<Stock> stockMarket = new ArrayList<Stock>();
        initStockMarket();
        runTradingApp();
    }

    //MODIFIES: this
    //EFFECTS: runs the trading application
    //templated off TellerApp
    private void runTradingApp() {
        Scanner scanner = new Scanner(System.in);
        this.input = new Scanner(System.in);
        boolean keepGoing = true;
        String command = null;

        System.out.println("Enter starting funds: ");
        Double funds = scanner.nextDouble();
        userPort = new Portfolio(funds);
        System.out.println("Starting with $" + funds.intValue());

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        //System.out.println("Enter starting funds: ");
        //Double funds = scanner.nextDouble();
        //userPort = new Portfolio(funds);
        //System.out.println("Starting with $" + funds.intValue());
        //System.out.println("\nAvailable Stocks: ");
        //for (Stock stock : stockMarket) {
        //    System.out.println(stock.getName() + ":    $" + stock.getValue());
        //}
        //System.out.println("Press c for ");
    }

    //EFFECTS: initializes set of available stocks for purchasing
    public void initStockMarket() {
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

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> buy shares");
        System.out.println("\ts -> sell shares");
        System.out.println("\tw -> withdraw funds");
        System.out.println("\tt -> add funds");
        System.out.println("\tv -> view portfolio");
        System.out.println("\tc -> complete day");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
    
        if (command.equals("b")) {
            System.out.println("Your current funds: $" + userPort.getFunds());
            printStocksAndSharesMarket();
            System.out.print("Enter stock name to buy: ");
            String stockName = scanner.next();
            Stock stock = findStock(stockName); // Assuming a method to find stock
            if (stock == null) {
                System.out.println("Stock not found.");
                return;
            }
            System.out.print("Enter number of shares to buy: ");
            int shares = scanner.nextInt();
            userPort.buyShares(shares, stock);
    
        } else if (command.equals("s")) {
            System.out.println("Your current stocks: ");
            printStocksAndShares();
            System.out.print("Enter stock name to sell: ");
            String stockName = scanner.next();
            Stock stock = findStock(stockName);
            if (stock == null) {
                System.out.println("Stock not found.");
                return;
            }
            System.out.print("Enter number of shares to sell: ");
            int shares = scanner.nextInt();
            userPort.sellShares(shares, stock);
    
        } else if (command.equals("w")) {
            System.out.println("Your current funds: $" + userPort.getFunds());
            System.out.print("Enter amount to withdraw: ");
            int amount = scanner.nextInt();
            userPort.withdrawFunds(amount);
    
        } else if (command.equals("t")) {
            System.out.println("Your current funds: $" + userPort.getFunds());
            System.out.print("Enter amount to add: ");
            int amount = scanner.nextInt();
            userPort.addFunds(amount);
    
        } else if (command.equals("v")) {
            System.out.println("Portfolio Overview:");
            System.out.println("Portfolio current value: " + userPort.getTotalValue());
            System.out.println("portfolio current ROI: %" + currentReturn());
            System.out.println("Funds: $" + userPort.getFunds());
            System.out.println("Stocks: ");
            printStocksAndShares();
    
        } else if (command.equals("q")) {
            System.out.println("Exiting...");
    
        } else if (command.equals("c")) {
            System.out.println("Updating stock values.");
            for (Stock stock : stockMarket) {
                stock.updateValue();
            }
            
    
        } else {
            System.out.println("Selection not valid...");
        }
    }
    
    //EFFECTS: returns the stock with stock.name matching name
    private Stock findStock(String name) {
        for (Stock stock : stockMarket) {
            if (name.equals(stock.getName())) {
                return stock;
            }
        }
        return null;
    }

    //EFFECTS: prints the stock name and shares and price of each stock in portfolio stocks
    private void printStocksAndShares() {
        for (Stock stock : userPort.getStocks()) {
            System.out.println(stock.getName() + "   shares: " + stock.getShares());
            System.out.println(stock.getName() + " price per share: $" + stock.getValue());
        }
    }

    //EFFECTS: prints stock names and shares and price of each stock in stock market
    private void printStocksAndSharesMarket() {
        for (Stock stock : stockMarket) {
            System.out.println(stock.getName() + "   shares: " + stock.getShares());
            System.out.println(stock.getName() + " price per share: $" + stock.getValue());
        }
    }

    //returns the ROI of portfolio as a percent
    private double currentReturn() {
        if (userPort.getTotalValue() < userPort.getInitialFunds()) {
            return -1 * (userPort.getTotalValue() / userPort.getInitialFunds());
        } else {
            return userPort.getTotalValue() / userPort.getInitialFunds();
        }
    }

}
