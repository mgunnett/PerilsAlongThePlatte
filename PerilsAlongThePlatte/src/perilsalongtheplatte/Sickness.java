package perilsalongtheplatte;

/**
 * An enum to store the name of each of the various sickness statuses and the health penalty associated with it. 
 */
public enum Sickness {
    NONE(0), 
    FEVER(-1), //a fever results in -1 hp
    FLU(-2), 
    TYPHOID_FEVER(-3), 
    TUBERCULOSIS(-4),
    DYSENTERY(-5),
    CHOLERA(-25), 
    FOOD_POISONING(-1), 
    DEHYDRATION(-5), 
    MALNUTRITION(-5),
    HYPERTHERMIA(-2),
    HYPOTHERMIA(-2), 
    EXHAUSTION(-1);

	//constructor that set ups the penalty with its associated enum
    public final int penalty;
    Sickness(int penalty) { 
    	this.penalty = penalty; 
    }
    
    /**
     * Getter method to retrieve the penalty associated with a particular sickness label. 
     * @return the numeric representation of the penalty amount. 
     */
    public int getPenalty() {
    	return penalty; 
    }
}
