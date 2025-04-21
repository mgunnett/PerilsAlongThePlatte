package perilsalongtheplatte;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GAME {
	//create objects of the class, including each widget
	private JFrame frame;
	
	//create objects of each of our custom classes
	private Popups popup = new Popups(); 
	


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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1300, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setBackground(new Color(154, 128, 71));
		OptionsPanel.setBounds(36, 168, 1212, 490);
		frame.getContentPane().add(OptionsPanel);
		OptionsPanel.setLayout(null);
		
		JSpinner spinnerSpeed = new JSpinner();
		spinnerSpeed.setBackground(new Color(255, 255, 255));
		spinnerSpeed.setFont(new Font("Serif", Font.PLAIN, 20));
		spinnerSpeed.setBounds(115, 35, 172, 32);
		OptionsPanel.add(spinnerSpeed);
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setForeground(new Color(0, 0, 0));
		lblSpeed.setFont(new Font("Serif", Font.PLAIN, 30));
		lblSpeed.setBounds(12, 31, 93, 32);
		OptionsPanel.add(lblSpeed);
		
		JLabel lblRations = new JLabel("Rations:");
		lblRations.setForeground(new Color(0, 0, 0));
		lblRations.setFont(new Font("Serif", Font.PLAIN, 30));
		lblRations.setBounds(12, 78, 104, 32);
		OptionsPanel.add(lblRations);
		
		JSpinner spinnerRations = new JSpinner();
		spinnerRations.setFont(new Font("Serif", Font.PLAIN, 20));
		spinnerRations.setBackground(new Color(224, 213, 188));
		spinnerRations.setBounds(115, 82, 172, 32);
		OptionsPanel.add(spinnerRations);
		
		JButton btnRest = new JButton("Rest");
		btnRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TEST CODE ONly
				int mymommy = popup.restDays(); 
				System.out.println(""+ mymommy);
			}
		});
		btnRest.setBackground(new Color(220, 207, 180));
		btnRest.setFont(new Font("Serif", Font.PLAIN, 35));
		btnRest.setBounds(16, 369, 145, 110);
		OptionsPanel.add(btnRest);
		
		JButton btnTravel = new JButton("Travel");
		btnTravel.setFont(new Font("Serif", Font.PLAIN, 35));
		btnTravel.setBackground(new Color(220, 207, 180));
		btnTravel.setBounds(215, 369, 145, 110);
		OptionsPanel.add(btnTravel);
		
		JButton btnHunt = new JButton("Hunt");
		btnHunt.setFont(new Font("Serif", Font.PLAIN, 35));
		btnHunt.setBackground(new Color(220, 207, 180));
		btnHunt.setBounds(421, 369, 145, 110);
		OptionsPanel.add(btnHunt);
		
		JPanel InventoryPanel = new JPanel();
		InventoryPanel.setBackground(new Color(220, 207, 180));
		InventoryPanel.setBounds(894, 11, 308, 468);
		OptionsPanel.add(InventoryPanel);
		InventoryPanel.setLayout(null);
		
		JLabel lblInventory = new JLabel("<html><u>Inventory</u>");
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setForeground(Color.BLACK);
		lblInventory.setFont(new Font("Serif", Font.PLAIN, 30));
		lblInventory.setBounds(10, 11, 288, 39);
		InventoryPanel.add(lblInventory);
		
		JPanel EventLogPanel = new JPanel();
		EventLogPanel.setBackground(new Color(220, 207, 180));
		EventLogPanel.setBounds(576, 11, 308, 468);
		OptionsPanel.add(EventLogPanel);
		EventLogPanel.setLayout(null);
		
		JLabel lblEventLog = new JLabel("<html><u>Event Log</u>");
		lblEventLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventLog.setForeground(Color.BLACK);
		lblEventLog.setFont(new Font("Serif", Font.PLAIN, 30));
		lblEventLog.setBounds(10, 11, 288, 39);
		EventLogPanel.add(lblEventLog);
		
		JLabel lblConstOverallGroupHealth = new JLabel("Overall Group Health:");
		lblConstOverallGroupHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblConstOverallGroupHealth.setForeground(Color.BLACK);
		lblConstOverallGroupHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstOverallGroupHealth.setBounds(12, 156, 267, 32);
		OptionsPanel.add(lblConstOverallGroupHealth);
		
		JLabel lblOverallGroupHealth = new JLabel("");
		lblOverallGroupHealth.setForeground(new Color(0, 0, 0));
		lblOverallGroupHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblOverallGroupHealth.setBounds(281, 156, 153, 32);
		OptionsPanel.add(lblOverallGroupHealth);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(36, 11, 83, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblConstWeather = new JLabel("Weather:");
		lblConstWeather.setForeground(Color.WHITE);
		lblConstWeather.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstWeather.setBounds(36, 46, 142, 24);
		frame.getContentPane().add(lblConstWeather);
		
		JLabel lblConstDistanceTraveled = new JLabel("Distance Traveled:");
		lblConstDistanceTraveled.setForeground(Color.WHITE);
		lblConstDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceTraveled.setBounds(36, 81, 256, 24);
		frame.getContentPane().add(lblConstDistanceTraveled);
		
		JLabel lblConstDistanceUntilNext = new JLabel("Distance Until Next Landmark:");
		lblConstDistanceUntilNext.setForeground(Color.WHITE);
		lblConstDistanceUntilNext.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceUntilNext.setBounds(36, 117, 406, 24);
		frame.getContentPane().add(lblConstDistanceUntilNext);
		
		JLabel lblDate = new JLabel("");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDate.setBounds(129, 11, 153, 24);
		frame.getContentPane().add(lblDate);
		
		JLabel lblWeather = new JLabel("");
		lblWeather.setForeground(Color.WHITE);
		lblWeather.setFont(new Font("Serif", Font.PLAIN, 30));
		lblWeather.setBounds(178, 46, 153, 24);
		frame.getContentPane().add(lblWeather);
		
		JLabel lblDistanceTraveled = new JLabel("");
		lblDistanceTraveled.setForeground(Color.WHITE);
		lblDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceTraveled.setBounds(289, 81, 153, 24);
		frame.getContentPane().add(lblDistanceTraveled);
		
		JLabel lblDistanceUntilNextLandmark = new JLabel("");
		lblDistanceUntilNextLandmark.setForeground(Color.WHITE);
		lblDistanceUntilNextLandmark.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceUntilNextLandmark.setBounds(441, 117, 153, 24);
		frame.getContentPane().add(lblDistanceUntilNextLandmark);
	}
}
