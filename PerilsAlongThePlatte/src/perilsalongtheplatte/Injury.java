package perilsalongtheplatte;

/**
 * This enum stores each type of injury perils and its corresponding health penalty. 
 */
public enum Injury {
    NONE(0), 
    FELL_OFF_WAGON(15),//falling off the wagon causes the party member to lose 15 health 
    BROKEN_BONE(20), 
    SNAKEBITE(30),
    TRAMPLED(80),
    DROWNED(100), 
    FIREARM_MISFIRE(75);

    public final int penalty;
    //constructor to associate each penalty loss value to an enum
    Injury(int penalty) { 
    	this.penalty = penalty; 
    }
    
    /**
     * A getter to get the penalty value associated to a particular enum. 
     * @return The sickenss penalty of that enum. 
     */
    public int getPenalty() {
    	return penalty; 
    }
}