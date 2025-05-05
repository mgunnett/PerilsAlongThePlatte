package perilsalongtheplatte;

import java.util.Random;

public class DailyEvents {
	
	private TravelDistance travelDistance;

    private final double WEATHER_EVENT_PROBABILITY = 0.3; // 30%
    private Random random;
    private int playerHealth;
    private boolean isSick;

    private Perils perils;
    private Party party; 

	public String YorNSick = "healthy";
    public String YorNRecovered = "";
    public String Penalty = "";
    public String Sickness = "";
    public String currentWeather = "Clear"; // Store today's weather

    private final String[] weatherEvents = {"Sunny", "Rainy", "Windy", "Thunderstorms", "Snowy"};

    public DailyEvents(int health, TravelDistance travelDistance, Party party) {
        this.playerHealth = health;
        this.random = new Random();
        this.perils = new Perils();
        this.isSick = false; // default player health state
        this.travelDistance = travelDistance;
        this.party = party; 
    }

    /**
     * Runs all daily event updates: weather, sickness, penalties
     */
    public void updateEvents() {
        // Determine today's weather once per day
        currentWeather = handleWeatherEvent();

        //run through a for-each loop checking through each party member
        for (Pioneer p : party.getParty()) {
        	
        	boolean gotSick = p.isSick(); 
        	isSick = gotSick; //update sickness status for next day use
            YorNSick = gotSick ? "sick" : "healthy"; //string declaration of sickness state
        	
         // Sickness improvement
            if (gotSick && perils.getsBetter(p.isSick(), p.getHealth())) {
                YorNRecovered = "healed!";
                p.setRecoveredStatus(false); // pioneer healed, update status
            } else if (gotSick) {
                YorNRecovered = "still sick";
            } else {
                YorNRecovered = ""; // no change
            }

            // Apply sickness penalty
            int sicknessPenalty = p.getSicknessType().getPenalty(); 
            if (sicknessPenalty > 0) {
                Penalty = sicknessPenalty + " Health Lost!";
                Sickness = "You are suffering from: " + p.getSicknessType().toString().toLowerCase();
            } else {
                Penalty = "";
                Sickness = "You're not suffering from any sickness right now.";
            }

            // Handle health penalty for this pioneer
            p.changeHealth(sicknessPenalty);
        }
    }
        
        /*
        // Determine sickness state for the day
        boolean gotSick = perils.getsSick(playerHealth, isSick);
        isSick = gotSick; // update sickness status for next day use
        YorNSick = gotSick ? "sick" : "healthy";

        // Sickness improvement
        if (gotSick && perils.getsBetter(isSick, playerHealth)) {
            YorNRecovered = "healed!";
            isSick = false; // player healed, update status
        } else if (gotSick) {
            YorNRecovered = "still sick";
        } else {
            YorNRecovered = ""; // no change
        }

        // Apply sickness penalty
        int sicknessPenalty = perils.sicknessPenalty(gotSick, "good");
        if (sicknessPenalty > 0) {
            Penalty = sicknessPenalty + " Health Lost!";
            Sickness = "You are suffering from: " + perils.sickness;
        } else {
            Penalty = "";
            Sickness = "You're not suffering from any sickness right now.";
        }
        
    }*/

    /**
     * Determines the weather string for the current day
     */
    public String handleWeatherEvent() {
        if (random.nextDouble() < WEATHER_EVENT_PROBABILITY) {
            return weatherEvents[random.nextInt(weatherEvents.length)];
        } else {
            return "Clear";
        }
    }
    /**
     * Gets the status of the player's sickness.
     * @return a string stating the player's sickness status. 
     */
    public String getYorNSick() {
        return YorNSick;
    }

    /**
     * Gets the string representing if the player continues to be sick. 
     * @return a string stating if the player continues to be sick. 
     */
    public String getYorNRecovered() {
        return YorNRecovered;
    }
    
    /**
     * Gets the string representing the sickness penalty the player currently suffers from.
     * @return a string stating if the player suffers from a penalty. 
     */
    public String getPenalty() {
        return Penalty;
    }
    
    
    /**
     * Get the string representing what sickness the player suffers from. 
     * @return a string stating if the player suffers from a particular peril. 
     */
    public String getSickness() {
        return Sickness;
    }
    
    /**
     * Get the string representing the current weather status. 
     * @return a string containing the current weather status. 
     */
    public String getCurrentWeather() { 
    	return currentWeather; 
    	}

    /**
     * Gets just the month name from the date() method in TravelDistance
     * @return a string containing the current month
     * */
//    private String extractMonth(String dateStr) {
//        return dateStr.split(" ")[0];
//    }
    
}
