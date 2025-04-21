package perilsalongtheplatte;
import java.util.Random;


public class TravelDistance {
	
	TravelDistance(){
		;
	}
	
	// declare global variables
		public int day = 0;
		public int pace = 0;
	/**
	 * Tracks the current distance traveled.
	 * */
	public int distanceTraveled() {
		int Distance = 0;
		int min = 5;
        int max = 15;
     // Create a Random object
        Random random = new Random();

        // Generate a random integer between min and max (inclusive)
        int randomNumber = random.nextInt(max - min + 1) + min;
		while (isStopped() == false) {
			for (int i = 0; i <= day; i++) {
				Distance += randomNumber;
			}
		}
			return Distance;
	}
	
	/**
	 * Tracks the number of days traveled.
	 * */
	public int daysTraveled(int unused) {
	    java.util.Timer timer = new java.util.Timer();

	    timer.scheduleAtFixedRate(new java.util.TimerTask() {
	        public void run() {
	            while (!isStopped()) {
	                day++;
	            } 
	        }
	    }, 0, 1000);

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
	 * 
	 * Reference this order in relation to the reachedLandmark function below
	 *                                |
	 *                                V
	 */
	
	
	/**
	 * Determines if the player has reached the next landmark.
	 * */
	public boolean reachedLandmark(int n) {
		if (distanceTraveled() >= 70 && distanceTraveled() < 85 || distanceTraveled() >= 130 && distanceTraveled() < 145 || distanceTraveled() >= 355 && distanceTraveled() < 370 || distanceTraveled() >= 610 && distanceTraveled() < 625 || distanceTraveled() >= 680 && distanceTraveled() < 700)
		{
			return true;
		}
		else
			return false;
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
		if (distanceTraveled() > 137 && distanceTraveled() <= 359 || distanceTraveled() > 615 && distanceTraveled() <=689 )
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Determines if the current landmark is a scenic landmark.
	 * */
	public boolean isScenicSpot(int n) {
		if (distanceTraveled() > 359 && distanceTraveled() <= 615)
		{
			return true;
		}
		else
			return false;
	}
}