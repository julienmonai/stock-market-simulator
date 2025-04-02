package ui;

import model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MarketTab extends Tab implements ActionListener {
    private JTable marketTable;
    private DefaultTableModel tableModel;
    private JTextField sharesField;
    private JButton buyButton;
    private JLabel fundsLabel;
    
    // the tab displaying the information of the stock market
    public MarketTab(TradingAppUI controller) {
        super(controller);
        setLayout(new BorderLayout());
        
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        updateDisplay();
    }
    
    //MODIFIES: this
    //EFFECTS: initializes the JPanel that displays the portfolio funds
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Your Funds"));
        
        fundsLabel = new JLabel("Available Funds: $0.00");
        panel.add(fundsLabel);
        return panel;
    }
    
    //MODIFIES: this
    //EFFECTS: initializes the JPanel that holds the table displaying the stocks in stockmarket
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Stock Market"));
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tableModel.addColumn("Stock Name");
        tableModel.addColumn("Value per Share");
        tableModel.addColumn("Owned Shares");
        
        marketTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(marketTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    //MODIFIES: this
    //EFFECTS: initializes the JPanel with the button to buy stocks and textfield for # of stocks to buy
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createTitledBorder("Buy Stocks"));
        
        panel.add(new JLabel("Number of shares to buy:"));
        sharesField = new JTextField(5);
        panel.add(sharesField);
        
        buyButton = new JButton("Buy Selected Stock");
        buyButton.addActionListener(this);
        panel.add(buyButton);
        
        return panel;
    }
    
    //MODIFIES: this
    //EFFECTS: updates the values in the stock market table, and the funds 
    public void updateDisplay() {
        fundsLabel.setText(String.format("Available Funds: $%.2f", getController().getUserPort().getFunds()));
        tableModel.setRowCount(0);
        
        ArrayList<Stock> stockMarket = getController().getStockMarket();
        for (Stock stock : stockMarket) {
            tableModel.addRow(new Object[]{
                        stock.getName(),
                        String.format("$%.2f", stock.getValue()), stock.getShares()});
        }
    }
    
    @Override
    @SuppressWarnings("methodlength")
    //MODIFIES: this
    //EFFECTS: if action is buyButton, buy the selected stock and # of stocks, and updates tab
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            int selectedRow = marketTable.getSelectedRow();            
            int shares = Integer.parseInt(sharesField.getText());

                
            String stockName = (String) tableModel.getValueAt(selectedRow, 0);
            
            ArrayList<Stock> marketStocks = getController().getStockMarket();
            Stock stockToBuy = null;
            for (Stock stock : marketStocks) {
                if (stock.getName().equals(stockName)) {
                    stockToBuy = stock;
                    break;
                }
            }
                
            if (stockToBuy == null) {
                JOptionPane.showMessageDialog(this, "Stock not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            double cost = shares * stockToBuy.getValue();
            if (cost > getController().getUserPort().getFunds()) {
                JOptionPane.showMessageDialog(this, "Insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            getController().getUserPort().buyShares(shares, stockToBuy);
            updateDisplay();
            sharesField.setText("");

            if (getController().getTabbedPane().getSelectedIndex() == TradingAppUI.PORTFOLIO_TAB_INDEX) {
                int tab = TradingAppUI.PORTFOLIO_TAB_INDEX;
                ((PortfolioTab) this.getController().getTabbedPane().getComponentAt(tab)).updateDisplay();
            }
        }
    }
}