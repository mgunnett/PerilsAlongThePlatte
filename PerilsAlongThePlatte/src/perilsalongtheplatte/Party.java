package perilsalongtheplatte;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents the group of pioneer, or the party, and those who are in it. This class calls the updating of each pioneer's health
 * based on various status, and it can retrieve the overall group health status. 
 */
public class Party {
	//create a list object to store all of our 5 pioneers
	List<Pioneer> party = new ArrayList<>();
	private final int MAX_SIZE = 5; 
	private boolean isAnyoneSick = false; 
	private Inventory inventory; 
	
	public Party(Inventory i) {
		this.inventory = i; 
	}
	
	/**
	 * A method that adds a pioneer object to the party list. This method needs to be called when each name is inputed into the GUI.
	 * @param p The pioneer object to be added into the list.  
	 */
	public void addPioneer(Pioneer p) {
		party.add(p); //adds the pioneer to the list
	}
	
	public void changeHealth(boolean isResting) {
		int counter = 0; //to help determine when no one is sick
		
		//create an iterator object
		Iterator<Pioneer> iterator = party.iterator(); 
		
		while (iterator.hasNext()) { //iterates through the list
			Pioneer p = iterator.next(); //creating pioneer object
			
			if (!p.isSick()) { //update our counter
			    counter++;
			}
			
			//if the pioneer is now dead, then skip this code and remove
			if (p.getDeathStatus()) 
				continue; //skips over below code
	
			
			//set the sickness status of each pioneer utilizing the Pioneer class
			p.setSickness(); 
			p.setSicknessStatus(p.isSick());
			
			//now perform checks to see what penalty status needs to be added/subtracted
			int change = 0; //a cumulative value of each health penalty the pioneer suffers from
			//start with checking if the pioneer is sick or recovered
			if (p.isSick()) {
//				check if the pioneer recovered from their sickness 
				boolean recovered = p.getRecoveredStatus(); 
				if (recovered) {
					p.setSicknessBoolean(false); //set both values of isSick and theSickness
					p.setSicknessStatus(false); //will clear the sickness enum
				} 
				else {
					isAnyoneSick = true; 
					Sickness type = p.getSicknessType(); //store the enum of the sickness type
					change += type.getPenalty(); //change is now equal to the penalty associated with that enum
					//for example, if the pioneer has a fever, they lose 1 hp 
				}
			}
			
			if (p.getInjuredStatus()) { //get injury penalty, similar to above
				Injury type = p.getInjuryType(); 
				change += type.getPenalty(); 
			}
			
			if (isResting) { //if resting, add hp
				change += 5; //adds 5 hp
			}
			
			if (inventory.outOfFood())
				change -= 10; //lose 10 hp if out of food
			
			if (inventory.getSupply(SupplyType.SOAP) > 0.0) { //if you have soap, gain some hp per day
				change += 1; 
			}
			
			
			//if none of the above are true, then the pioneer gains 0 hp
			//now we are good to add the penalties to the health pool of the pioneer
			p.changeHealth(change);
			
			
			isAnyoneSick = (counter != party.size()); //update the isAnyoneSick value
		}
	}
	
	/**
	 * Method to check and report the overall group health of the party as an integer. This is useful for either testing purposes or printing 
	 * it to the GUI as a label. 
	 * @return Representation of a number relating to the overall party health. 
	 */
	public int getOverallHealth() {
		int cumHealth = 0; //stores the cumulative health of each pioneer 
		for (Pioneer p : party) {
			cumHealth += p.getHealth();  
		}
		int avgHealth = cumHealth / party.size(); //average is equal to total health divided by number of members
		return avgHealth; 
	}
	
	/**
	 * Checks if anyone in the party is dead. 
	 * @return True if a pioneer is dead. 
	 */
	public boolean isAnyoneDead() {
		if (party.size() < MAX_SIZE) //checks if the size is less than maximum number of members, 5
			return true;
		return false; //if size == MAX, then return false
		
	}
	
	/**
	 * Getter method to check if anyone is sick within the party
	 * @return True if someone is sick. 
	 */
	public boolean isAnyoneSick() {
		return isAnyoneSick; 
	}
	
	/**
	 * Getter method to get the party size
	 * @return The current party size
	 */
	public int getPartySize() {
		return party.size(); 
	}
	
	/**
     * Getter method to retrieve the list of pioneers (party members).
     * @return List of Pioneer objects representing the party members.
     */
    public List<Pioneer> getParty() {
        return party;
    }
    
    /**
     * Getter method to check if everyone in the party is dead.
     * @return True if everyone is dead, false if otherwise. 
     */
    public boolean isPartyDead() { 
    	for (Pioneer p : party) {
    		if (!p.getDeathStatus())
    			return false; //someone is still alive, the game is not over yet!
    	}
    	return true; //then everyone is dead 
    }
}
