package perilsalongtheplatte;

import java.util.ArrayList;

public class HealthPool {
	
	public Perils peril;
	public TravelDistance travDist = new TravelDistance(null);
	public int numOfPeople = 5; 
	String weather = "";
    ArrayList<Integer> personHealth = new ArrayList<Integer>();
    ArrayList<String> personName = new ArrayList<String>();
    ArrayList<String> whoIsDead = new ArrayList<String>();
    ArrayList<Boolean> isSick = new ArrayList<Boolean>(); 
    Boolean dayOfRest;
	
    //create a constructor to initialize the inputed names from GAME into this class
    HealthPool(String person1, String person2, String person3, String person4, String person5) {
    	personName.add(person1); 
    	personName.add(person2); 
    	personName.add(person3); 
    	personName.add(person4);
    	personName.add(person5); 

    }
    
    
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
    
	/**
	 * Initializes the health for any and all pioneers
	 * @param numOfPeople is used to see how many health integers are needed
	 */
	public void initializeHealth(int numOfPeople) {
		peril = new Perils();
		personHealth = new ArrayList<>();
    	for (int i = 0; i < numOfPeople; i++) {
	    	personHealth.add(100);
	    	isSick.add(false);
    	}
	}
	/**
	 * Checks to see the condition of the overall health of the group
	 */
	public String healthPoolCondition() {
		int healthPool = 0;
		for (int i = 0; i < numOfPeople; i++) {
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
	public void healthDecrease(Boolean dayOfRest) {
		int conditionOfTrail = 1;
		if(!dayOfRest) { 
			for (int i = 0; i < numOfPeople; i++) {
				
				personHealth.set(i, personHealth.get(i) - conditionOfTrail);
			}
		}
	}
	
	/**
	 * Checks to see if the pioneer is resting and gains health
	 */
	public void healthIncrease(Boolean dayOfRest) { 
		if(dayOfRest) {
			for (int i = 0; i < numOfPeople; i++) {
				personHealth.set(i, personHealth.get(i) + 5);
			}
		}
	}
	
	/**
	 * Checks if the pioneer is sick and needs to lose additional health
	 * @return true or false depending on if the pioneer is sick
	 */
	public void checkIfSick() {
		for(int i = 0; i < numOfPeople; i++) { 
			if (peril.getsSick(personHealth.get(i), dayOfRest) == true) {			//SOMETHING
				isSick.set(i, true);
			//	personHealth.set(i, personHealth.get(i) - peril.sicknessPenalty(isSick.get(i), weather) - peril.injuryPenalty());
			}
		}
	}
	
	/**
	 * Checks if the pioneer is dead
	 * @param health is checked to see if it is equal to or below 0
	 */
	public void checkIfDead() {
		for(int i = 0; i < numOfPeople; i++) {
			int health = personHealth.get(i);
			if( health <= 0) {
				System.out.println(personName.get(i) + " has died.");
				whoIsDead.add(personName.get(i));
				personHealth.remove(i);
				personName.remove(i);
				isSick.remove(i);
				numOfPeople =- 1;
			}
		}
	}
	


}
