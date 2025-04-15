package perilsalongtheplatte.panels;
import java.awt.BorderLayout;
import javax.swing.*;

public class WelcomeScreen extends JPanel {
    //create the objects of various buttons and text fields
    JPanel welcomePanel = new JPanel(new BorderLayout());
    JLabel welcomeLbl = new JLabel("Welcome! ", JLabel.CENTER); //centers the welcome label 
    
    //since this class extends JPanel, we can use a method similar to the one below... 
    public WelcomeScreen() {
        welcomePanel.add(welcomeLbl, BorderLayout.CENTER); //add the label to the center
        add(welcomePanel); //add the panel to the WelcomeScreen
    }
}