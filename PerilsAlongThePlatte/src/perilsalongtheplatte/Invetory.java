package perilsalongtheplatte;
import javax.swing.*;
/**
 * This class handles all methods related to the management of the inventory, including supplies lost from daily travel, the shop, etc. The variables will be stored 
 * here and reported back to GAME for usage. This class needs help from an additional one to handle the graphics associated with the shop; these methods can only generate 
 * JOptionPanes. 
 * @author Parker R. West
 * @version 1.0
 */

public class Invetory {
	//declare public variables to be used in other classes, all initially set to 0
	public int flour, bacon, fruit, veggies, meat, coffee, lard, water,
			   wheels, axels, tongues, ammo, oxen, medicine, clothes, soap, buffaloChips;
	public double cash; 
	
	//price of each item, source: https://historicoregoncity.org/2019/04/03/outfitting-for-the-journey/
	//prices are in $/lb
	public final double flourPrice = 0.02, baconPrice = 0.05, fruitPrice = 0.06, veggiePrice = 0.06, meatPrice = 0.10, 
					     coffeePrice = 0.10, lardPrice = 0.05, /*skipping water as there is no price...*/ 
					     wheelPrice =  15.0, axelPrice = 15.0, tonguePrice = 15.0, ammoPrice = 2.75, oxenPrice = 35.0, 
					     medicinePrice = 36.0, clothesPrice = 8.00, soapPrice = 0.15; //buffaloChips also cannot be bought
	/**
	 * A polymorphic method to buy a type of supply from the store. This method needs the kind of supply to buy and the unit price. 
	 * Once bought, the amount bought is added to the user's inventory and cash is subtracted form the inventory. 
	 * @param supply Integer representation of the supply to purchase. Reference the public integers to use as parameters. 
	 * @param price The unit price ($/lb) of the supply to buy. Again, reference public doubles in this class. 
	 */
	public void buySupply(int supply, double price) {
		//create a spinner object
		final int MAX = (int) (cash / price); //max amount player can purchase. Determined by the cash divided by price
		final int MIN = 0; //useful if the user wants to exit without doing anything
		SpinnerNumberModel model = new SpinnerNumberModel //creates the parameters of the spinner
		(0, MIN, MAX, 1); //sets the bound/increments of the spinner. 
	    JSpinner spinner = new JSpinner(model); //creates the actual spinner object
		//start with asking how much the user would like to buy with a JOptionPane
		int amountBought = JOptionPane.showOptionDialog(
						null, 						  			 //no parent component, centers on screen
						spinner, 					  			 //inputs the spinner into the popup
						"How much would you like to buy?", //title
						JOptionPane.OK_CANCEL_OPTION, 			 //which buttons to display. This popup uses a OK/Cancel option to confirm
						JOptionPane.QUESTION_MESSAGE, 			 //what icon to display. This popup uses the question icon
						null, 						 		     //no custom icon
						null, 						  			 //no custom buttons
						null);		
		//update amounts if the OK label is selected
				if (amountBought == JOptionPane.OK_OPTION) {
					amountBought = (int) spinner.getValue(); //downcast to an integer value
					supply += amountBought; 
					cash -= (price * amountBought);    
				}
	}
	//example call: buySupply(Invetory.soap, Invetory.soapPrice); 
	
		
	public void loseSupply(int supply, )
}

