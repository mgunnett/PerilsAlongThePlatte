package perilsalongtheplatte;
import java.util.Map;
import java.util.Map.Entry;
import java.util.EnumMap; 
import java.util.Random;
import javax.swing.*;

/**
 * This class handles all methods related to the management of the inventory, including supplies lost from daily travel, the shop, etc. This class
 * relies on the enum SupplyType to store the construct for each supply type and its corresponding price. This class needs help from 
 * an additional one to handle the graphics associated with the shop; these methods can only generate JOptionPanes. 
 * @author Parker R. West
 * @version 2.0
 */
public class Inventory {
	//in order to associate a value with the corresponding enum, a enumMap will be used to associate a value (the amount)
	//with its corresponding enum
	public static Map<SupplyType, Double> supplies = new EnumMap<>(SupplyType.class); 
	//creating several enum maps for different perils
	public Map<SupplyType, Double> defaultUsage = new EnumMap<>(SupplyType.class); 
	public Map<SupplyType, Double> sicknessInjury = new EnumMap<>(SupplyType.class);
	public Map<SupplyType, Double> death = new EnumMap<>(SupplyType.class);
	
	//other class objects
	GAME game = new GAME(); //access to key variables...
	HealthPool health = new HealthPool();
	//now with each value created within a map, a constructor is needed to initialize the map
	public Inventory() {
		//utilizing a for each loop...
		for (SupplyType supply : SupplyType.values()) { 
			supplies.put(supply, 0.0); //initialize map with 0.00 as the current amount of item
			//special case for the player's cash
			if (supply.equals(SupplyType.CASH))
				supplies.put(supply, 500.00); //amount of starting cash
		}
	}
	/**
	 * Getter method to retrieve the current amount of supplies corresponding to an enum. 
	 * @param supply The The map key to check; type of supply to get amount of. 
	 * @return The amount of the specified supply type. 
	 */
	public double getSupply(SupplyType supply) {
		return supplies.get(supply);
	}
	
	/**
	 * A polymorphic method to buy a type of supply from the store. This method needs the kind of supply to buy and the unit price. 
	 * Once bought, the amount bought is added to the user's inventory and cash is subtracted form the inventory. If the player is male, then they can access
	 * the shop. If female, the "husband" will buy the supplies for the player, or a randomly generated value of supplies. 
	 * @param supply Integer representation of the supply to purchase. Reference the public integers to use as parameters. 
	 * @param isMale The current gender of the player. True if male is selected. 
	 */
	public void buySupply(SupplyType supply, boolean isMale) {
		if (isMale) {
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
		else { //if the player gender is female, then tell them they cannot buy supplies
			JOptionPane.showMessageDialog(null, "Sorry, since you're female, you cannot buy supplies. Your husband handles this part. ");	
		}

	}
	//example call: buySupply(SupplyType.BACON); 
	
	/**
	 * If the player gender is female, then the husband will buy supplies from her. The husband will try to buy some of each supply until the cash is depleted. 
	 * He will try to buy less of larger items (such as oxen). 
	 * @param isMale
	 */
	public void husbandBuySupplies(boolean isMale) {
		if (!isMale) { //if the player is female, then husband buys supplies
			//create new map to check if a supply is low
		    Map<SupplyType, Boolean> isSupplyLow = new EnumMap<>(SupplyType.class);

		    //define the default thresholds to check for
		    double LOW_THRESHOLD = 5.0; 
		    double MAX_AMOUNT = 20.0; //max amount husband wants to buy

		    //begin by finding which supplies are low 
		    for (SupplyType type : SupplyType.values()) {
		        if (type != SupplyType.CASH || type != SupplyType.BUFFALOCHIPS || type != SupplyType.WATER) { //skip over cash, buffalochips, and water, unbuyable items
		            double currentAmount = supplies.get(type); 
		            isSupplyLow.put(type, currentAmount < LOW_THRESHOLD); //check if the current amount is lower than the threshold. If so, put true. 
		        }
		    }

		    //now since we've determined which supplies are low, determine which ones need to be bought
		    for (Map.Entry<SupplyType, Boolean> entry : isSupplyLow.entrySet()) { //iterate through the isSupplyLow map 
		    	//grab the keys of each element and the boolean associated with it
		        SupplyType type = entry.getKey();
		        boolean low = entry.getValue();

		        if (low) {
		        	 //switch on the key type to check which values have different max ranges
		            switch (type) {
		            case WHEELS: 
		            	MAX_AMOUNT = 2.0; //max of 3 wheels can be bought 
		            	LOW_THRESHOLD = 0.0; //0 is low
		            case AXELS: 
		            	MAX_AMOUNT = 2.0;  
		            	LOW_THRESHOLD = 0.0; 
		            case TONGUES: 
		            	MAX_AMOUNT = 2.0; 
		            	LOW_THRESHOLD = 0.0; 
		            case OXEN: 
		            	MAX_AMOUNT = 2.0; 
		            	LOW_THRESHOLD = 0.0; 
		            case CLOTHES: 
		            	MAX_AMOUNT = 3.0; 
		            	LOW_THRESHOLD = 0.0; 
		            }
		           
		            double currentAmount = supplies.get(type); 
		            double amountToBuy = MAX_AMOUNT - currentAmount; //top the supplies up to the maximum threshold
		            
		            //variables to use in calculations
		            double pricePerUnit = type.getPrice();
		            double totalCost = amountToBuy * pricePerUnit;
		            double currentCash = supplies.get(SupplyType.CASH);

		            if (currentCash >= totalCost) { //check if the current cash is enough to buy the selected amount
		            	//if so, update values
		                supplies.put(type, currentAmount + amountToBuy);
		                supplies.put(SupplyType.CASH, currentCash - totalCost);
		            } 
		            else { //then the husband cannot buy the maximum amount 
		                ///not enough cash, buy as much as possible
		                double maxAmount = currentCash / pricePerUnit; //max amount able to be bought
		                supplies.put(type, currentAmount + maxAmount);
		                supplies.put(SupplyType.CASH, 0.0); //if this point is reached, the husband spent all the cash (he is stupid)!
		                break; //exit for loop
		            }
		        }
		    }
		}
	}
	
	/**
	 * A method that allows the player to trade for different supplies along their journey. They will receive a random supply offer in exchange for a random supply. 
	 * The amount to trade for will also be random.  
	 */
	public static void tradeSupply() {
		//assign a value to each enum with a Map construct and an counter variable
		//the counter iterates through values 0-18 and assigns it to an enum
		Map<SupplyType, Integer> supplyRandomValue = new EnumMap<>(SupplyType.class); 
		int counter = 0; //counter to be iterated
		for (SupplyType supply : SupplyType.values()) //for each loop checking through each enum
			supplyRandomValue.put(supply, counter++);
		
		//get a value to check which supply is TO BE TRADDED 
		Random rng = new Random(); 
		final int MAP_MAX = 18 + 1; //maximum range of the map [0-18]
		int offer = rng.nextInt(MAP_MAX);
		
		//grab the key associated to the value to be traded
		//to do this, we need to manually search through the map, as Java doesn't like vale -> key relationships :-(
		SupplyType offeredSupply = null; //our key, which will be traded
		for (Entry<SupplyType, Integer> entry : supplyRandomValue.entrySet()) {
			if (entry.getValue().equals(offer))
				offeredSupply = entry.getKey(); //stores the key associated to the random value
		}
		
		//repeat the above process for the supply the player is trading for
		int toBeTraded = (int)(Math.random() * MAP_MAX); 
		SupplyType tradedSupply = null; //our key, which will be traded by the player
		for (Entry<SupplyType, Integer> entry : supplyRandomValue.entrySet()) {
			if (entry.getValue().equals(toBeTraded))
				tradedSupply = entry.getKey(); //stores the key associated to the random value
		}
		
		//create random number of items to be traded
		int playerAmount = rngHelper(tradedSupply); //helper method, see below
		int traderAmount = rngHelper(offeredSupply);; 
		
		
		//create a JOptionPane popup to ask player to trade
		int result = JOptionPane.showConfirmDialog(
				null, 																				  //no parent component
				"Would you like to trade " + playerAmount + " " + tradedSupply.toString() + " for " + //message containing amount of each supply
				traderAmount + " " + offeredSupply.toString() + "?", 
				"Trade Offer!", 																	  //title of the popup
				JOptionPane.OK_CANCEL_OPTION, 			 											  //which buttons to display. This popup uses a OK/Cancel option to confirm
				JOptionPane.WARNING_MESSAGE, 			 											  //what icon to display. This popup uses the question icon
				null);																				  //no custom icon
		//update amounts if the OK label is selected
		if (result == JOptionPane.OK_OPTION) {
			if (supplies.get(offeredSupply) >= playerAmount) { //check to make sure player doesn't trade into a negative amount
			supplies.put(offeredSupply, supplies.get(offeredSupply) - (double) playerAmount); //update the player's inventory with the amount that they lost
			supplies.put(tradedSupply, supplies.get(tradedSupply) + (double) traderAmount);   //add amount added
			}
			else {
				JOptionPane.showMessageDialog(null, "You don't have enough to trade for that :-("); //sad dialog message
			}
		}		
	}	
	/**
	 * A method to control the daily deprecation of supplies from travel. Different supplies will deprecate at different amounts. This method will utilize
	 * maps to change the value of each lost supply based on different events, such as weather, etc. 
	 * @param supply The supply to be changed daily. 
	 */
	public void loseSupply() {
		//initialize each usage amount
		supplyCalculator(); 
	
		//create variables to help with supply calculations
		double multiplier; //a multiplier to multiply the daily supply usage with; based on rations construct
		int rationsSwitch = game.rations; //helper integer for the switch statement
		switch (rationsSwitch) {
		case 2:  multiplier = 1.5; break; //if 2, use 1.5x more
		case 3:  multiplier = 2.0; break; //if 3, use 2.0x more
		default: multiplier = 1.0; break; //(assuming default of 1) use 1.0x supplies
		}
		boolean isSomeoneDead = !health.whoIsDead.isEmpty();
		boolean isSomeoneSick = health.isAnyoneSick();
		if (isSomeoneDead && isSomeoneSick) { //check if the dead person list has an entry and if someone is sick
			for (SupplyType supply : SupplyType.values()) 
				supplies.put(supply, supplies.get(supply) - ((death.get(supply) + sicknessInjury.get(supply) * multiplier)));
		}
		else if (isSomeoneDead && !isSomeoneSick) { //check if someone is dead and if someone is NOT sick
			for (SupplyType supply : SupplyType.values()) 
				supplies.put(supply, supplies.get(supply) - (death.get(supply) *  multiplier));
		}
		else if (!isSomeoneDead && isSomeoneSick) { //check if someone is NOT dead and if someone is sick
			for (SupplyType supply : SupplyType.values()) 
				supplies.put(supply, supplies.get(supply) - (sicknessInjury.get(supply) * multiplier));
		}
		else { //default usage
			for (SupplyType supply : SupplyType.values()) 
				supplies.put(supply, supplies.get(supply) - (supply.getUsageAmount() * multiplier));
		}
	}
	
	//helper method to declare and contain all Maps associated with different weather events
	private void supplyCalculator() {
		//initializes the default usage map
		defaultUsage.clear();
		death.clear();
		sicknessInjury.clear();
		for (SupplyType supply : SupplyType.values()) 
			defaultUsage.put(supply, supply.getUsageAmount());
		
		//initialization of the sicknessInjury map
		for (SupplyType supply : SupplyType.values()) {
			//switch statement on supply to change certain values from the defaults
			switch (supply) {
			case MEDICINE:
				sicknessInjury.put(supply, 0.5); break; //uses 0.5 per day, compared to the default of 0
			case  SOAP:
				sicknessInjury.put(supply, 0.5); break;//sick people want to clean bodily fluids/injuries
			case WATER:
				sicknessInjury.put(supply, 15.0); break;
			default: 
				sicknessInjury.put(supply, supply.getUsageAmount());
			}
		}
			
		//death map initialization
		double multiplier = 1.0;
		int alive = health.personName.size(); //uses an array to check how many are alive
		if (alive == 4) 
			multiplier = 0.8; //use 20% less supplies
		else if (alive == 3) 
			multiplier = 0.6;
		else if (alive == 2)
			multiplier = 0.4;
		else if (alive == 1) 
			multiplier = 0.2;
		
		//initialize map
		for (SupplyType supply : SupplyType.values()) 
			death.put(supply, supply.getUsageAmount() * multiplier);
	//special cases for different perils	
	}
	
	//helper method to calculate the number of each supply to be traded by either party in the tradeSupply method. 
	private static int rngHelper(SupplyType supply) {
		Random rng = new Random(); //create a random number generator
		int amount = 0; 
		switch (supply) {
		case FLOUR: amount =  10 + rng.nextInt(11); break;      //can trade between 10-20lbs of flour
		case BACON: amount =  10 + rng.nextInt(11); break;     //10-20lbs
		case FRUIT: amount =  5 + rng.nextInt(6); break;	   //5-10lbs
		case VEGGIES: amount = 5 + rng.nextInt(6); break;	   //5-10lbs
		case MEAT: amount = 25 + rng.nextInt(26); break;       //25-50lbs
		case COFFEE: amount =  5 + rng.nextInt(6); break;	   //5-10lbs
		case TEA: amount = 5 + rng.nextInt(6); break;	       //5-10lbs
		case LARD: amount =  10 + rng.nextInt(11); break;      //10-20lbs
		case WATER:  amount =  10 + rng.nextInt(11); break;    //10-20gal
		case WHEELS:  amount =  1 + rng.nextInt(2); break;     //1-2 wheels
		case AXELS:  amount =  1 + rng.nextInt(2); break;      //1-2 axels
		case TONGUES:  amount =  1 + rng.nextInt(2); break;  //1-2 tongues 
		case AMMO:  amount =  25 + rng.nextInt(51); break;     //50-100 bullets
		case OXEN:  amount =  1 + rng.nextInt(2); break;       //1-2 oxen
		case MEDICINE: amount = 5 + rng.nextInt(6); break;     //5-10lbs
		case CLOTHES: amount = 2 + rng.nextInt(3); break;	   //2-4 sets of clothing
		case SOAP: amount = 5 + rng.nextInt(6); break;	 	   //5-10lbs
		case BUFFALOCHIPS: amount = 5 + rng.nextInt(6); break; //5-10lbs
		case CASH: amount = 25 + rng.nextInt(51); break;	   //25-75 dollars
		}
		return amount; 
	}
}

