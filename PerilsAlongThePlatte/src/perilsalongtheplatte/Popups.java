package perilsalongtheplatte;
import javax.swing.*;

/**
 * Popups contains the many Java popups the program will display to give educational content. These popups will be used to both collect input from the user
 * (such as names, day to rest, etc.) and display when the user experiences an event. Each popup utilizes the JOptionPane class to show specific popups. 
 * @author Parker West
 * 
 */

public class Popups {
	//create an object of the perils classes to get the 
	private Perils perils = new Perils(); 
	private static LandmarkDescriptions landDesc = new LandmarkDescriptions();
	
	/*******************
	 *OPTION MENU ITEMS*
	 *******************/
	//these menu items will be called when the user selects a button option, such as rest
	
	/**
	 * Creates a popup that will allow the user to enter the total number of days to rest. 
	 * @return the number of days the user selects to rest for. 
	 */
	public int restDays() {
		//create objects to be integrated into the JOptionPane
		int days; //value that will be stored
		final int MAX = 7; //the user can only rest a MAX of 7 days (1 week)
		final int MIN = 0; //useful if the user wants to exit without doing anything
		
		 SpinnerNumberModel model = new SpinnerNumberModel //creates the parameters of the spinner
		 (0, MIN, MAX, 1); //sets the bound/increments of the spinner. 
	     JSpinner spinner = new JSpinner(model); //creates the actual spinner object
		
		
		//popup dialog with a JSpinner to get input for the number of days to rest
		days = JOptionPane.showOptionDialog(
				null, 						  //no parent component, centers on screen
				spinner, 					  //inputs the spinner into the popup
				"How many days to rest?", 	  //title
				JOptionPane.OK_CANCEL_OPTION, //which buttons to display. This popup uses a OK/Cancel option to confirm
				JOptionPane.QUESTION_MESSAGE, //what icon to display. This popup uses the question icon
				null, 						  //no custom icon
				null, 						  //no custom buttons
				null);						  //no initial object selection
		//update days if the OK label is selected
		if (days == JOptionPane.OK_OPTION) {
		    days = (Integer) spinner.getValue(); //downcast to an integer value
		}
		return days; 
	}
	
	/**************
	 *EVENT POPUPS*
	 **************/
	//these popups run when the user reaches a critical event, such as a landmark, deadly peril, etc. 
	
	/**
	 * Create a popup that will display when the user encountered a deadly peril. Other perils appear in the Event Log. 
	 * @param message A string stating which peril the user encountered. 
	 */
	public void perilPopup(String message) {
		JOptionPane.showMessageDialog(
				null, 						   //no parent
				message, 					   //displays the passed String to the dialog area
				"Peril Encountered!",		   //<-- displays that title
				JOptionPane.WARNING_MESSAGE);  //display warning icon
				
	}

	/********************
	 *EDUCATIONAL POPUPS*
	 ********************/
	//these popups run when the user reaches an educational event, such as a landmark, 
	
	public void landmarkPopup(String landmark) {
		JOptionPane.showMessageDialog(
				null,
				"You have reached " + landmark + "!",
				"Landmark Reached!",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void educationalDescription(String landmark) {
		if (landmark.equals("Kansas River")) { JOptionPane.showMessageDialog(null, landDesc.kansasRiver, "Kansas River Information:", JOptionPane.INFORMATION_MESSAGE); }
		else if(landmark.equals("Big Blue River")){ JOptionPane.showMessageDialog(null, landDesc.bigBlueRiver, "Big Blue River Information:", JOptionPane.INFORMATION_MESSAGE); }
		else if(landmark.equals("Fort Kearny")){ JOptionPane.showMessageDialog(null, landDesc.fortKearny, "Fort Kearny Information:", JOptionPane.INFORMATION_MESSAGE); }
		else if(landmark.equals("Chimney Rock")){ JOptionPane.showMessageDialog(null, landDesc.chimneyRock, "Chimney Rock Information:", JOptionPane.INFORMATION_MESSAGE); }
		else if(landmark.equals("Fort Loramie")){ JOptionPane.showMessageDialog(null, landDesc.fortLaramie, "Fort Laramie Information:", JOptionPane.INFORMATION_MESSAGE); 
		JOptionPane.showMessageDialog(null, landDesc.endingInfo, "The End:", JOptionPane.INFORMATION_MESSAGE);}
	}

	public void descriptionHuntingInfo() {
		JOptionPane.showMessageDialog(null, landDesc.huntingInfo, "Hunting Information:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void descriptionTradingInfo() {
		JOptionPane.showMessageDialog(null, landDesc.tradingInfo, "Trading Information:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void descriptionIllnessInfo() {
		JOptionPane.showMessageDialog(null, landDesc.illnessInfo, "Illness Information:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void descriptionBeginningInfo() {
		JOptionPane.showMessageDialog(null, landDesc.beginningInfo, "Beginning Introduction:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mormonTrailInfo() {
		JOptionPane.showMessageDialog(null, landDesc.mormonTrail, "Mormon Trail Background:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void californiaTrailInfo() {
		JOptionPane.showMessageDialog(null, landDesc.californiaTrail, "California Trail Background:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void oregonTrailInfo() {
		JOptionPane.showMessageDialog(null, landDesc.oregonTrail, "Oregon Trail Background:", JOptionPane.INFORMATION_MESSAGE);
	}
}
