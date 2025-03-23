package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeTab extends Tab implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    
    public HomeTab(TradingAppUI controller) {
        super(controller);
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Enter Starting Funds: ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // frame.setSize
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
