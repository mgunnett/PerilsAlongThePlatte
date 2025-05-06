package perilsalongtheplatte;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import perilsalongtheplatte.DailyEvents;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;


public class GAME {
	//UI widgets
	private JFrame frame;
	private JTextField txtPlayer1Name;
	private JTextField txtPlayer2Name;
	private JTextField txtPlayer3Name;
	private JTextField txtPlayer4Name;
	private JTextField txtPlayer5Name;
	JLabel lblDaysPassed;
	JLabel lblDistanceTraveled;
	JLabel lblWeather;
	JButton btnContinue;
	JPanel GamePanel;
	JPanel HuntingPanel;
	JPanel StartingOptionsPanel;
	JLabel lblRations;
	JSpinner spinnerRations;
	JSpinner spinnerSpeed;
	JLabel lblDistanceUntilNextLandmark;
	JLabel lblDate;
	JTextArea inventoryTextArea;
	JTextArea EventLogTextArea;
	JLabel lblResult;
	JLabel lblImageHolder;
	JLabel lblName1HealthValue;
	JLabel lblName2HealthValue;
	JLabel lblName3HealthValue;
	JLabel lblName4HealthValue;
	JLabel lblName5HealthValue;
	private boolean isMale = true;

	//weather icons
	ImageIcon sunnyIcon = new ImageIcon(getClass().getResource("/Images/Sunny.PNG"));
	ImageIcon rainyIcon = new ImageIcon(getClass().getResource("/Images/Rainy.PNG"));
	ImageIcon snowyIcon = new ImageIcon(getClass().getResource("/Images/Snowy.PNG"));
	ImageIcon windyIcon = new ImageIcon(getClass().getResource("/Images/Windy.PNG"));
	ImageIcon stormyIcon = new ImageIcon(getClass().getResource("/Images/Stormy.PNG"));

	//hunting game widgets
	private JTextField txtFldResponse;
	private ImageIcon icon;
	private JLabel lblBang2Shoot;
	private Timer timer;
	private JLabel imageHolder;
	private JLabel lblNewLabel1;
	private int counter = 0;
	private int meat = 100;
	private boolean closeGame = false;

	//custom game class instances
	private Popups popup = new Popups(); 
	private TravelDistance travelDistance;
	private DailyEvents daily_events;
	private TravelDistance pace;
	private Inventory inventory = new Inventory();
	private Party party = new Party(inventory);

	//game state variables
	private int rations; //stores the rations value, a number ranged [1-3]
	private String weather; //stores the current weather
	boolean isResting = false;

	
	
	

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
	    // Initialize core game systems
		 daily_events = new DailyEvents(100, pace, party); // Gender and health may change later

	    initialize(); // Set up the GUI components

	    // Set up spinner change listener once — outside the timer logic
	    spinnerSpeed.addChangeListener(e -> {
	        int speed = (Integer) spinnerSpeed.getValue();
	        travelDistance.setPace(speed);
	        //updateEventLog();
	    });

	    // Set up the TravelDistance system with its callback logic
	    travelDistance = new TravelDistance(() -> {
	        updateDayAndDistanceLabel();  // Update GUI for day and distance
	        updateInventory();            // Update supply inventory

	        // Run daily events (weather, sickness, penalties)
	        daily_events.updateEvents();

	        // Display updated weather
	        lblWeather.setText(daily_events.getCurrentWeather());
	        
	        // Variable to update weather picture
	        weather = daily_events.getCurrentWeather();
	        lblWeather.setText(weather);
	        
	     // Log today's events
	        updateEventLog();

	        
	        // Update the party's health
	        updatePartyHealth();
	        
	        
	        // Update weather image
	        updateWeatherImage(weather);

	        
	        // Refresh additional data
	        updateMilesLeftLabel();
	        updateDateLabel();
	        
	        //check if the game ends
	        checkIfGameIsDone();
	        System.out.println("loop ran!");
	    });

	    // Hook up the "Continue" button to start the game loop
	    btnContinue.addActionListener(e -> travelDistance.startTimer());
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

		GamePanel = new JPanel();
		GamePanel.setBackground(new Color(0, 0, 0));
		GamePanel.setBounds(0, 0, 1283, 699);
		frame.getContentPane().add(GamePanel);
		GamePanel.setLayout(null);
		GamePanel.setVisible(false);
		
		JPanel ShopPanel = new JPanel();
		ShopPanel.setBackground(new Color(156, 123, 82));
		ShopPanel.setBounds(0, -3, 1283, 702);
		frame.getContentPane().add(ShopPanel);
		ShopPanel.setLayout(null);
		ShopPanel.setVisible(false);
		
		lblImageHolder = new JLabel();
		lblImageHolder.setBounds(684, -3, 661, 201);
		GamePanel.add(lblImageHolder);
		GamePanel.repaint();
		
		StartingOptionsPanel = new JPanel();
		StartingOptionsPanel.setBounds(0, 0, 1283, 688);
		StartingOptionsPanel.setBackground(new Color(154, 128, 71));
		frame.getContentPane().add(StartingOptionsPanel);
		StartingOptionsPanel.setLayout(null);
		StartingOptionsPanel.setVisible(false);
		
		HuntingPanel = new JPanel();
		HuntingPanel.setBackground(new Color(154, 128, 71));
		HuntingPanel.setBounds(100, 100, 450, 300);
		frame.getContentPane().add(HuntingPanel);
		HuntingPanel.setLayout(null);
		HuntingPanel.setVisible(false);
		
		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(null);
		OptionsPanel.setBackground(new Color(154, 128, 71));
		OptionsPanel.setBounds(25, 189, 1212, 490);
		GamePanel.add(OptionsPanel);
		
		spinnerSpeed = new JSpinner(new SpinnerNumberModel(1,1,3,1));
		spinnerSpeed.setFont(new Font("Serif", Font.PLAIN, 20));
		spinnerSpeed.setBackground(Color.WHITE);
		spinnerSpeed.setBounds(115, 35, 172, 32);
		OptionsPanel.add(spinnerSpeed);
		
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setForeground(Color.BLACK);
		lblSpeed.setFont(new Font("Serif", Font.PLAIN, 30));
		lblSpeed.setBounds(12, 31, 93, 32);
		OptionsPanel.add(lblSpeed);
		
		lblRations = new JLabel("Rations:");
		lblRations.setForeground(Color.BLACK);
		lblRations.setFont(new Font("Serif", Font.PLAIN, 30));
		lblRations.setBounds(12, 78, 104, 32);
		OptionsPanel.add(lblRations);
		
		//create a SpinnerNumberModel with bounds
		SpinnerNumberModel rationsModel = new SpinnerNumberModel(1, 1, 3, 1);
	    spinnerRations = new JSpinner(rationsModel);
	    spinnerRations.addChangeListener(e -> {
	        rations = (int) spinnerRations.getValue();
	        System.out.println("Updated rations: " + rations);
	    });

		spinnerRations.setFont(new Font("Serif", Font.PLAIN, 20));
		spinnerRations.setBackground(new Color(224, 213, 188));
		spinnerRations.setBounds(115, 82, 172, 32);
		OptionsPanel.add(spinnerRations);
		
		JButton btnRest = new JButton("Rest");
		btnRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 travelDistance.pauseTimer();
				  int restDays = popup.restDays(); // show popup and get input
				  isResting = true; //boolean status of resting 
				  if (restDays > 0) {
					    travelDistance.startRest(restDays);
					   //set resting status here
					    inventory.addSupply(SupplyType.WATER, 8); //adds 8lbs of water per day
					    inventory.addSupply(SupplyType.BUFFALOCHIPS, 0.5); //ads 0.5lbs of buffalo chips per day
					    party.changeHealth(isResting); //make sure values update when resting
				  }
				  isResting = false; 
				  travelDistance.resumeTimer();


			}
		});
		btnRest.setFont(new Font("Serif", Font.PLAIN, 35));
		btnRest.setBackground(new Color(220, 207, 180));
		btnRest.setBounds(16, 369, 145, 110);
		OptionsPanel.add(btnRest);
		
		JButton btnTrade = new JButton("Trade");
		btnTrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory.tradeSupply();
			}
		});
		btnTrade.setFont(new Font("Serif", Font.PLAIN, 35));
		btnTrade.setBackground(new Color(220, 207, 180));
		btnTrade.setBounds(215, 369, 145, 110);
		OptionsPanel.add(btnTrade);
		
		lblResult = new JLabel("");
		JButton btnHunt = new JButton("Hunt");
		btnHunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HuntingPanel.setVisible(true);
				GamePanel.setVisible(false);
				
				travelDistance.pauseTimer();

				timer.start();

				imageHolder = new JLabel("                                  \\      . .(");
				imageHolder.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder.setBounds(95, 65, 246, 24);
				HuntingPanel.add(imageHolder);
				
				
				lblBang2Shoot = new JLabel("Type BANG to shoot");
				lblBang2Shoot.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
				lblBang2Shoot.setHorizontalAlignment(SwingConstants.CENTER);
				lblBang2Shoot.setBounds(109, 11, 218, 24);
				HuntingPanel.add(lblBang2Shoot);
				
				lblResult.setText("");
				lblResult.setHorizontalAlignment(SwingConstants.CENTER);
				lblResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblResult.setBounds(217, 159, 209, 35);
				HuntingPanel.add(lblResult);
				
				txtFldResponse = new JTextField();
				//txtFldResponse.setText("");
				txtFldResponse.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        timer.stop();
				        if (meat < 0) {
				            meat = 0;
				        }
				        String response1 = txtFldResponse.getText().trim();
				        if (response1.equalsIgnoreCase("bang")) {
				            if (meat > 0) {
				                lblResult.setText("You got " + meat + " meat.");
				            } else {
				                lblResult.setText("The deer ran away.");
				            }
				        } else {
				            lblResult.setText("You missed. :(");
				        }
				        closeGame = true;
				        txtFldResponse.setText("");
				        counter = 0;
				        timer.stop();
				        travelDistance.resumeTimer();
				        new Timer(2000, new ActionListener() {
				            @Override
				            public void actionPerformed(ActionEvent evt) {
				                txtFldResponse.setText("");
				                HuntingPanel.setVisible(false);
				                GamePanel.setVisible(true);
				                ((Timer) evt.getSource()).stop();
				                closeGame = false;
				            }
				        }).start();
				    }
				});
				txtFldResponse.setHorizontalAlignment(SwingConstants.CENTER);
				txtFldResponse.setFont(new Font("Tahoma", Font.PLAIN, 30));
				txtFldResponse.setBounds(170, 224, 96, 35);
				HuntingPanel.add(txtFldResponse);
				txtFldResponse.setColumns(10);
				
				lblNewLabel1 = new JLabel("Time");
				lblNewLabel1.setBounds(338, 122, 49, 14);
				HuntingPanel.add(lblNewLabel1);
				
				JLabel lblt = new JLabel("                                   |  __T |");
				lblt.setHorizontalAlignment(SwingConstants.LEFT);
				lblt.setBounds(95, 77, 246, 24);
				HuntingPanel.add(lblt);
				
				JLabel imageHolder_2 = new JLabel("                                  /       |");
				imageHolder_2.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_2.setBounds(95, 89, 246, 24);
				HuntingPanel.add(imageHolder_2);
				
				JLabel imageHolder_3 = new JLabel("         _.---======='          |");
				imageHolder_3.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_3.setBounds(95, 100, 246, 24);
				HuntingPanel.add(imageHolder_3);
				
				JLabel imageHolder_4 = new JLabel("     //                                 {}");
				imageHolder_4.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_4.setBounds(95, 112, 246, 24);
				HuntingPanel.add(imageHolder_4);
				
				JLabel imageHolder_5 = new JLabel("   `|             ,       ,            {}");
				imageHolder_5.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_5.setBounds(95, 124, 246, 24);
				HuntingPanel.add(imageHolder_5);
				
				JLabel imageHolder_6 = new JLabel("      \\            /___;          ,'");
				imageHolder_6.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_6.setBounds(95, 135, 246, 24);
				HuntingPanel.add(imageHolder_6);
				
				JLabel imageHolder_7 = new JLabel("                                  |`\\__/ /");
				imageHolder_7.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7.setBounds(95, 53, 246, 24);
				HuntingPanel.add(imageHolder_7);
				
				JLabel imageHolder_1 = new JLabel("       )      ,-;`       `\\      / /");
				imageHolder_1.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_1.setBounds(95, 147, 246, 24);
				HuntingPanel.add(imageHolder_1);
				
				JLabel imageHolder_7_1 = new JLabel("       |     /   (          ;|   | |");
				imageHolder_7_1.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7_1.setBounds(95, 159, 246, 24);
				HuntingPanel.add(imageHolder_7_1);
				
				JLabel imageHolder_7_2 = new JLabel("       |    |`\\  \\            |  | |");
				imageHolder_7_2.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7_2.setBounds(95, 170, 246, 24);
				HuntingPanel.add(imageHolder_7_2);
				
				JLabel imageHolder_7_3 = new JLabel("       |   |    \\  \\          |  | |");
				imageHolder_7_3.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7_3.setBounds(95, 182, 246, 24);
				HuntingPanel.add(imageHolder_7_3);
				
				JLabel imageHolder_7_4 = new JLabel("        )  \\     )  \\        )  | |");
				imageHolder_7_4.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7_4.setBounds(95, 194, 246, 24);
				HuntingPanel.add(imageHolder_7_4);
				
				JLabel imageHolder_7_5 = new JLabel("         `\"      `\"         `\"\"");
				imageHolder_7_5.setHorizontalAlignment(SwingConstants.LEFT);
				imageHolder_7_5.setBounds(95, 205, 246, 24);
				HuntingPanel.add(imageHolder_7_5);
				
				
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
		
		inventoryTextArea = new JTextArea();
		inventoryTextArea.setBounds(20, 61, 266, 396);
		InventoryPanel.add(inventoryTextArea);
		
		JButton btnBuyIntroSupplies = new JButton("Shop");
		btnBuyIntroSupplies.setBackground(new Color(220, 207, 180));
		btnBuyIntroSupplies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopPanel.setVisible(true);
				StartingOptionsPanel.setVisible(false);
			}
		});
		btnBuyIntroSupplies.setFont(new Font("Serif", Font.PLAIN, 30));
		btnBuyIntroSupplies.setBounds(937, 357, 214, 95);
		StartingOptionsPanel.add(btnBuyIntroSupplies);
		
		JLabel lblConstBuyIntroSupplies = new JLabel("Buy Starting Supplies!!!");
		lblConstBuyIntroSupplies.setHorizontalAlignment(SwingConstants.CENTER);
		lblConstBuyIntroSupplies.setForeground(new Color(255, 255, 255));
		lblConstBuyIntroSupplies.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstBuyIntroSupplies.setBounds(896, 308, 303, 46);
		StartingOptionsPanel.add(lblConstBuyIntroSupplies);
		
		JPanel EventLogPanel = new JPanel();
		EventLogPanel.setBackground(new Color(220, 207, 180));
		EventLogPanel.setBounds(576, 11, 308, 468);
		OptionsPanel.add(EventLogPanel);
		EventLogPanel.setLayout(null);
		
		JLabel lblEventLog = new JLabel("<html><u>Event Log</u>");
		lblEventLog.setBounds(10, 11, 288, 39);
		lblEventLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventLog.setForeground(Color.BLACK);
		lblEventLog.setFont(new Font("Serif", Font.PLAIN, 30));
		EventLogPanel.add(lblEventLog);
		
		EventLogTextArea = new JTextArea(10, 40); // 10 rows, 40 columns as example
		EventLogTextArea.setBounds(10, 61, 288, 396);
		EventLogPanel.add(EventLogTextArea);
		EventLogTextArea.setEditable(false);       // user cannot edit the log
		EventLogTextArea.setLineWrap(true);        // wrap lines nicely
		EventLogTextArea.setWrapStyleWord(true);   // wrap at word boundaries
		
		JLabel lblPlayer1NameHealth = new JLabel("Name1 Health:");
		lblPlayer1NameHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer1NameHealth.setForeground(Color.BLACK);
		lblPlayer1NameHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblPlayer1NameHealth.setBounds(12, 143, 216, 32);
		OptionsPanel.add(lblPlayer1NameHealth);
		
		JLabel lblPlayer2NameHealth = new JLabel("Name2 Health:");
		lblPlayer2NameHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer2NameHealth.setForeground(Color.BLACK);
		lblPlayer2NameHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblPlayer2NameHealth.setBounds(12, 184, 216, 32);
		OptionsPanel.add(lblPlayer2NameHealth);
		
		JLabel lblPlayer3NameHealth = new JLabel("Name3 Health:");
		lblPlayer3NameHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer3NameHealth.setForeground(Color.BLACK);
		lblPlayer3NameHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblPlayer3NameHealth.setBounds(12, 227, 216, 32);
		OptionsPanel.add(lblPlayer3NameHealth);
		
		JLabel lblPlayer4NameHealth = new JLabel("Name4 Health:");
		lblPlayer4NameHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer4NameHealth.setForeground(Color.BLACK);
		lblPlayer4NameHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblPlayer4NameHealth.setBounds(12, 270, 216, 32);
		OptionsPanel.add(lblPlayer4NameHealth);
		
		JLabel lblPlayer5NameHealth = new JLabel("Name5 Health:");
		lblPlayer5NameHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer5NameHealth.setForeground(Color.BLACK);
		lblPlayer5NameHealth.setFont(new Font("Serif", Font.PLAIN, 30));
		lblPlayer5NameHealth.setBounds(12, 313, 216, 32);
		OptionsPanel.add(lblPlayer5NameHealth);
		
		lblName1HealthValue = new JLabel("");
		lblName1HealthValue.setForeground(Color.BLACK);
		lblName1HealthValue.setFont(new Font("Serif", Font.PLAIN, 30));
		lblName1HealthValue.setBounds(215, 143, 153, 32);
		OptionsPanel.add(lblName1HealthValue);
		
		lblName2HealthValue = new JLabel("");
		lblName2HealthValue.setForeground(Color.BLACK);
		lblName2HealthValue.setFont(new Font("Serif", Font.PLAIN, 30));
		lblName2HealthValue.setBounds(215, 186, 153, 32);
		OptionsPanel.add(lblName2HealthValue);
		
	    lblName3HealthValue = new JLabel("");
		lblName3HealthValue.setForeground(Color.BLACK);
		lblName3HealthValue.setFont(new Font("Serif", Font.PLAIN, 30));
		lblName3HealthValue.setBounds(215, 227, 153, 32);
		OptionsPanel.add(lblName3HealthValue);
		
		lblName4HealthValue = new JLabel("");
		lblName4HealthValue.setForeground(Color.BLACK);
		lblName4HealthValue.setFont(new Font("Serif", Font.PLAIN, 30));
		lblName4HealthValue.setBounds(215, 270, 153, 32);
		OptionsPanel.add(lblName4HealthValue);
		
		lblName5HealthValue = new JLabel("");
		lblName5HealthValue.setForeground(Color.BLACK);
		lblName5HealthValue.setFont(new Font("Serif", Font.PLAIN, 30));
		lblName5HealthValue.setBounds(215, 313, 153, 32);
		OptionsPanel.add(lblName5HealthValue);
		
		JLabel lblConstDistanceUntilNext = new JLabel("Distance Until Next Landmark:");
		lblConstDistanceUntilNext.setForeground(Color.WHITE);
		lblConstDistanceUntilNext.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceUntilNext.setBounds(38, 146, 406, 32);
		GamePanel.add(lblConstDistanceUntilNext);
		
		lblDistanceUntilNextLandmark = new JLabel("");
		lblDistanceUntilNextLandmark.setForeground(Color.WHITE);
		lblDistanceUntilNextLandmark.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceUntilNextLandmark.setBounds(417, 154, 153, 24);
		GamePanel.add(lblDistanceUntilNextLandmark);
		
		JLabel lblConstDistanceTraveled = new JLabel("Distance Traveled:");
		lblConstDistanceTraveled.setForeground(Color.WHITE);
		lblConstDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblConstDistanceTraveled.setBounds(38, 105, 256, 39);
		GamePanel.add(lblConstDistanceTraveled);
		
		lblDistanceTraveled = new JLabel("");
		lblDistanceTraveled.setForeground(Color.WHITE);
		lblDistanceTraveled.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDistanceTraveled.setBounds(271, 111, 153, 24);
		GamePanel.add(lblDistanceTraveled);
		
		lblWeather = new JLabel("Clear");
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
		
		lblDate = new JLabel("");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDate.setBounds(111, 39, 313, 32);
		GamePanel.add(lblDate);
		
		JLabel lblCDaysPassed = new JLabel("Days Passed:");
		lblCDaysPassed.setForeground(Color.WHITE);
		lblCDaysPassed.setFont(new Font("Serif", Font.PLAIN, 30));
		lblCDaysPassed.setBounds(38, -3, 171, 39);
		GamePanel.add(lblCDaysPassed);
		
		lblDaysPassed = new JLabel("");
		lblDaysPassed.setForeground(Color.WHITE);
		lblDaysPassed.setFont(new Font("Serif", Font.PLAIN, 30));
		lblDaysPassed.setBounds(200, -3, 153, 39);
		GamePanel.add(lblDaysPassed);
		
		txtPlayer1Name = new JTextField();
		txtPlayer1Name.setFont(new Font("Serif", Font.PLAIN, 30));
		txtPlayer1Name.setBounds(250, 30, 234, 35);
		txtPlayer1Name.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String input = txtPlayer1Name.getText();
		        System.out.println("Player 1 name entered: " + input);
		    }
		});
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
		
		btnContinue = new JButton("Continue");
		btnContinue.setBackground(new Color(220, 207, 180));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create objects of the Party class and assign Pioneer objects a name
				Pioneer person1 = new Pioneer(txtPlayer1Name.getText());
				Pioneer person2 = new Pioneer(txtPlayer2Name.getText());
				Pioneer person3 = new Pioneer(txtPlayer3Name.getText());
				Pioneer person4 = new Pioneer(txtPlayer4Name.getText());
				Pioneer person5 = new Pioneer(txtPlayer5Name.getText());
				//add each pioneer into the Party class
				party.addPioneer(person1);
				party.addPioneer(person2);
				party.addPioneer(person3);
				party.addPioneer(person4);
				party.addPioneer(person5);
				
				System.out.println(person1.getName()); 
				
				 int currentPace = (Integer) spinnerSpeed.getValue();
				    travelDistance.setPace(currentPace);
				    travelDistance.startTimer();
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
		lblTitlePerils.setBounds(193, 5, 900, 102);
		WelcomePanel.add(lblTitlePerils);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Popups.descriptionBeginningInfo();
				WelcomePanel.setVisible(false);
				GamePanel.setVisible(false);
				StartingOptionsPanel.setVisible(true);
				
			}
		});
		btnStart.setBackground(new Color(220, 207, 180));
		btnStart.setFont(new Font("Serif", Font.BOLD, 80));
		btnStart.setBounds(193, 156, 815, 181);
		WelcomePanel.add(btnStart);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Serif", Font.PLAIN, 35));
		rdbtnMale.setBackground(new Color(220, 207, 180));
		rdbtnMale.setBounds(853, 30, 167, 46);
		StartingOptionsPanel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Serif", Font.PLAIN, 35));
		rdbtnFemale.setBackground(new Color(220, 207, 180));
		rdbtnFemale.setBounds(1053, 30, 167, 46);
		StartingOptionsPanel.add(rdbtnFemale);
		
		 ButtonGroup group = new ButtonGroup();
	        group.add(rdbtnMale);
	        group.add(rdbtnFemale);
	        
	        rdbtnMale.addActionListener(e -> isMale = true);
	        rdbtnFemale.addActionListener(e -> isMale = false);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Serif", Font.PLAIN, 30));
		lblGender.setBounds(739, 30, 108, 39);
		StartingOptionsPanel.add(lblGender);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(200, 186, 162));
		panel.setBounds(24, 152, 1237, 525);
		ShopPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblFood = new JLabel("Food:");
		lblFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblFood.setFont(new Font("Serif", Font.PLAIN, 35));
		lblFood.setBounds(77, 88, 89, 75);
		ShopPanel.add(lblFood);
		
		JButton btnFlour = new JButton("Flour");
		btnFlour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.FLOUR, isMale); 
			}; 
		});
		btnFlour.setFont(new Font("Serif", Font.PLAIN, 20));
		btnFlour.setBackground(new Color(203, 182, 156));
		btnFlour.setBounds(10, 14, 104, 52);
		panel.add(btnFlour);
		
		JLabel lblNewLabel_1 = new JLabel("$0.02");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(124, 25, 62, 30);
		panel.add(lblNewLabel_1);
		
		JButton btnBacon = new JButton("Bacon");
		btnBacon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.BACON, isMale); 
			}
		});
		btnBacon.setFont(new Font("Serif", Font.PLAIN, 20));
		btnBacon.setBackground(new Color(203, 182, 156));
		btnBacon.setBounds(10, 80, 104, 52);
		panel.add(btnBacon);
		
		JLabel lblNewLabel_1_1 = new JLabel("$0.05");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(124, 90, 62, 30);
		panel.add(lblNewLabel_1_1);
		
		JButton btnFruit = new JButton("Fruit");
		btnFruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.FRUIT, isMale); 
			}
		});
		btnFruit.setFont(new Font("Serif", Font.PLAIN, 20));
		btnFruit.setBackground(new Color(203, 182, 156));
		btnFruit.setBounds(10, 144, 104, 52);
		panel.add(btnFruit);
		
		JLabel lblNewLabel_1_2 = new JLabel("$0.06");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(124, 155, 62, 30);
		panel.add(lblNewLabel_1_2);
		
		JButton btnVeggies = new JButton("Veggies");
		btnVeggies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.VEGGIES, isMale); 
			}
		});
		btnVeggies.setFont(new Font("Serif", Font.PLAIN, 20));
		btnVeggies.setBackground(new Color(203, 182, 156));
		btnVeggies.setBounds(10, 207, 104, 52);
		panel.add(btnVeggies);
		
		JLabel lblNewLabel_1_3 = new JLabel("$0.06");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(124, 216, 62, 30);
		panel.add(lblNewLabel_1_3);
		
		JButton btnMeat = new JButton("Meat");
		btnMeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.MEAT, isMale); 
			}
		});
		btnMeat.setFont(new Font("Serif", Font.PLAIN, 20));
		btnMeat.setBackground(new Color(203, 182, 156));
		btnMeat.setBounds(10, 270, 104, 52);
		panel.add(btnMeat);
		
		JLabel lblNewLabel_1_4 = new JLabel("$0.10");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(124, 281, 62, 30);
		panel.add(lblNewLabel_1_4);
		
		JButton btnCoffee = new JButton("Coffee");
		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.COFFEE, isMale); 
			}
		});
		btnCoffee.setFont(new Font("Serif", Font.PLAIN, 20));
		btnCoffee.setBackground(new Color(203, 182, 156));
		btnCoffee.setBounds(10, 333, 104, 52);
		panel.add(btnCoffee);
		
		JLabel lblNewLabel_1_5 = new JLabel("$0.10");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(124, 343, 62, 30);
		panel.add(lblNewLabel_1_5);
		
		JButton btnTea = new JButton("Tea");
		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.TEA, isMale); 
			}
		});
		btnTea.setFont(new Font("Serif", Font.PLAIN, 20));
		btnTea.setBackground(new Color(203, 182, 156));
		btnTea.setBounds(10, 396, 104, 52);
		panel.add(btnTea);
		
		JLabel lblNewLabel_1_6 = new JLabel("$0.60");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(124, 407, 62, 30);
		panel.add(lblNewLabel_1_6);
		
		JButton btnLard = new JButton("Lard");
		btnLard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.LARD, isMale); 
			}
		});
		btnLard.setFont(new Font("Serif", Font.PLAIN, 20));
		btnLard.setBackground(new Color(203, 182, 156));
		btnLard.setBounds(10, 461, 104, 52);
		panel.add(btnLard);
		
		JLabel lblNewLabel_1_7 = new JLabel("$0.05");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_7.setBounds(124, 471, 62, 30);
		panel.add(lblNewLabel_1_7);
		
		JButton btnWheels = new JButton("Wheels");
		btnWheels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.WHEELS, isMale); 
			}
		});
		btnWheels.setFont(new Font("Serif", Font.PLAIN, 20));
		btnWheels.setBackground(new Color(203, 182, 156));
		btnWheels.setBounds(310, 14, 104, 52);
		panel.add(btnWheels);
		
		JLabel lblNewLabel_1_8 = new JLabel("$0.15");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_8.setBounds(424, 25, 62, 30);
		panel.add(lblNewLabel_1_8);
		
		JButton btnAxels = new JButton("Axels");
		btnAxels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.AXELS, isMale); 
			}
		});
		btnAxels.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAxels.setBackground(new Color(203, 182, 156));
		btnAxels.setBounds(310, 80, 104, 52);
		panel.add(btnAxels);
		
		JLabel lblNewLabel_1_9 = new JLabel("$0.15");
		lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_9.setBounds(424, 90, 62, 30);
		panel.add(lblNewLabel_1_9);
		
		JButton btnTongues = new JButton("Tongues");
		btnTongues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.TONGUES, isMale); 
			}
		});
		btnTongues.setFont(new Font("Serif", Font.PLAIN, 18));
		btnTongues.setBackground(new Color(203, 182, 156));
		btnTongues.setBounds(310, 144, 104, 52);
		panel.add(btnTongues);
		
		JLabel lblNewLabel_1_10 = new JLabel("$0.15");
		lblNewLabel_1_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_10.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_10.setBounds(424, 155, 62, 30);
		panel.add(lblNewLabel_1_10);
		
		JButton btnAmmo = new JButton("Ammo");
		btnAmmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAmmo.setFont(new Font("Serif", Font.PLAIN, 20));
		btnAmmo.setBackground(new Color(203, 182, 156));
		btnAmmo.setBounds(585, 14, 104, 52);
		panel.add(btnAmmo);
		
		JLabel lblNewLabel_1_11 = new JLabel("$2.75");
		lblNewLabel_1_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_11.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_11.setBounds(699, 25, 62, 30);
		panel.add(lblNewLabel_1_11);
		
		JButton btnOxen = new JButton("Oxen");
		btnOxen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.OXEN, isMale); 
			}
		});
		btnOxen.setFont(new Font("Serif", Font.PLAIN, 20));
		btnOxen.setBackground(new Color(203, 182, 156));
		btnOxen.setBounds(585, 80, 104, 52);
		panel.add(btnOxen);
		
		JLabel lblNewLabel_1_12 = new JLabel("$35.00");
		lblNewLabel_1_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_12.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_12.setBounds(699, 90, 62, 30);
		panel.add(lblNewLabel_1_12);
		
		JButton btnMedicine = new JButton("Medicine");
		btnMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.MEDICINE, isMale); 
			}
		});
		btnMedicine.setFont(new Font("Serif", Font.PLAIN, 18));
		btnMedicine.setBackground(new Color(203, 182, 156));
		btnMedicine.setBounds(585, 144, 104, 52);
		panel.add(btnMedicine);
		
		JLabel lblNewLabel_1_13 = new JLabel("$36.00");
		lblNewLabel_1_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_13.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_13.setBounds(699, 155, 62, 30);
		panel.add(lblNewLabel_1_13);
		
		JButton btnClothes = new JButton("Clothes");
		btnClothes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.CLOTHES, isMale); 
			}
		});
		btnClothes.setFont(new Font("Serif", Font.PLAIN, 20));
		btnClothes.setBackground(new Color(203, 182, 156));
		btnClothes.setBounds(585, 207, 104, 52);
		panel.add(btnClothes);
		
		JLabel lblNewLabel_1_14 = new JLabel("$8.00");
		lblNewLabel_1_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_14.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_14.setBounds(699, 216, 62, 30);
		panel.add(lblNewLabel_1_14);
		
		JButton btnSoap = new JButton("Soap");
		btnSoap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.buySupply(SupplyType.SOAP, isMale); 
			}
		});
		btnSoap.setFont(new Font("Serif", Font.PLAIN, 20));
		btnSoap.setBackground(new Color(203, 182, 156));
		btnSoap.setBounds(585, 270, 104, 52);
		panel.add(btnSoap);
		
		JLabel lblNewLabel_1_15 = new JLabel("$0.15");
		lblNewLabel_1_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_15.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_15.setBounds(699, 281, 62, 30);
		panel.add(lblNewLabel_1_15);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblShop.setFont(new Font("Serif", Font.BOLD, 60));
		lblShop.setBounds(464, 0, 256, 88);
		ShopPanel.add(lblShop);
		
		JLabel lblParts = new JLabel("Wagon Parts:");
		lblParts.setHorizontalAlignment(SwingConstants.CENTER);
		lblParts.setFont(new Font("Serif", Font.PLAIN, 35));
		lblParts.setBounds(309, 81, 218, 88);
		ShopPanel.add(lblParts);
		
		JLabel lblMisc = new JLabel("Misc:");
		lblMisc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisc.setFont(new Font("Serif", Font.PLAIN, 35));
		lblMisc.setBounds(649, 91, 89, 69);
		ShopPanel.add(lblMisc);
		
		JLabel lblWallet = new JLabel("Wallet:");
		lblWallet.setHorizontalAlignment(SwingConstants.CENTER);
		lblWallet.setFont(new Font("Serif", Font.PLAIN, 35));
		lblWallet.setBounds(947, 88, 218, 75);
		ShopPanel.add(lblWallet);
		
		JLabel lblWalletAmount = new JLabel("$500.00");
		lblWalletAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalletAmount.setFont(new Font("Serif", Font.PLAIN, 25));
		lblWalletAmount.setBounds(925, 0, 218, 52);
		panel.add(lblWalletAmount);
//		lblWalletAmount.setText("" + inventory.getSupply(SupplyType.CASH));
		
		JButton btnExitShop = new JButton("Exit Shop");
		btnExitShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopPanel.setVisible(false);
				StartingOptionsPanel.setVisible(true);
			}
		});
		btnExitShop.setFont(new Font("Serif", Font.PLAIN, 40));
		btnExitShop.setBounds(933, 446, 254, 68);
		panel.add(btnExitShop);
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				timerActionPerformed();
			}
		});
	}
	
	
	
	private void timerActionPerformed() {
		counter++;
		lblNewLabel1.setText("" + counter);
		meat -= 10;

		if (closeGame == true) {
			timer.stop();
			
			GamePanel.setVisible(true);
			HuntingPanel.setVisible(false);
		}
	}
	
	 private void updateDayAndDistanceLabel() {
	        // Changes days past label
		 String daysPassed = String.valueOf(travelDistance.daysTraveled());
	        lblDaysPassed.setText(daysPassed);
	        
	        // Changes distance traveled label
	     String distanceTraveled = String.valueOf(travelDistance.distanceTraveled());
	     	lblDistanceTraveled.setText(distanceTraveled);
	}


	 public void updateMilesLeftLabel() {
		 int milesLeft = travelDistance.getMilesLeftToNextLandmark();
		 lblDistanceUntilNextLandmark.setText(String.valueOf(milesLeft));
	 }
	 
	 public void updateDateLabel() {
		 String currentDate = travelDistance.date();  // get formatted date string
		    lblDate.setText(currentDate);
	 }
	 
	 //update the inventory text area daily 
	 private void updateInventory() {
		 inventoryTextArea.setText(""); //reset the text area
		 inventory.loseSupply(rations, party.isAnyoneSick(), party.isAnyoneDead(), party.getPartySize()); //lose the daily amount of supplies
         String units; //string to format the units
         for (SupplyType supply : SupplyType.values()) {
        	 if(supply.equals(SupplyType.CASH))
        		 units = "dollars";
        	 else
        		 units = "lbs"; 
        	 //format the string removing tailing 0s and creating columns 
        	 double value = inventory.getSupply(supply);
        	 int space =  15 - supply.name().length(); 
        	 String amount = String.format("%-" + space + "s : %7.2f %s\n", supply.name(), value, units);
             inventoryTextArea.append(amount); 
         }
	 }
	 
	 //update the event log with the many events from dailyEvents
	 private void updateEventLog() {
		 //display starting from top to bottom
		 EventLogTextArea.setText(""); //clear the text area
		 EventLogTextArea.append("\n" + travelDistance.date() + "\n"); 
		 String weatherTxt; 
		 switch (weather) {
		 case "Sunny":
			 weatherTxt = "It is bright and sunny today. You feel calm..."; 
			 break;
		 case "Thunderstorms":
			 weatherTxt = "It's comin' down buckets!";
			 break;
		 case "Rainy": 
			 weatherTxt = "Ain't no dust here, just mudpies.";
			 break; 
		 case "Windy":
			 weatherTxt = "The wind blows you around. You better watch you hat!";
			 break;
		 case "Snowy": 
			weatherTxt = "It's now a winter wonderland.";
			break; 
			default:
				weatherTxt = "You're grateful for a calm day :-)";
				break; 
		 }
		 
		 EventLogTextArea.append(weatherTxt);
//		 EventLogTextArea.append("\nHealth Status:   " + daily_events.getYorNSick()); //health status
//		 EventLogTextArea.append("\nRecovery Update: " + daily_events.getYorNRecovered()); 
//		 EventLogTextArea.append("\nPenalty        : " + daily_events.getPenalty()); 
//		 EventLogTextArea.append("\nSickness       : " + daily_events.getSickness()); 
		
	 }
	 private void resetMinigame() {
		    meat = 100;// some initial value
		    counter = 0;
		    closeGame = false;
		    txtFldResponse.setText("");
		    lblResult.setText("");
		    lblNewLabel1.setText(""); // or whatever default
		}
	 
	 private void updateWeatherImage(String weather) {
		    switch (weather) {
		        case "Sunny":
		            lblImageHolder.setIcon(sunnyIcon);
		            break;
		        case "Rainy":
		            lblImageHolder.setIcon(rainyIcon);
		            break;
		        case "Windy":
		            lblImageHolder.setIcon(windyIcon);
		            break;
		        case "Thunderstorms":
		            lblImageHolder.setIcon(stormyIcon);
		            break;
		        case "Snowy":
		            lblImageHolder.setIcon(snowyIcon);
		            break;
		        default:
		            lblImageHolder.setIcon(sunnyIcon); // I NEED A CLEAR PICTURE!!!!
		            break;
		    }
		    lblImageHolder.repaint(); // Ensure it redraws
		}
	 
	//a method to call other methods to update the health of each pioneer
		 private void updatePartyHealth() {
			 party.changeHealth(isResting); //change the health
			 //now display the updated health values to the GUI
			 int counter = 1; //a counter to keep track of which text field to update with strings
			 for (Pioneer p : party.getParty()) {
				 switch (counter) {
				 case 1:
					 lblName1HealthValue.setText("" + p.getHealth());
					 break;
				 case 2: 
					 lblName2HealthValue.setText("" + p.getHealth());
					 break;
				 case 3:
					 lblName3HealthValue.setText("" + p.getHealth());
					 break;
				 case 4: 
					 lblName4HealthValue.setText("" + p.getHealth());
					 break; 
				 case 5: 
					 lblName5HealthValue.setText("" + p.getHealth());
					 break;
				 }
				 counter++; 
				
				 //update event log if the pioneer is sick
				 EventLogTextArea.append("\n"); // space between entries

				 String name = p.getName();

				 //log sickness 
				 if (p.isSick()) {
					 if (p.getDeathStatus())
						 continue; 
				     Sickness sickness = p.getSicknessType();
				     String sicknessStr = sickness.toString().toLowerCase();
				     EventLogTextArea.append("• " + name + " is sick with " + sicknessStr + ". Suffer a " 
				         + sickness.getPenalty() + " health penalty.\n");
				 }

				 //log injury
				 if (p.getInjuredStatus()) {
				     Injury injury = p.getInjuryType();
				     String injuryStr = injury.toString().toLowerCase();
				     EventLogTextArea.append("• " + name + " is injured with " + injuryStr + ". Suffer a " 
				         + injury.getPenalty() + " health penalty.\n");
				 }
				 

				 //log death (only once)
				 if (p.getDeathStatus() && !p.hasDeathBeenLogged()) {
				     EventLogTextArea.append("☠ " + name + " has died. May they rest in peace.\n");
				     p.setDeathLogged(true);
				 }

			 }
			 
			 //log if the party is starving
			 if (inventory.outOfFood()) {
				 EventLogTextArea.append("You have no food! Get some now or suffer a -10 health penalty!\n");
			 }
		}
		 
		 //helper method to check if the game has ended
		 private void checkIfGameIsDone() {
			 if (popup.isGameDone()) {
				 travelDistance.pauseTimer();
				
				frame.setVisible(false);
				frame.dispose();
			 }
			 if (party.isPartyDead()) {
				 travelDistance.pauseTimer();
				 popup.descriptionIllnessInfo();
				 frame.setVisible(false);
				 frame.dispose(); 
			 }
		}
		 
		private void updateScore() {
			ScoreCalculation scoreCalculator = new ScoreCalculation(inventory.getTotalAmount(), party.getOverallHealth(), 
																	party.getPartySize(), travelDistance.daysTraveled(), isMale); 
			double totalScore = scoreCalculator.getScore();
			//update labels here
		}
		 
}