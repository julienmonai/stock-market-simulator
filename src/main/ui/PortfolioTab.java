package ui;

import model.Portfolio;
import model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PortfolioTab extends Tab implements ActionListener {
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JTextField sharesField;
    private JButton sellButton;
    private JLabel portfolioValueLabel;
    private JLabel roiLabel;
    private JLabel fundsLabel;
    
    public PortfolioTab(TradingAppUI controller) {
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
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Portfolio Information"));
        
        portfolioValueLabel = new JLabel("Portfolio Value: $0.00");
        roiLabel = new JLabel("Return on Investment: 0.00%");
        fundsLabel = new JLabel("Available Funds: $0.00");
        
        panel.add(portfolioValueLabel);
        panel.add(roiLabel);
        panel.add(fundsLabel);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Your Stocks"));
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Stock Name");
        tableModel.addColumn("Value per Share");
        tableModel.addColumn("Shares Owned");
        tableModel.addColumn("Total Value");
        

        stockTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(stockTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    //EFFECT: creates JPanel for selling
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createTitledBorder("Sell Stocks"));
        
        panel.add(new JLabel("Number of shares to sell:"));
        sharesField = new JTextField(5);
        panel.add(sharesField);
        
        sellButton = new JButton("Sell Selected Stock");
        sellButton.addActionListener(this);
        panel.add(sellButton);
        
        return panel;
    }
    
    //EFFECT: Updates the display with current portfolio information
    public void updateDisplay() {
        Portfolio portfolio = getController().getUserPort();
        
        portfolioValueLabel.setText(String.format("Portfolio Value: $%.2f", portfolio.getTotalValue()));
        fundsLabel.setText(String.format("Available Funds: $%.2f", portfolio.getFunds()));
        double roi = currentReturn(portfolio);
        roiLabel.setText(String.format("Return on Investment: %.2f%%", roi));
        
        tableModel.setRowCount(0);
        for (Stock stock : portfolio.getStocks()) {
            double totalValue = stock.getValue() * stock.getShares();
            tableModel.addRow(new Object[]{
                stock.getName(),
                String.format("$%.2f", stock.getValue()),
                stock.getShares(),
                String.format("$%.2f", totalValue)
            });
        }
    }
    
    //EFFECTS: returns the ROI of the portfolio
    private double currentReturn(Portfolio userPort) {
        double initialFunds = userPort.getInitialFunds();
        double totalValue = userPort.getTotalValue();
        return ((totalValue - initialFunds) / initialFunds) * 100;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellButton) {
            int selectedRow = stockTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a stock to sell", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int shares = Integer.parseInt(sharesField.getText());
                if (shares <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number of shares", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String stockName = (String) tableModel.getValueAt(selectedRow, 0);
                int currentShares = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
                
                if (shares > currentShares) {
                    JOptionPane.showMessageDialog(this, "You don't have enough shares to sell", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ArrayList<Stock> userStocks = getController().getUserPort().getStocks();
                for (Stock stock : userStocks) {
                    if (stock.getName().equals(stockName)) {
                        getController().getUserPort().sellShares(shares, stock);
                        break;
                    }
                }
            
                updateDisplay();
                sharesField.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}