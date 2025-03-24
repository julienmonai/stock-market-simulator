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
    
    public MarketTab(TradingAppUI controller) {
        super(controller);
        setLayout(new BorderLayout());
        
        // Create funds info panel
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);
        
        // Create table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        // Create control panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        // Update display
        updateDisplay();
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Your Funds"));
        
        fundsLabel = new JLabel("Available Funds: $0.00");
        panel.add(fundsLabel);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Stock Market"));
        
        // Create table model with column names
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        tableModel.addColumn("Stock Name");
        tableModel.addColumn("Value per Share");
        tableModel.addColumn("Owned Shares");
        
        // Create table with the model
        marketTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(marketTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
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
    
    // Updates the display with current market information
    public void updateDisplay() {
        fundsLabel.setText(String.format("Available Funds: $%.2f", getController().getUserPort().getFunds()));
        tableModel.setRowCount(0);
        
        // Populate table with market stocks
        ArrayList<Stock> stockMarket = getController().getStockMarket();
            for (Stock stock : stockMarket) {
                //int sharesText = stock.getShares();
                
                tableModel.addRow(new Object[]{
                    stock.getName(),
                    String.format("$%.2f", stock.getValue()), stock.getShares()});
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            int selectedRow = marketTable.getSelectedRow();            
            int shares = Integer.parseInt(sharesField.getText());

                
            String stockName = (String) tableModel.getValueAt(selectedRow, 0);
                
            // Find the stock to buy
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
                
            // Check if user has enough funds
            double cost = shares * stockToBuy.getValue();
            if (cost > getController().getUserPort().getFunds()) {
                JOptionPane.showMessageDialog(this, "Not enough funds to buy these shares", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                
            // Buy the stock
            getController().getUserPort().buyShares(shares, stockToBuy);
                
            // Update the display
            updateDisplay();
            // Clear the shares field
            sharesField.setText("");
            
            // Update portfolio tab if visible
            if (getController().getTabbedPane().getSelectedIndex() == TradingAppUI.PORTFOLIO_TAB_INDEX) {
                ((PortfolioTab) this.getController().getPortTab().getController().getTabbedPane().getComponentAt(TradingAppUI.PORTFOLIO_TAB_INDEX)).updateDisplay();
            }
        }
    }
}