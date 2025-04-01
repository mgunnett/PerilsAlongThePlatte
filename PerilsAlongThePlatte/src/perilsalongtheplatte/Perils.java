package perilsalongtheplatte;

import java.util.Random;

public class Perils {
	public int speed;
	public String weather;
	Random rng = new Random();
	Boolean isSick;
	
	/**
	 * Checks to see if the pioneer gets sick based off of their health
	 * @param personHealth checks the health of the pioneer
	 * @return true or false based off of if the pioneer gets sick or not
	 */
	public Boolean getsSick( int personHealth ) {											//EDIT
		int chance = rng.nextInt(101);
		int sickChance = 0;
		if (personHealth > 90) { sickChance = 5; }
		else if (personHealth > 70) { sickChance = 10; }
		else if (personHealth > 50) { sickChance = 20; }
		else if (personHealth > 30) { sickChance = 30; }
		else if (personHealth <= 30) { sickChance = 40;}
		
		if (chance < sickChance) {return true;}
		return false;
	}
	
	/**
	 * If a pioneer is already sick, checks to see if the pioneer gets better
	 * @param isSick checks to see if a pioneer is sick
	 * @param personHealth checks the health to see if the pioneer is healthy enought to get better
	 * @return
	 */
	public Boolean getsBetter ( boolean isSick, int personHealth ) {
		int chance = rng.nextInt(101);
		int betterChance = 0;
		if (isSick) {
			if (personHealth > 90) { betterChance = 80; }
			else if (personHealth > 70) { betterChance = 75; }
			else if (personHealth > 50) { betterChance = 70; }
			else if (personHealth > 30) { betterChance = 65; }
			else if (personHealth <= 30) { betterChance = 60;}
			
			if (chance < betterChance) {return true;}
			else { return false;}
		}
		else{ return false; }
	}

	/**
	 * @param gotSick is used to see if the pioneer gets a random sickness
	 * @return an integer that correlates to a sickness
	 */
	public Integer sicknessObtained(Boolean gotSick) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Takes the speed and rations to see if the pioneers get injured on the journey
	 * @return an integer that correlates to an injury
	 */
	public Integer injuryObtained() {															//EDIT
		return 0; // no injury obtained
	}
	
	/**
	 * Takes the speed and the conditions of the trail and decides 
	 * whether or not the oxen get injured
	 * @return true or false based on if the oxen get injured
	 */
	public Boolean oxInjured() {
		int chanceOfOxInjury = 0;
		int chance = rng.nextInt(101);
		if (speed==1 && weather.equals("good")) {chanceOfOxInjury = 10;}
		if (speed==1 && weather.equals("ok")) {chanceOfOxInjury = 15;}
		if (speed==1 && weather.equals("bad")) {chanceOfOxInjury = 20;}
		if (speed==2 && weather.equals("good")) {chanceOfOxInjury = 15;}
		if (speed==2 && weather.equals("ok")) {chanceOfOxInjury = 20;}
		if (speed==2 && weather.equals("bad")) {chanceOfOxInjury = 25;}
		if (speed==3 && weather.equals("good")) {chanceOfOxInjury = 20;}
		if (speed==3 && weather.equals("ok")) {chanceOfOxInjury = 25;}
		if (speed==3 && weather.equals("bad")) {chanceOfOxInjury = 35;}
		
		if (chance < chanceOfOxInjury) {return true;}
		else {return false;}
	}

}
