package perilsalongtheplatte;

import perilsalongtheplatte.Perils;
import java.util.Random;

/**
 * As the player travels the trail, events can randomly happen to stimulate daily life along the Platte River. 
 * These events have a set probability of happening, and multiple events can happen per day. Some events have a 
 * different chance on happening based on the gender of the player. 
 */

public class DailyEvents {

    // Define player gender
    private String playerGender;

    // Probabilities for weather events
    private final double WEATHER_EVENT_PROBABILITY = 0.3; // 30% chance of weather event

    // Random number generator
    private Random random;

    // Player health (out of 100)
    private int playerHealth;
    boolean isSick;

    // Perils object to simulate sickness and events
    private Perils perils;

    // Constructor to set player gender and health
    public DailyEvents(String gender, int health) {
        this.playerGender = gender.toLowerCase();
        this.playerHealth = health;
        this.random = new Random();

        // Create an instance of Perils to handle sickness and penalties
        this.perils = new Perils();
    }

    public void weatherEvents() {
    	handleWeatherEvent();

        // Check if a weather event happens
        if (random.nextDouble() < WEATHER_EVENT_PROBABILITY) {
            handleWeatherEvent();
        }

        // Check if the player gets sick
        boolean gotSick = perils.getsSick(playerHealth);  // Pass health to getsSick method
        if (gotSick) {
            System.out.println("You got sick!");
        } else {
            System.out.println("You're healthy today.");
        }

        // Check if sickness gets better
        if (gotSick && perils.getsBetter(isSick, playerHealth)) {
            System.out.println("You recovered from your sickness.");
        } else if (gotSick) {
            System.out.println("You are still sick.");
        }

        // Apply sickness penalty
        int sicknessPenalty = perils.sicknessPenalty(gotSick, "good"); // Pass parameters
        if (sicknessPenalty > 0) {
            System.out.println("You suffer a sickness penalty of: " + sicknessPenalty);
        } else {
            System.out.println("You're not suffering from any sickness right now.");
        }
    }

    // Handle weather event
    private void handleWeatherEvent() {
    	// List of weather events
    	String[] weatherEvents = {"Sunny", "Rainy", "Windy", "Thunderstorms", "Snowy"};

    	// Determines if a weather event will happen
        if (random.nextDouble() < WEATHER_EVENT_PROBABILITY) {
            String event = weatherEvents[random.nextInt(weatherEvents.length)];
            System.out.println("The weather is " + event);
        } else {
            System.out.println("The weather is clear.");
        }
    }

    // Main method to test
    public static void main(String[] args) {
        // Test for male player with health 75
        DailyEvents malePlayer = new DailyEvents("male", 75);
        malePlayer.weatherEvents();

        // Test for female player with health 60
        DailyEvents femalePlayer = new DailyEvents("female", 60);
        femalePlayer.weatherEvents();
    }
}
