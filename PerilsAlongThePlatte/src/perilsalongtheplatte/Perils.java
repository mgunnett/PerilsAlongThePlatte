package perilsalongtheplatte;
import java.util.Random;

/**
 * This class stores all logic relating to checks to see if a person is sick using a random number generator. This class helps others, mainly
 * HealthPool, to subtract a particular penalty number if a peril condition is met. 
 */
public class Perils {
	//create the random number generator
    private final Random rng = new Random();
    
    /****************
     *SICKNESS LOGIC*
     ****************/

    /**
     * A method that checks if a person gets sick, provided their current health value. 
     * @param health The current health value of that person. 
     * @return true if that person gets sick, false if otherwise. 
     */
    public boolean getsSick(int health) {
    	//perform a switch on the person's health to see if they get sick
    	//lower health values result in a greater chance of getting sick
        int sickChance; 
        switch (health) {
            case 90:
            	sickChance = 5;
            case 70:
            	sickChance = 10;
            case 50:
            	sickChance = 20;
            case 30:
            	sickChance = 30;
            default:
            	sickChance = 40;
        }
        
        //now check if the randomly generated integer is less than the chance
        boolean isSick = rng.nextInt(100) < sickChance;
        //if so, that person is now sick
        return isSick; 
    }
    
    /**
     * A method that checks if a person recovers from their sickness/injury, given their health and sickness status. 
     * @param isSick Sickness status of the person. 
     * @param health Health pool value of the person. 
     * @return True if the person recovers from their injury/sickness
     */
    public boolean getsBetter(boolean isSick, int health) {
    	//if the person is not sick, then there is nothing to get better from!!
        if (!isSick) 
        	return false;
        int chance = rng.nextInt(100); //set up our randomly generated chance value
        //use a switch to set our recovery chance based on health value
        //higher health values mean a greater chance of recovering
        int recoverChance;
        switch (health) {
            case 90:
            	recoverChance = 80;
            	break;
            case 70:
            	recoverChance = 75;
            	break;
            case 50: 
            	recoverChance = 70;
            	break;
            case 30:
            	recoverChance = 65;
            	break;
            default:
            	recoverChance = 60;
            	break;
        }
        
        boolean recovers = chance < recoverChance; //decides if the player recovers based on chance
        return recovers; 
    }

    /**
     * A method to check what sickness a person is suffering from based on a random chance. Running this method will return the corresponding 
     * enum that the person is suffering from. This returned enum can then be used in the HealthPool to determine how much health to subtract. 
     * @param weather Enum representation of the weather. 
     * @param rations The current rations value from the GUI interface. 
     * @param speed The current speed value from the GUI interface. 
     * @param isSick Boolean status of a person's sickness status. 
     * @return The enum a person suffers from based on chance. 
     */
    public Sickness determineIfSick(Weather weather, int rations, int speed, boolean isSick) {
    	if (isSick) { //only determine what sickness a person suffers from if they are sick
	        int roll = rng.nextInt(100); //generate the random number
	        if (roll >= 90) 
	        	return Sickness.FEVER; 					 //10% chance of a fever
	        if (roll >= 85) 
	        	return Sickness.DEHYDRATION; 			 //5% chance 
	        if (roll >= 80 && rations < 2) 
	        	return Sickness.MALNUTRITION; 			 //5% chance 
	        if (roll >= 75 && speed == 3) 
	        	return Sickness.EXHAUSTION; 			 //5% chance
	        if (roll >= 70) 
	        	return Sickness.FOOD_POISONING; 		 //5% chance
	        if (roll >= 60)
	        	return Sickness.FLU; //10% chance
	        if (roll >= 50 && weather == Weather.SUNNY)   //needs to be sunny to get hot
	        	return Sickness.HYPERTHERMIA; 		     //10% chance
	        if (roll >= 50 && weather == Weather.SNOWY)  //needs to be snowy to get cold
	        	return Sickness.HYPOTHERMIA; 		     //10% chance 
	        if (roll >= 30) 
	        	return Sickness.CHOLERA; 				 //20% chance, most common and most deadly peril
	        if (roll >= 20) 
	        	return Sickness.TYPHOID_FEVER; 			 //10% chance
	        if (roll >= 10) 
	        	return Sickness.DYSENTERY; 				 //10% chance 
	        return Sickness.TUBERCULOSIS; 				 //10% chance 
    	}
    	else {
    		return Sickness.NONE; //then the person is not sick at all
    	}
    }

    /**************
     *INJURY LOGIC*
     **************/
    
    /**
     * A method that checks if a person gets injured. This check does not rely on the current health. . 
     * @return true if that person gets injured, false if otherwise. 
     */
    public boolean getsInjured() {
    	//lower health values result in a greater chance of getting sick
        int injuredChance = 30; //there is a 30% chance of injury
        
        //now check if the randomly generated integer is less than the chance
        boolean isInjured = rng.nextInt(100) < injuredChance;
        //if so, that person is now sick
        return isInjured; 
    }
    
    /**
     * A method to check if a person suffers from an injury based on random chance. These chances do *not* rely on the health of the person. 
     * This check can then be used within the HealthPool to check if a person is suffering from an enum sickness. 
     * @return The enum a person suffers from based on random chance. 
     */
    public Injury determineIfInjured() {
        int roll = rng.nextInt(100);
        if (roll <= 15) 
        	return Injury.FIREARM_MISFIRE; //15% chance
        if (roll <= 30) 
        	return Injury.TRAMPLED; 	   //15% chance
        if (roll <= 50) 
        	return Injury.SNAKEBITE;       //20% chance
        if (roll <= 70) 
        	return Injury.BROKEN_BONE;	   //20% chance 
        return Injury.FELL_OFF_WAGON;  //30% chance
    }

    /**
     * Checks if the party, when crossing a river, will die. There is only two river crossing with a small chance of death, but it is possible to still die :-(. 
     * @return True if the party dies in the river. 
     */
    public boolean diesInRiver() {
    	int roll = rng.nextInt(100);
    	if (roll <= 10)  //10% chance of drowning
    		return true;
    	return false; 
    }
    
    // -- Ox Injury Logic --

    /* MAY OR MAY NOT BE NEEDED. WON'T TOUCH FOR NOW. 
    public boolean isOxInjured(Weather weather, int speed) {
        int chance = switch (weather) {
            case SUNNY -> switch (speed) {
                case 1 -> 5; case 2 -> 15; case 3 -> 20; default -> 0;
            };
            case WINDY, RAINY -> switch (speed) {
                case 1 -> 10; case 2 -> 20; case 3 -> 25; default -> 0;
            };
            case SNOWY, THUNDERSTORMS -> switch (speed) {
                case 1 -> 15; case 2 -> 25; case 3 -> 35; default -> 0;
            };
        };
        return rng.nextInt(100) < chance;
    } */
    
}
