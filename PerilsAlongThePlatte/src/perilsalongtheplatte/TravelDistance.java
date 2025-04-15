package perilsalongtheplatte;
import java.util.Random;

/**
 * Calculates and controls all elements directly tied to the player's distance traveled in the Perils Along the Platte game.
 * <p>
 * The TravelDistance class contains methods that track the current distance the player has traveled,how many days they've traveled, 
 * the next landmark the player will come across, as well as what that landmark will be. The purpose of this class is to provide 
 * essential randomization and specific calculations that will interact with the other classes. 
 * 
 * @version 0.1 
 * @author Landon Bollenbacher
 * @since 03/26/2025
 */


public class TravelDistance {
	// declare global variables
		int day = 0;
		int pace = 0;
	/**
	 * Tracks the current distance traveled.
	 * */
	public int distanceTraveled() {
		int Distance = 0;
		int min = 10;
        int max = 20;
     // Create a Random object
        Random random = new Random();

        // Generate a random integer between min and max (inclusive)
        int randomNumber = random.nextInt(max - min + 1) + min;
		while (isStopped() == false) {
			for (int i = 0; i <= day; i++) {
				Distance = Distance + randomNumber;
			}
		}
			return Distance;
	}
	
	/**
	 * Tracks the number of days traveled.
	 * */
	public int daysTraveled(int day) {
		
		
		return day;
	}
	
	public boolean isStopped() {
		int Stopped = 0;
		if (Stopped == 0) {
			return false;
		}
		else
		return true;
	}
	
	
	/**
	 * LANDMARK ORDER
	 * 
	 * Kansas River
	 * 
	 * Big Blue River
	 * 
	 * Fort Kearny
	 * 
	 * Chimney Rock
	 * 
	 * Fort Laramie
	 */
	
	
	/**
	 * Determines if the player has reached the next landmark.
	 * */
	public boolean reachedLandmark(int n) {
		if (distanceTraveled())
		return true;
	
	}
	
	/**
	 * Determines if the current landmark is a river.
	 * */
	public boolean isRiver(int n) {
		if (distanceTraveled() <= 78 || distanceTraveled() > 78 && distanceTraveled() <= 137)
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Determines if the current landmark is a town.
	 * */
	public boolean isTown(int n) {
		
		return true;
	}
	
	/**
	 * Determines if the current landmark is a scenic landmark.
	 * */
	public boolean isScenicSpot(int n) {
	
		return true;
	}
}