package perilsalongtheplatte;

import java.util.Random;

public class Perils {
	public int speed;
	public int rations;
	public String weather;
	public String sickness = "";
	Random rng = new Random();
	Boolean isSick;
	
	 
	/**
	 * Checks to see if the pioneer gets sick based off of their health
	 * @param personHealth checks the health of the pioneer
	 * @return true or false based off of if the pioneer gets sick or not
	 */
	public Boolean getsSick( int personHealth ) {
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
	 * @param personHealth checks the health to see if the pioneer is healthy enough to get better
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
	public Integer sicknessPenalty(Boolean gotSick, String weather) {
		// TODO Auto-generated method stub
		
		
		if (gotSick) {
			int chance = rng.nextInt(101);	
			if (chance > 90) { sickness = "fever"; }
			else if (chance > 85) { sickness = "dehydration"; }
			else if (chance > 85 && rations == 1) { sickness = "malnutrition"; }
			else if (chance > 80 && speed == 3) { sickness = "exhaustion"; }
			else if (chance > 75) { sickness = "food poisoning"; }
			else if (chance > 70) { sickness = "flu"; }
			else if (chance > 60 && weather.equals("Sunny")) { sickness = "hyperthermia"; }
			else if (chance > 60 && weather.equals("Snowy")) { sickness = "hypothermia"; }
			else if (chance > 30) { sickness = "cholera"; }
			else if (chance > 25) { sickness = "typhoid fever"; }
			else if (chance > 20) { sickness = "dysentery"; }
			else if (chance > 10) { sickness = "tuberculosis"; }
			
			
		}
		
		switch (sickness) {
		case "fever" : return 1;
		case "typhoid fever" : return 3;
		case "flu": return 2;
		case "dysentery": return 5;
		case "cholera": return 25;
		case "tuberculosis": return 4;
		case "food poisoning" : return 1;
		case "dehydration" : return 5;
		case "malnutrition" : return 5;
		case "hyperthermia" : return 2;
		case "hypothermia" : return 2;
		case "exhaustion" : return 1;
		default: return 0;
		}
	}
	
	/**
	 * Takes the speed and rations to see if the pioneers get injured on the journey
	 * @return an integer that correlates to an injury
	 */
	public Integer injuryPenalty() {															//EDIT
		String injury = "";
		int chance = rng.nextInt(101);
		if (chance < 15) { injury = "fell of wagon"; }
		if (chance < 10) { injury = "broken bone"; }
		if (chance < 7) { injury = "snakebite"; }
		if (chance < 4) { injury = "been trampled"; }
		if (chance < 3) { injury = "drowned"; }
		if (chance < 2) { injury = "firearm misfire"; }
		
		
		switch (injury) {
		case "drowned" : return 100;
		case "been trampled" : return 80;
		case "firearm misfire": return 75;
		case "snakebite": return 30;
		case "broken bone": return 20;
		case "fell of wagon": return 15;
		default: return 0;
		}
	}
	
	/**
	 * Takes the speed and the conditions of the trail and decides 
	 * whether or not the oxen get injured
	 * @return true or false based on if the oxen get injured
	 */
	public Boolean oxInjured() {
		int chanceOfOxInjury = 0;
		String weather1 = "";
		
		switch (weather) {
		case "Snowy": weather1.equals("bad");
		case "Rainy": weather1.equals("ok");
		case "Thunderstorms": weather1.equals("bad");
		case "Sunny": weather1.equals("good");
		case "Windy": weather1.equals("ok");
		default: ;
		}
		
		int chance = rng.nextInt(101);
		if (speed==1 && weather1.equals("good")) {chanceOfOxInjury = 5;}
		if (speed==1 && weather1.equals("ok")) {chanceOfOxInjury = 10;}
		if (speed==1 && weather1.equals("bad")) {chanceOfOxInjury = 15;}
		if (speed==2 && weather1.equals("good")) {chanceOfOxInjury = 15;}
		if (speed==2 && weather1.equals("ok")) {chanceOfOxInjury = 20;}
		if (speed==2 && weather1.equals("bad")) {chanceOfOxInjury = 25;}
		if (speed==3 && weather1.equals("good")) {chanceOfOxInjury = 20;}
		if (speed==3 && weather1.equals("ok")) {chanceOfOxInjury = 25;}
		if (speed==3 && weather1.equals("bad")) {chanceOfOxInjury = 35;}
		
		if (chance < chanceOfOxInjury) {return true;}
		else {return false;}
		
	}
	
	/*
	 * 
	
                   |`\__/ /
                   \  . .(
                    | __T|
                   /   |
      _.---======='    |
     //               {}
    `|      ,   ,     {}
     \      /___;    ,'
      ) ,-;`    `\  //
     | / (        ;||
     ||`\\        |||
     ||  \\       |||
     )\   )\      )||
     `"   `"      `""
	 */
}
