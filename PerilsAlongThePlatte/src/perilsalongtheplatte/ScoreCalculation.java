package perilsalongtheplatte;

/**
 * This class calculates the player's running score. The calculation is as follows: 
 * (supplies + Health) * (Members * Gender) + (days * n). Supplies is the total amount of supplies, health is the total health pool, 
 * members is the number of surviving members, gender is represented by a constant and grants female players more score, 
 * days is the number of days in the trip, and n is a constant. The score value can only be an integer.
*/
public class ScoreCalculation {
	//declare some instance variables
	private double supplies;
	private double health;
	private int members;
	private int days; 
	private boolean isMale;
	
	//a constructor to create the objects in memory
	public ScoreCalculation(double supplies, double health, int members, int days, boolean isMale){
		this.supplies = supplies; 
		this.health = health;
		this.members = members; 
		this.days = days;
		this.isMale = isMale; 
	}
	
/**
 * This class calculates the score of the player, using the variables listed in the class description. The formula is: 
 * (supplies + Health) * (Members * Gender) + (days * n)
 * @return score the player's current score
 */
public double getScore() {
	//declare the gender variable and set value differently based on if player is a male or female.
	//playing as a female will award more points. 
	int gender; 
	if (isMale) 
		gender = 1;
	else 
		gender = 2; 
	
	final int N = 100; //an arbitrary constant that can be modified to change the value of the score.
				 //this constant is useful if you want to adjust the score from the 100s to the 1000s. 
	return (health / supplies) * (members * gender) + days * N; 
	
	//return (supplies + health) * (members * gender) + (days * N);
}
	
	
	
}
