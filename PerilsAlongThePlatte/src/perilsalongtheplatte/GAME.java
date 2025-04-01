package perilsalongtheplatte;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Scanner;

public class GAME { 

	private JFrame frame;
	
	JLabel Title;
	JRadioButton rdbtnFemale;
	boolean isMale;
	JRadioButton rdbtnMale;
	JLabel MaleOrFemalelbl;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txt_PlayerName;
	private JTextField txt_Person1Name;
	private JTextField txt_Person2Name;
	private JTextField txt_Person3Name;
	private JTextField txt_Person4Name;
	String playerName;
	String person1Name;
	String person2Name;
	String person3Name;
	String person4Name;
	 
	private StartingStats stats = new StartingStats(isMale); 
	private JButton StartTripButton;
	private JButton TravelButton;
	private JLabel lbl_DistanceTraveled;
	private JLabel lbl_DistanceTraveledValue;
	
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
		frame.setBounds(100, 100, 781, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TravelButton = new JButton("Travel");
		TravelButton.setVisible(false);
		TravelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		TravelButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		TravelButton.setBounds(232, 248, 310, 84);
		frame.getContentPane().add(TravelButton);
		
		MaleOrFemalelbl = new JLabel("Male Or Female:");
		MaleOrFemalelbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MaleOrFemalelbl.setBounds(50, 36, 245, 61);
		frame.getContentPane().add(MaleOrFemalelbl);
		MaleOrFemalelbl.setVisible(false);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMale.isSelected()) {
					rdbtnFemale.setEnabled(false);
					isMale = true;
				}
			}
		});
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnMale.setBounds(289, 46, 96, 48);
		frame.getContentPane().add(rdbtnMale);
		rdbtnMale.setVisible(false);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFemale.isSelected()) {
					rdbtnMale.setEnabled(false);
					isMale = false;
				}
			}
		});
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnFemale.setBounds(390, 46, 96, 48);
		frame.getContentPane().add(rdbtnFemale);
		rdbtnFemale.setVisible(false);
		
		Title = new JLabel("Perils Along the Platte");
		Title.setForeground(new Color(0, 0, 0));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Title.setBounds(26, 52, 681, 110);
		frame.getContentPane().add(Title);
		
		lblNewLabel = new JLabel("Traveller Names:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(50, 108, 245, 61);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		lblNewLabel_1 = new JLabel("Your Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(119, 169, 115, 25);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		lblNewLabel_2 = new JLabel("Person #1:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(119, 205, 115, 25);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("Person #2:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(119, 244, 115, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Person #3:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(119, 280, 115, 25);
		frame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		lblNewLabel_5 = new JLabel("Person #4:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(119, 316, 115, 25);
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		txt_PlayerName = new JTextField();
		txt_PlayerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerName = txt_PlayerName.getText();
			}
		});
		txt_PlayerName.setBounds(232, 169, 310, 32);
		frame.getContentPane().add(txt_PlayerName);
		txt_PlayerName.setColumns(10);
		txt_PlayerName.setVisible(false);
		
		txt_Person1Name = new JTextField();
		txt_Person1Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				person1Name = txt_Person1Name.getText();
			}
		});
		txt_Person1Name.setColumns(10);
		txt_Person1Name.setBounds(232, 205, 310, 32);
		frame.getContentPane().add(txt_Person1Name);
		txt_Person1Name.setVisible(false);
		
		txt_Person2Name = new JTextField();
		txt_Person2Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				person2Name = txt_Person2Name.getText();
			}
		});
		txt_Person2Name.setColumns(10);
		txt_Person2Name.setBounds(232, 241, 310, 32);
		frame.getContentPane().add(txt_Person2Name);
		txt_Person2Name.setVisible(false);
		
		txt_Person3Name = new JTextField();
		txt_Person3Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				person3Name = txt_Person3Name.getText();
			}
		});
		txt_Person3Name.setColumns(10);
		txt_Person3Name.setBounds(232, 280, 310, 32);
		frame.getContentPane().add(txt_Person3Name);
		txt_Person3Name.setVisible(false);
		
		txt_Person4Name = new JTextField();
		txt_Person4Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				person4Name = txt_Person4Name.getText();
			}
		});
		txt_Person4Name.setColumns(10);
		txt_Person4Name.setBounds(232, 316, 310, 32);
		frame.getContentPane().add(txt_Person4Name);
		txt_Person4Name.setVisible(false);
		
		JButton StartButton = new JButton("Start Game");
		StartButton.setForeground(new Color(0, 0, 0));
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Title.setVisible(false);
				StartButton.setVisible(false);
				rdbtnMale.setVisible(true);
				rdbtnFemale.setVisible(true);
				MaleOrFemalelbl.setVisible(true);
				lblNewLabel.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblNewLabel_3.setVisible(true);
				lblNewLabel_4.setVisible(true);
				lblNewLabel_5.setVisible(true);
				txt_PlayerName.setVisible(true);
				txt_Person1Name.setVisible(true);
				txt_Person2Name.setVisible(true);
				txt_Person3Name.setVisible(true);
				txt_Person4Name.setVisible(true);
				StartTripButton.setVisible(true);
				
			}
		});
		StartButton.setFont(new Font("Tahoma", Font.PLAIN, 45));
		StartButton.setBounds(95, 227, 565, 115);
		frame.getContentPane().add(StartButton);
		
		StartTripButton = new JButton("Start Trip");
		StartTripButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				lblNewLabel_5.setVisible(false);
				txt_PlayerName.setVisible(false);
				txt_Person1Name.setVisible(false);
				txt_Person2Name.setVisible(false);
				txt_Person3Name.setVisible(false);
				txt_Person4Name.setVisible(false);
				StartTripButton.setVisible(false);
				rdbtnFemale.setVisible(false);
				rdbtnMale.setVisible(false);
				MaleOrFemalelbl.setVisible(false);
				TravelButton.setVisible(true);
				lbl_DistanceTraveledValue.setVisible(true);
				lbl_DistanceTraveled.setVisible(true);

			}
		});
		StartTripButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		StartTripButton.setBounds(156, 496, 431, 70);
		frame.getContentPane().add(StartTripButton);
		
		lbl_DistanceTraveledValue = new JLabel("0");
		lbl_DistanceTraveledValue.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lbl_DistanceTraveledValue.setBounds(353, 374, 108, 70);
		frame.getContentPane().add(lbl_DistanceTraveledValue);
		
		
		lbl_DistanceTraveled = new JLabel("Distance Traveled: ");
		lbl_DistanceTraveled.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_DistanceTraveled.setBounds(172, 381, 204, 48);
		frame.getContentPane().add(lbl_DistanceTraveled);
		lbl_DistanceTraveled.setVisible(false);
		lbl_DistanceTraveledValue.setVisible(false);
		StartTripButton.setVisible(false);
	}
}
