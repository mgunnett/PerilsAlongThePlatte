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
import perilsalongtheplatte.DailyEvents;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class GAME {
	//create objects of the class, including each widget
	private JFrame frame;
	
	//create objects of each of our custom classes
	private Popups popup = new Popups(); 
	
	private DailyEvents daily_events;
	private JTextField txtPlayer1Name;
	private JTextField txtPlayer2Name;
	private JTextField txtPlayer3Name;
	private JTextField txtPlayer4Name;
	private JTextField txtPlayer5Name;

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
		daily_events = new DailyEvents("any", 100); // change for gender and health in the future. Not Final.
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		JPanel StartingOptionsPanel;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1300, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel GamePanel = new JPanel();
		GamePanel.setBackground(new Color(0, 0, 0));
		GamePanel.setBounds(0, 0, 1283, 699);
		frame.getContentPane().add(GamePanel);
		GamePanel.setLayout(null);
		GamePanel.setVisible(false);		
		
		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(null);
		OptionsPanel.setBackground(new Color(154, 128, 71));
		OptionsPanel.setBounds(38, 198, 1212, 490);
		GamePanel.add(OptionsPanel);
		
		JSpinner spinnerSpeed = new JSpinner();
		spinnerSpeed.setFont(new Font("Serif", Font.PLAIN, 20));
		spinnerSpeed.setBackground(Color.WHITE);
		spinnerSpeed.setBounds(115, 35, 172, 32);
		OptionsPanel.add(spinnerSpeed);
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setForeground(Color.BLACK);
		lblSpeed.setFont(new Font("Serif", Font.PLAIN, 30));
		lblSpeed.setBounds(12, 31, 93, 32);
		OptionsPanel.add(lblSpeed);
		
		JLabel lblRations = new JLabel("Rations:");
		lblRations.setForeground(Color.BLACK);
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
				int mymommy = popup.restDays(); 
 				System.out.println(""+ mymommy);
			}
		});
		btnRest.setFont(new Font("Serif", Font.PLAIN, 35));
		btnRest.setBackground(new Color(220, 207, 180));
		btnRest.setBounds(16, 369, 145, 110);
		OptionsPanel.add(btnRest);
		
		JButton btnTravel = new JButton("Travel");
		btnTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTravel.setFont(new Font("Serif", Font.PLAIN, 35));
		btnTravel.setBackground(new Color(220, 207, 180));
		btnTravel.setBounds(215, 369, 145, 110);
		OptionsPanel.add(btnTravel);
		
		JButton btnHunt = new JButton("Hunt");
		btnHunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHunt.setFont(new Font("Serif", Font.PLAIN, 35));
		btnHunt.setBackground(new Color(220, 207, 180));
		btnHunt.setBounds(421, 369, 145, 110);
		OptionsPanel.add(btnHunt);
		
		JPanel InventoryPanel = new JPanel();
		InventoryPanel.setLayout(null);
		InventoryPanel.setBackground(new Color(220, 207, 180));
		InventoryPanel.setBounds(894, 11, 308, 468);
		OptionsPanel.add(InventoryPanel);
		
		JLabel lblInventory = new JLabel("<html><u>Inventory</u>");
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setForeground(Color.BLACK);
		lblInventory.setFont(new Font("Serif", Font.PLAIN, 30));
		lblInventory.setBounds(10, 11, 288, 39);
		InventoryPanel.add(lblInventory);
		
		JPanel EventLogPanel = new JPanel();
		EventLogPanel.setLayout(null);
		EventLogPanel.setBackground(new Color(220, 207, 180));
		EventLogPanel.setBounds(576, 11, 308, 468);
		OptionsPanel.add(EventLogPanel);
		
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
		lblOverallGroupHealth.setForeground(Color.BLACK);
		lblOverallGroupHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblOverallGroupHealth.setBounds(281, 156, 153, 32);
		OptionsPanel.add(lblOverallGroupHealth);
		
		JLabel lblConstDistanceUntilNext = new JLabel("Distance Until Next Landmark:");
		lblConstDistanceUntilNext.setForeground(Color.WHITE);
		lblConstDistanceUntilNext.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceUntilNext.setBounds(38, 146, 406, 32);
		GamePanel.add(lblConstDistanceUntilNext);
		
		JLabel lblDistanceUntilNextLandmark = new JLabel("");
		lblDistanceUntilNextLandmark.setForeground(Color.WHITE);
		lblDistanceUntilNextLandmark.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceUntilNextLandmark.setBounds(443, 146, 153, 24);
		GamePanel.add(lblDistanceUntilNextLandmark);
		
		JLabel lblConstDistanceTraveled = new JLabel("Distance Traveled:");
		lblConstDistanceTraveled.setForeground(Color.WHITE);
		lblConstDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceTraveled.setBounds(38, 105, 256, 39);
		GamePanel.add(lblConstDistanceTraveled);
		
		JLabel lblDistanceTraveled = new JLabel("");
		lblDistanceTraveled.setForeground(Color.WHITE);
		lblDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceTraveled.setBounds(291, 119, 153, 24);
		GamePanel.add(lblDistanceTraveled);
		
		JLabel lblWeather = new JLabel("Clear");
		lblWeather.setForeground(Color.WHITE);
		lblWeather.setFont(new Font("Serif", Font.PLAIN, 30));
		lblWeather.setBounds(151, 70, 256, 32);
		GamePanel.add(lblWeather);
		lblWeather.setText(daily_events.handleWeatherEvent());
		
		
		JLabel lblConstWeather = new JLabel("Weather:");
		lblConstWeather.setForeground(Color.WHITE);
		lblConstWeather.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstWeather.setBounds(38, 67, 142, 39);
		GamePanel.add(lblConstWeather);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(38, 32, 83, 39);
		GamePanel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDate.setBounds(111, 47, 153, 24);
		GamePanel.add(lblDate);
		
		StartingOptionsPanel = new JPanel();
		StartingOptionsPanel.setBounds(0, 0, 1283, 688);
		StartingOptionsPanel.setBackground(new Color(154, 128, 71));
		frame.getContentPane().add(StartingOptionsPanel);
		StartingOptionsPanel.setLayout(null);
		StartingOptionsPanel.setVisible(false);
		
		txtPlayer1Name = new JTextField();
		txtPlayer1Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer1Name.setBounds(250, 30, 234, 35);
		StartingOptionsPanel.add(txtPlayer1Name);
		
		txtPlayer2Name = new JTextField();
		txtPlayer2Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer2Name.setColumns(10);
		txtPlayer2Name.setBounds(250, 80, 234, 35);
		StartingOptionsPanel.add(txtPlayer2Name);
		
		txtPlayer3Name = new JTextField();
		txtPlayer3Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer3Name.setColumns(10);
		txtPlayer3Name.setBounds(250, 130, 234, 35);
		StartingOptionsPanel.add(txtPlayer3Name);
		
		txtPlayer4Name = new JTextField();
		txtPlayer4Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer4Name.setColumns(10);
		txtPlayer4Name.setBounds(250, 180, 234, 35);
		StartingOptionsPanel.add(txtPlayer4Name);
		
		txtPlayer5Name = new JTextField();
		txtPlayer5Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer5Name.setColumns(10);
		txtPlayer5Name.setBounds(250, 230, 234, 35);
		StartingOptionsPanel.add(txtPlayer5Name);
		
		
		JLabel lblCPlayer1Name = new JLabel("Person 1's Name:");
		lblCPlayer1Name.setForeground(Color.WHITE);
		lblCPlayer1Name.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCPlayer1Name.setBounds(38, 30, 226, 39);
		StartingOptionsPanel.add(lblCPlayer1Name);
		
		JLabel lblCPlayer2Name = new JLabel("Person 2's Name:");
		lblCPlayer2Name.setForeground(Color.WHITE);
		lblCPlayer2Name.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCPlayer2Name.setBounds(38, 80, 226, 39);
		StartingOptionsPanel.add(lblCPlayer2Name);
		
		JLabel lblCPlayer3Name = new JLabel("Person 3's Name:");
		lblCPlayer3Name.setForeground(Color.WHITE);
		lblCPlayer3Name.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCPlayer3Name.setBounds(38, 130, 226, 39);
		StartingOptionsPanel.add(lblCPlayer3Name);
		
		JLabel lblCPlayer4Name = new JLabel("Person 4's Name:");
		lblCPlayer4Name.setForeground(Color.WHITE);
		lblCPlayer4Name.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCPlayer4Name.setBounds(38, 180, 226, 39);
		StartingOptionsPanel.add(lblCPlayer4Name);
		
		JLabel lblCPlayer5Name = new JLabel("Person 5's Name:");
		lblCPlayer5Name.setForeground(Color.WHITE);
		lblCPlayer5Name.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCPlayer5Name.setBounds(38, 230, 226, 39);
		StartingOptionsPanel.add(lblCPlayer5Name);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBackground(new Color(220, 207, 180));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartingOptionsPanel.setVisible(false);
				GamePanel.setVisible(true);
			}
		});
		btnContinue.setFont(new Font("Serif", Font.PLAIN, 45));
		btnContinue.setBounds(312, 550, 572, 110);
		StartingOptionsPanel.add(btnContinue);
		
		JPanel WelcomePanel = new JPanel();
		WelcomePanel.setBackground(new Color(154, 128, 71));
		WelcomePanel.setBounds(0, 0, 1283, 689);
		frame.getContentPane().add(WelcomePanel);
		WelcomePanel.setLayout(null);
		
		JLabel lblTitlePerils = new JLabel("Perils Along The Platte");
		lblTitlePerils.setForeground(Color.BLACK);
		lblTitlePerils.setFont(new Font("Serif", Font.PLAIN, 80));
		lblTitlePerils.setBackground(new Color(220, 207, 180));
		lblTitlePerils.setBounds(193, 5, 747, 102);
		WelcomePanel.add(lblTitlePerils);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePanel.setVisible(false);
				GamePanel.setVisible(false);
				StartingOptionsPanel.setVisible(true);
			}
		});
		btnStart.setBackground(new Color(220, 207, 180));
		btnStart.setFont(new Font("Serif", Font.BOLD, 80));
		btnStart.setBounds(193, 156, 815, 181);
		WelcomePanel.add(btnStart);
		
	}
}