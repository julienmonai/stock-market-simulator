package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;

public class HomeTab extends Tab implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton completeButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton saveButton;
    
    // creates a home tab consisting of buttons complete day, load, save, and quit
    public HomeTab(TradingAppUI controller) {
        super(controller);
        createButtons();
        setLayout(new BorderLayout());
        JPanel centerPanel =  new JPanel();
        centerPanel.add(completeButton);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(quitButton);
        topPanel.add(topRightPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
    }

    //EFFECTS: initialize buttons for homeTab
    private void createButtons() {
        completeButton = new JButton("complete day");
        completeButton.setActionCommand("complete day");
        completeButton.addActionListener(this);
        loadButton = new JButton("load save");
        loadButton.setActionCommand("load save");
        loadButton.addActionListener(this);
        quitButton = new JButton("quit");
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        saveButton = new JButton("save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: manages inputs, if save button is pressed, write JSon data
    //                         if load button is pressed, read JSon data
    //                         if complete day button is pressed, update stock values
    //                         if quit is pressed, exit program
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            JsonWriter writer = new JsonWriter("./data/portfolio.Json");
            try {
                writer.open();
            } catch (Exception a) {
                a.printStackTrace();
            }
            writer.writePortfolio(this.getController().getUserPort());
            writer.close();
            JsonWriter writer2 = new JsonWriter("./data/stockMarket.Json");
            try {
                writer2.open();
            } catch (Exception a) {
                a.printStackTrace();
            } 
            writer2.writeStockMarket(this.getController().getStockMarket());
            writer2.close();
        } else if ("load save".equals(e.getActionCommand())) {
            JsonReader reader = new JsonReader("./data/portfolio.Json");
            try {
                this.getController().setUserPortfolio(reader.readPortfolio());
            } catch (Exception a) {
                a.printStackTrace();
            }
            JsonReader reader2 = new JsonReader("./data/stockMarket.Json");
            try {
                this.getController().setStockMarket(reader2.readStockMarket());
            } catch (Exception a) {
                a.printStackTrace();
            }
        } else if ("complete day".equals(e.getActionCommand())) {
            this.getController().completeDay();
            this.getController().getMarketTab().updateDisplay();
            this.getController().getPortTab().updateDisplay();
        } else if ("quit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
}
