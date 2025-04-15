package perilsalongtheplatte;
import perilsalongtheplatte.panels.*; //import subpackage of panels

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Scanner;
import java.util.Random;

public class GAME { 
	//declare objects of the class, including instantuations of the various classes
	private JFrame frame;
	private JPanel gamePanel = new JPanel();
	
	//construction of the panel classes
	WelcomeScreen welcomeScreen = new WelcomeScreen(); 
	TravelDistance distance = new TravelDistance(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GAME window = new GAME();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GAME() {
		initialize();
		
		//runs an infinite loop with a small delay
		try {
			
			//checks to see if the panels should close using buttons located within the respective panels
			closeWelcomeScreen(); 
			frame.repaint();
			
		    Thread.sleep(15); //delay for 15 ms
		    
		} catch (Exception e) {
		    e.printStackTrace(); // Handle the error
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//create components of the frame
		frame = new JFrame(); 
		frame.setSize(1200, 700);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		gamePanel.setBounds(0, 0, 1200, 700);
		
		//create elements of the "home panel", or the primary game panel 
		JLabel traveledDistance = new JLabel(); 
		JLabel notification = new JLabel(); 
		JButton travel = new JButton(); 
		//int totalDistance = distance.distanceTraveled(); 	
		
		//modify the contents of traveledDistance Label and add to gamePanel
		traveledDistance.setFont(new Font("Serif", Font.BOLD, 25)); 
		traveledDistance.setText("Distance Traveled: ");
		gamePanel.add(traveledDistance, BorderLayout.CENTER); 
		
		//modify contents of notification label
		notification.setFont(new Font("Serif", Font.BOLD, 25)); 
		notification.setText("HI");
		gamePanel.add(notification);
		
		//modify content of travel button
		travel.setText("Travel?");
		travel.setFont(new Font("Serif", Font.BOLD, 25)); 
		gamePanel.add(travel, BorderLayout.CENTER); 
		
		
		
		
		//add the various panels into the frame, set them to invisible to start
		frame.add(welcomeScreen); //this is the exception, since it is the starting screen
		frame.add(gamePanel); 
		
		
	}
	
	
	private void closeWelcomeScreen() {
		//check if the user clicked the "start" button within the panel
        Timer timer = new Timer(200, event -> {
            if (welcomeScreen.closed) { //utilizing a boolean variable
            	//remove the current frame 
                frame.remove(welcomeScreen); // take away the welcome screen 
                frame.revalidate(); // refresh layout
                frame.repaint();    // redraw everything
                //since the panel is gone, stop the timer 
                ((Timer) event.getSource()).stop();
            }
        });
        timer.start(); //start checking with the created timer 
		
		

	}
	
	
}
