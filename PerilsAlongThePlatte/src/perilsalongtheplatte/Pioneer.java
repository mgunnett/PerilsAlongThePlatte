package perilsalongtheplatte;

/**
 * A class that maintains all status effects of one pioneer, including health pool value, their sickness/injury status, and if they're dead.
 *  This class can be called per pioneer object and used multiple times for each party member. Many of the methods within this class function
 *  as helper methods to do the work for other classes. 
 * @param name The name of the pioneer entered within the text field. 
 */
public class Pioneer {
	//declare variables and objects
	private String name; 
	private int health; 
	private boolean isSick;
	private Sickness sicknessType; 
	private boolean isInjured;
	private Injury injuryType; 
	private boolean isDead;
	private boolean isRecovered; 
	private boolean deathLogged = false;
	private Perils peril = new Perils(); 
	
	//constructor to initialize the values of each value
	public Pioneer(String name) {
		//this.peril = peril;
		this.name = name;
		this.health = 100;   //each pioneer starts with 100 health
		this.isSick = false; //you cannot start sick
		this.isInjured = false; 
		this.isDead = false; //now why would you WANT to start dead... 
		this.sicknessType = Sickness.NONE; //start healthy (yay)
		this.injuryType = Injury.NONE; //you don't start injured
	}
	
	/**
	 * Set the current sickness status of this pioneer utilizing a method within Perils. Updates the value accordingly. 
	 */
	public void setSickness() {
		if (isDead)
			return; //we don't want to update anything if someone is dead...
		isSick = peril.getsSick(health); //update the sickness value if the person gets sick by chance 
	}
	
	/**
	 * Retrieves what sickness value the pioneer is suffering from as an enun and updates the corresponding variable. 
	 * @param isSick Boolean status if the pioneer is sick or not. 
	 */
	public void setSicknessStatus(boolean isSick) {
		Sickness status = peril.determineIfSick(isSick);  
		sicknessType = status; 
	}
	
	/**
	 * Set the current injury status of this pioneer utilizing a method within Perils. Updates the value accordingly.
	 */
	public void setInjury() {
		if (isDead)
			return; // don't update injury if dead
		isInjured = peril.getsInjured(); // update the injury value if the person gets injured by chance
	}

	/**
	 * Retrieves what injury value the pioneer is suffering from as an enum and updates the corresponding variable.
	 * @param isInjured Boolean status if the pioneer is injured or not.
	 */
	public void setInjuryStatus(boolean isInjured) {
		Injury status = peril.determineIfInjured(); 
		injuryType = status;
	}
	
	/**
	 * A setter method to set the recovery status of the pioneer. 
	 * @param acton If true, the recovered status can be manually set. If false, there is an automated check. 
	 */
	public void setRecoveredStatus(boolean action) {
		boolean recovered; 
		if (action) {
			recovered = true;
		}
		recovered = peril.getsBetter(isSick, health);
		isRecovered = recovered; 
		
	}
	
	/**
	 * Functional method to update the pioneer's health based on a change. This method can both add and subtract health based on the supplied value. 
	 * @param change The value to be added/subtracted from the pioneer's health pool
	 */
	public void changeHealth(int change) {
		final int HEALTH_MAX = 100, HEALTH_MIN = 0; 
		//first check if applying the penalty 
		if (health + change <= HEALTH_MIN) { //using addition since adding a negative is still subtraction
			isDead = true; //this pioneer is now dead :-(
			health = HEALTH_MIN; //set health equal to 0. 
			return; //exit the method
		}
		
		//if the pioneer will not die, than update health
		if (health + change >= HEALTH_MAX) { //since 100 is the maximum health pool value, we cannot add over it
			health = HEALTH_MAX; //set equal to maximum value
		}
		
		health += change; 
	} 
	
	/**
	 * Getter to check if the pioneer is dead. 
	 * @return True if the pioneer is dead. 
	 */
	public boolean getDeathStatus() { 
		return isDead; 
	}
	
	/**
	 * Gets the name of the pioneer, originally entered from the GUI. 
	 * @return The name of the pioneer. 
	 */
	public String getName() {
		return name; 
	}
	
	/**
	 * Getter to retrieve the pioneer's health.
	 * @return The health of the pioneer. 
	 */
	public int getHealth() {
		return health; 
	}
	
	/**
	 * Getter to retrieve if the pioneer is sick.
	 * @return True if the pioneer is sick. 
	 */
	public boolean isSick() {
		return isSick; 
	}
	
	/**
	 * Getter to retrieve what sickness the pioneer suffers from.
	 * @return The sickness status of the pioneer. 
	 */
	public Sickness getSicknessType() {
		return sicknessType; 
	}
	
	/**
	 * Getter to retrieve if the pioneer is injured. 
	 * @return The injury status of the pioneer. 
	 */
	public boolean getInjuredStatus() {
	    return isInjured;
	}

	/**
	 * Getter to retrieve the type of injury the pioneer is suffering from.
	 * @return The injury status of the pioneer. 
	 */
	public Injury getInjuryType() {
	    return injuryType;
	}

	
	/**
	 * Setter method to manually set if the pioneer is sick or not. 
	 * @param isSick The boolean status if the pioneer is sick. 
	 */
	public void setSicknessBoolean(boolean status) {
		isSick = status; 
	}
	
	/**
	 * Getter to see if the pioneer has recovered from a sickness. 
	 * @return True if the pioneer is recovered, false if otherwise. 
	 */
	public boolean getRecoveredStatus() {
		return isRecovered; 
	}
	
	/**
	 * Getter to check if the death of this pioneer has already been logged. 
	 * @return True if their death is logged. 
	 */
	public boolean hasDeathBeenLogged() {
	    return deathLogged;
	}
	
	/**
	 * Setter to set when the death of this pioneer is logged.
	 * @param logged True if you want to log the death of a pioneer. 
	 */
	public void setDeathLogged(boolean logged) {
	    deathLogged = logged;
	}
	
}
