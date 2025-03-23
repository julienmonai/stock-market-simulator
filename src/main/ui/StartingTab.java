package ui;

import model.Portfolio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class StartingTab extends Tab implements ActionListener{
    private JTextField inputField;
    private JButton confirmButton;
    private double funds;
    private Portfolio userPort;
    

    public StartingTab(TradingAppUI controller) {
        super(controller);
        
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel fundsLabel = new JLabel("Enter starting amount: $");
        BufferedImage picture;
        try {
            //picture = ImageIO.read(getClass().getResource("/images/stockExchange.jpg"));
            picture = ImageIO.read(new File("src/main/ui/images/stockExchange.jpg"));
            JLabel pictureLabel = new JLabel(new ImageIcon(picture));
            inputField = new JTextField(10);
            confirmButton = new JButton("confirm");
            confirmButton.setActionCommand("confirm");
            confirmButton.addActionListener(this);

            inputPanel.add(fundsLabel);
            inputPanel.add(inputField);
            inputPanel.add(confirmButton);
            inputPanel.add(pictureLabel);  

            add(inputPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // JLabel pictureLabel = new JLabel(new ImageIcon(picture));
        // inputField = new JTextField(10);
        // confirmButton = new JButton("confirm");
        // confirmButton.setActionCommand("confirm");
        // confirmButton.addActionListener(this);

        // inputPanel.add(fundsLabel);
        // inputPanel.add(inputField);
        // inputPanel.add(confirmButton);  

        // add(inputPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            double funds = Double.parseDouble(inputField.getText());
            userPort = new Portfolio(funds);
            getController().setUserPortfolio(userPort);
            getController().switchUI();
        }
    }    
}
