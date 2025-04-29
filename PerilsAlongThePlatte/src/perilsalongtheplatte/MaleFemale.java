package perilsalongtheplatte;

/**
 * This class stores and handles the main differences between a male & female player. Male players, the "normal mode" will start with more money, meaning
 * more buying power. Females cannot buy supplies (sorry!) but it is determined randomly by "the husband." Being female also comes with random pregnancy and 
 * rewards more points. 
 * @version 1.0
 * @author Parker West
 */
public class MaleFemale {
    private boolean isMale;
    public MaleFemale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isMale() { return isMale; }

    public boolean isFemale() { return !isMale; }
}

