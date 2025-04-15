package perilsalongtheplatte.panels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomeScreen extends JPanel {
    //create the objects of various buttons and text fields
    JPanel welcomePanel = new JPanel(new BorderLayout());
    JLabel welcomeLbl = new JLabel("Welcome! ", JLabel.CENTER); //centers the welcome label 
    JButton start = new JButton("Start"); //creates a start button
    public boolean closed = false; //a boolean that tracks if the frame should be visible 
    
    //since this class extends JPanel, we can use a method similar to the one below... 
    public WelcomeScreen() {
    	setSize(1200, 700); 
    	//modify the appearance of the welcome label
    	welcomeLbl.setFont(new Font("Serif", Font.BOLD, 100)); 
    	
    	//add in components to the label
        welcomePanel.add(welcomeLbl, BorderLayout.CENTER); 
        welcomePanel.add(start, BorderLayout.SOUTH); 
        
        //an action listener on the start button. Since there is a single button, no methods are created for the listener
        start.addActionListener(e -> {
            closed = true; 
            System.out.println(closed);
        }); 
        
        add(welcomePanel); //add the panel to the WelcomeScreen
    }

 
}