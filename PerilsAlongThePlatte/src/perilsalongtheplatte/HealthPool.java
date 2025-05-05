package perilsalongtheplatte;

import java.util.ArrayList;

public class HealthPool {
	
	public Perils peril;
	private TravelDistance travDist = new TravelDistance(null);
	private int NUM_PEOPLE = 5; //max number of party members
	String weather = "";
    ArrayList<Integer> personHealth = new ArrayList<Integer>();
    ArrayList<String> personName = new ArrayList<String>();
    ArrayList<String> whoIsDead = new ArrayList<String>();
    ArrayList<Boolean> isSick = new ArrayList<Boolean>(); 
    boolean dayOfRest = false;
    String person1, person2, person3, person4, person5;
    
    
//    //Create a constructor to update the health pool based off the the timer from TravelDistance
//    HealthPool (Timer dayTimer, boolean isStopped)    {
//		Timer Task:
//    	healthIncrease(dayOfRest);
//		healthDecrease(dayOfRest);
//		healthPoolCondition(); [ FOR GUI ]
//		checkIfSick();
//		checkIfDead();
//		
//		Outside of timer task: initializeHealth(5);
//    }
    
	public HealthPool() {
		initializeHealth();
	}
	/**
	 * Initializes the health for any and all pioneers
	 * @param NUM_PEOPLE is used to see how many health integers are needed
	 */
	public void initializeHealth() {
    	for (int i = 0; i < NUM_PEOPLE; i++) {
	    	personHealth.add(100);
	    	isSick.add(false);
    	}
	}
	
	/**
	 * Checks to see the condition of the overall health of the group
	 */
	public String healthPoolCondition() {
		int healthPool = 0;
		for (int i = 0; i < NUM_PEOPLE; i++) {
			healthPool = healthPool + personHealth.get(i);
		}
		double avgHealth = healthPool / personHealth.size();
		if (avgHealth > 75) {return "good";}
		else if (avgHealth > 50) {return "fair";}
		else if (avgHealth > 25) {return "poor";}
		else if (avgHealth > 0) {return "terrible";}
		return "";
	}
	
	/**
	 * Checks to see if the pioneer is traveling and loses health
	 */
	public void healthDecrease(boolean dayOfRest, String weather) {
		//exit the method now if the player is resting, we don't want to lose health
		if (dayOfRest)
			return; 
		//health loss will depend on the different weather conditions, and lose more for more harsh conditions
		int healthLoss; 
		switch (weather) {
        case "Sunny":
        	healthLoss = 3;
            break;
        case "Rainy":
        	healthLoss = 4;
            break;
        case "Windy":
        	healthLoss = 3;
            break;
        case "Thunderstorms":
        	healthLoss = 5;
            break;
        case "Snowy":
        	healthLoss = 6; //the most sever weather
            break;
        default:
        	healthLoss = 3;
            break;
		}
		//now subtract the current health with that of the health loss
			for (int i = 0; i < NUM_PEOPLE; i++) 
				personHealth.set(i, personHealth.get(i) - healthLoss);
			
	}
	
	/**
	 * Checks to see if the pioneer is resting and gains health
	 */
	public void healthIncrease(boolean dayOfRest) { 
		if(dayOfRest) {
			for (int i = 0; i < NUM_PEOPLE; i++) {
				personHealth.set(i, personHealth.get(i) + 5);
			}
		}
	}
	
	/**
	 * Checks if the pioneer is sick and needs to lose additional health
	 * @return true or false depending on if the pioneer is sick
	 */
	public void checkIfSick() {
		for(int i = 0; i < NUM_PEOPLE; i++) { 
			boolean sickStatus = isSick.get(i);
			if (peril.getsSick(personHealth.get(i), sickStatus) == true) {			//SOMETHING
				isSick.set(i, true);
			personHealth.set(i, personHealth.get(i) - peril.sicknessPenalty(isSick.get(i), weather) - peril.injuryPenalty());
			}
		}
	}
	
	/**
	 * Checks if the pioneer is dead
	 * @param health is checked to see if it is equal to or below 0
	 */
	public void checkIfDead() {
		for(int i = 0; i < NUM_PEOPLE; i++) {
			int health = personHealth.get(i);
			if( health <= 0) {
				System.out.println(personName.get(i) + " has died.");
				whoIsDead.add(personName.get(i));
				personHealth.remove(i);
				personName.remove(i);
				isSick.remove(i);
				NUM_PEOPLE--;
			}
		}
	}
	
	public Boolean isAnyoneSick() {
		for (int i = 0; i < isSick.size(); i++) {
			if (isSick.get(i) == true) {return true;}
		}
		return false;
	}

	/**
	 * A setter that will update the value of dayOfRest to true when the player decides to rest. 
	 * @param isResting a boolean stating whether the player is resting or is ready to resume travel. 
	 */
	public void setRestStatus(boolean isResting) {
		dayOfRest = isResting; 
	}
	
}
