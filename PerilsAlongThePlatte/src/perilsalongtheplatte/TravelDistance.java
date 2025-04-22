package perilsalongtheplatte;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;

public class TravelDistance {
	
	//declare public variables
		public int day = 0;
		public int pace = 0;
		private int dayTime = 0;
		private int distance = 0;
		private boolean isStopped = false;
		public String landmark; 
		private Timer dayTimer;
		private Random random = new Random();
		private Popups popup = new Popups(); 
		
	/**
	 * A constructor that creates a Timer object with the amount of REAL time an in-game day will take. 
	 * @param updateCallback - allows you to "callback" to the function to set a label in the GUI
	 */
		
	public TravelDistance(Runnable updateCallback) {
		int delay = 5000; // This is 5 seconds for each in-game day

		dayTimer = new Timer(delay, null);
		dayTimer.setRepeats(false); // this makes sure the timer resets

		dayTimer.addActionListener(new ActionListener() {
			// Overrides the current settings no matter what they are
		     @Override
		     public void actionPerformed(ActionEvent e) {
		    	// if the timer is stopped, it will add a day (it stops at 10 seconds)
		    	 if (!isStopped) {
		               day++;
		               // will update with pace later on
		               distance += random.nextInt(11) + 5; // Adds 5–15 miles

		         if (updateCallback != null) {
		        	 	updateCallback.run(); // UI update
		         }
		         
		         // if you want to test... print here to console - Megan c:

		         // Restart the timer for the next day
		         if(reachedLandmark(distance))
		        	 popup.landmarkPopup(landmark);
		        	 
		         dayTimer.restart();
		         }
		     }
	     });


	}

	public void startTimer() {
		dayTimer.start();
	}
	
	/**
	 * Tracks the current distance traveled.
	 * */
	public int distanceTraveled() {
		return distance;
	}
	
	/**
	 * Tracks the number of days traveled.
	 * */
	public int daysTraveled() {
	   return day;
	}

	// Can later on be used to help with resting
	public void stopTravel() { //bool?
		isStopped = true;
		dayTimer.stop();
	}
	
	public void resumeTravel() {
		if (isStopped) {
			isStopped = false;
			dayTimer.restart();
		}
	}
	
	public boolean isStopped() {
		return isStopped;
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
	 * Fort Loramie
	 * 
	 * Reference this order in relation to the reachedLandmark function below
	 *                                |
	 *                                V
	 */
	
	
	/**
	 * Determines if the player has reached the next landmark.
	 * */
	 public boolean reachedLandmark(int n) {
	        int d = distanceTraveled(); //get the distance traveled
	        
	        //check if the wagon is within range of a certain landmark and report its string value
	        if (d >= 70 && d < 85) {
	        	landmark = "Kansas River"; 
	        	return true;
	        }
	        if (d >= 130 && d < 145) {
	        	landmark = "Big Blue River"; 
	        	return true;
	        }
	        if (d >= 355 && d < 370) {
		        landmark = "Fort Kearny"; 
	        	return true;
	        }
	        if (d >= 610 && d < 625) {
	        	landmark = "Chimney Rock";
	        	return true; 
	        }
	        if (d >= 680 && d < 700) {
	        	landmark = "Fort Loramie";
	        	return true; 
	        }
	        
	        return false; //then no landmark has been reached
	        
	       /* return (d >= 70 && d < 85) || (d >= 130 && d < 145) ||
	               (d >= 355 && d < 370) || (d >= 610 && d < 625) ||
	               (d >= 680 && d < 700);*/
	        
	    }
	
	/**
	 * Determines if the current landmark is a river.
	 * */
	  public boolean isRiver(int n) {
	        int d = distanceTraveled();
	        return d <= 78 || (d > 78 && d <= 137);
	    }
	
	/**
	 * Determines if the current landmark is a town.
	 * */
	  public boolean isTown(int n) {
	        int d = distanceTraveled();
	        return (d > 137 && d <= 359) || (d > 615 && d <= 689);
	    }
	
	/**
	 * Determines if the current landmark is a scenic landmark.
	 * */
	    public boolean isScenicSpot(int n) {
	        int d = distanceTraveled();
	        return d > 359 && d <= 615;
	    }
}