package perilsalongtheplatte;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;

public class TravelDistance {
	
	//declare public variables
		public int day = 0;
		public int pace = 0;
		public String date = "";
		private int dayTime = 0;
		private int distance = 0;
		private boolean isStopped = false;
		public String landmark; 
		private Timer dayTimer;
		private Random random = new Random();
		private Popups popup = new Popups(); 
		private GAME game;
		private final int[] landmarkRangeStarts = {70, 130, 355, 610, 680};
		private final int[] landmarkRangeEnds = {85, 145, 370, 625, 700};
		private final String[] landmarkNames = {
			    "Kansas River", "Big Blue River", "Fort Kearny", "Chimney Rock", "Fort Loramie"
			};
		
		
	/**
	 * A constructor that creates a Timer object with the amount of REAL time an in-game day will take. 
	 * @param updateCallback - allows you to "callback" to the function to set a label in the GUI
	 */
		
	public TravelDistance(Runnable updateCallback) {
		int delay = 3000; // This is 3 seconds for each in-game day
		int speed = 2;

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
		               if (pace == 1) {
		            	   distance += random.nextInt(4) + 3; // Adds 3-6 miles
		               }
		               if (pace == 2) {
		            	   distance += random.nextInt(5) + 7; // Adds 7-13
		               }
		               if (pace == 3) {
		            	   distance += random.nextInt(4) + 12; // Adds 12-15 miles
		               }
		               System.out.println(distance);
		              

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
	
	public void setPace(int pace) {
	    // validate pace if needed here
	    this.pace = pace;
	}
	
	// For the date code
	public String date() {
		String month = "";
		int dayDate = 0;
		
		if(day == 0) {
			month = "February";
			dayDate = 28;}
		
		if (day >= 1 && day < 32) {
			month = "March";
			dayDate = day;}
		
		if (day >= 32 && day < 62) {
			month = "April";
			dayDate = day - 31;}
		
		if (day >= 62 && day < 93) {
			month = "May";
			dayDate = day - 61;}
		
		if (day >= 93 && day < 123) {
			month = "June";
			dayDate = day - 92;}
		
		if (day >= 123 && day < 154) {
			month = "July";
			dayDate = day - 122;}
		
		if (day >= 154 && day < 185) {
			month = "August";
			dayDate = day - 153;}
		
		if (day >= 185 && day < 215) {
			month = "September";
			dayDate = day - 184;}
		
		if (day >= 215 && day < 246) {
			month = "October";
			dayDate = day - 214;}
		
		if (day >= 246 && day < 276) {
			month = "November";
			dayDate = day - 245;}
		
		if (day >= 276 && day < 307) {
			month = "December";
			dayDate = day - 275;}
	
		date = month + " " + dayDate + ", 1872";
		return date;
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

	 public int getMilesLeftToNextLandmark() {
	     int d = distanceTraveled();

	     for (int i = 0; i < landmarkRangeStarts.length; i++) {
	         if (d < landmarkRangeStarts[i]) {
	             return landmarkRangeStarts[i] - d;  // distance left before next landmark range
	         }
	         // If currently inside the range, zero miles left
	         if (d >= landmarkRangeStarts[i] && d < landmarkRangeEnds[i]) {
	             return 0;
	         }
	     }
	     return 0; // No more landmarks => 0 miles left
	 }
	 
	 public String getNextLandmarkName() {
		    int d = distanceTraveled();

		    for (int i = 0; i < landmarkRangeStarts.length; i++) {
		        if (d < landmarkRangeStarts[i]) {
		            return landmarkNames[i];
		        }
		        if (d >= landmarkRangeStarts[i] && d < landmarkRangeEnds[i]) {
		            return landmarkNames[i]; // currently at this landmark
		        }
		    }
		    return "None"; // no further landmarks
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