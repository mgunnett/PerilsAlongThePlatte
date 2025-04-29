package perilsalongtheplatte;

import java.util.Random;

public class DailyEvents {

    private String playerGender;
    private final double WEATHER_EVENT_PROBABILITY = 0.3; // 30%
    private Random random;
    private int playerHealth;
    private boolean isSick;

    private Perils perils;

	public String YorNSick = "healthy";
    public String YorNRecovered = "";
    public String Penalty = "";
    public String Sickness = "";
    public String currentWeather = "Clear"; // Store today's weather

    private final String[] weatherEvents = {"Sunny", "Rainy", "Windy", "Thunderstorms", "Snowy"};

    public DailyEvents(String gender, int health) {
        this.playerGender = gender.toLowerCase();
        this.playerHealth = health;
        this.random = new Random();
        this.perils = new Perils();
        this.isSick = false; // default player health state
    }

    /**
     * Runs all daily event updates: weather, sickness, penalties
     */
    public void weatherEvents() {
        // Determine today's weather once per day
        currentWeather = handleWeatherEvent();

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
    }

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
}
