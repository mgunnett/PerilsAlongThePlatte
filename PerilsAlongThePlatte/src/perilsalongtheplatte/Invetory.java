package perilsalongtheplatte;
import java.util.Map;
import java.util.EnumMap; 
import javax.swing.*;

/**
 * This class handles all methods related to the management of the inventory, including supplies lost from daily travel, the shop, etc. This class
 * relies on the enum SupplyType to store the construct for each supply type and its corresponding price. This class needs help from 
 * an additional one to handle the graphics associated with the shop; these methods can only generate JOptionPanes. 
 * @author Parker R. West
 * @version 1.0
 */
public class Invetory {
	
	//in order to associate a value with the corresponding enum, a enumMap will be used to associate a value (the amount)
	//with its corresponding enum
	public Map<SupplyType, Double> supplies = new EnumMap<>(SupplyType.class); 
	
	//now with each value created within a map, a constructor is needed to initialize the map
	public Invetory() {
		//utilizing a for each loop...
		for (SupplyType supply : SupplyType.values()) { 
			supplies.put(supply, 0.0); 
			//special case for the player's cash
			if (supply.equals(SupplyType.CASH))
				supplies.put(supply, 1000.0); 
		}
	}
	
	/**
	 * A polymorphic method to buy a type of supply from the store. This method needs the kind of supply to buy and the unit price. 
	 * Once bought, the amount bought is added to the user's inventory and cash is subtracted form the inventory. 
	 * @param supply Integer representation of the supply to purchase. Reference the public integers to use as parameters. 
	 * @param price The unit price ($/lb) of the supply to buy. Again, reference public doubles in this class. 
	 */
	public void buySupply(SupplyType supply) {
		//create a variable for the price of the item and the current amount
		final double PRICE  = supply.getPrice(); 
		final double CURRENT_AMOUNT = supplies.getOrDefault(supply, 0.0); //gets the current value of the supply, returns 0 if there is no value
		
		//create a spinner object
		final int MAX = (int) (supplies.get(SupplyType.CASH)/ PRICE); //max amount player can purchase. Determined by the cash divided by price
		final int MIN = 0; //useful if the user wants to exit without doing anything
		SpinnerNumberModel model = new SpinnerNumberModel //creates the parameters of the spinner
		(0, MIN, MAX, 1); //sets the bound/increments of the spinner. 
	    JSpinner spinner = new JSpinner(model); //creates the actual spinner object
		//start with asking how much the user would like to buy with a JOptionPane
		int result = JOptionPane.showOptionDialog(
						null, 						  			 //no parent component, centers on screen
						spinner, 					  			 //inputs the spinner into the popup
						"How much " + supply.name().toLowerCase()
						+ " would you like to buy?", 		     //title
						JOptionPane.OK_CANCEL_OPTION, 			 //which buttons to display. This popup uses a OK/Cancel option to confirm
						JOptionPane.QUESTION_MESSAGE, 			 //what icon to display. This popup uses the question icon
						null, 						 		     //no custom icon
						null, 						  			 //no custom buttons
						null);		
		
		//update amounts if the OK label is selected
		if (result == JOptionPane.OK_OPTION) {
			double amountBought = (double) (int) spinner.getValue(); //downcast to an double value
			supplies.put(supply, CURRENT_AMOUNT + amountBought);  //adds the current amount with the amount bought and puts it into the map
			//this looong statement subtracts the current amount of cash with the amount paid (supply price * amount bought) and put it into the map
			supplies.put(SupplyType.CASH,  supplies.get(SupplyType.CASH) - (amountBought * supply.getPrice()));
		}
	}
	//example call: buySupply(SupplyType.BACON); 
	
	/**
	 * A method to control the daily deprecation of supplies from travel. Different supplies will deprecate at different amounts. This method will utilize
	 * maps to change the value of each lost supply based on different events, such as weather, etc. 
	 * @param supply The supply to be changed daily. 
	 */
	public void loseSupply(int supply) {
		//create objects of other classes to access public objects
		Perils perils = new Perils(); 
		DailyEvents event = new DailyEvents("Gender", 100); //NOT FINAL, NEEDS MODIFIED
		
		//create a MAP to associate 
		
	}
	
	//helper class to declare and contain all Maps associated with different weather events
	private static class SupplyCalculator {
		//declare each map and associate the enums with an amount lost 
		Map<SupplyType, Double> stormLoss; 
	}

	    public static void main(String[] args) {
	        Invetory i = new Invetory(); 
	       i.buySupply(SupplyType.BACON);
	       System.out.println(""+ i.supplies.get(SupplyType.BACON));
	    }

}

