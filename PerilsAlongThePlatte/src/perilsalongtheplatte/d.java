package perilsalongtheplatte;
import java.util.Random;
/**
* This class sets and manipulates the initial stat values for each element, such as supplies, number of party members, etc.
* Some initial stats will differ between the two genders. Some stat values will remain constant between genders, but most values will be randomly
* generated for each playthrough. The player will buy some initial supplies, then this class will control the
* random deprecation of these; only men can buy supplies.
* @author Parker West
* @version 0.1
*/
public class StartingStats {
	//create a new random object
	Random random = new Random();
	public boolean isMale;
	
	
	/**
	 * A constructor that initializes variables to run the various methods
	 */
	public StartingStats(boolean isMale) {
		this.isMale = isMale;
	}
	/**
	Prompts the player to buy initial supplies for their journey, which will be randomly deprecated when the game starts. Only men can buy supplies (sorry!).
	@param isMale boolean representation of palyer's gender.
	*/
	public void buySupplies(boolean isMale) {
		return;
	}
	/**
	Sets the player's cash when the game starts using a random range of values.
	@param cash the player's current cash.
	*/
	public void setCash(int cash) {
		int rCash;
		do {
			rCash = random.nextInt(150) + 1; //generate a number range 1-150
		} while (rCash <= 150 && rCash >= 100); //guarantee that the lost cash value is between 100-250
		
		cash =- rCash; //set the new cash value by subtracting the randomly lost amount
	}
	
	//all below methods function similarly to the above setCash method, where each stat is randomly reduced within a set range
	
	/**
	Sets the player's food when the game starts using a random range of values.
	@param food the player's current amount of food.
	*/
	public void setFood(int food) {
		int rFood;
		do {
			rFood = random.nextInt(300) + 1;
		} while (rFood <= 250 && rFood >= 150);
		 food =- rFood;	
	}
	
	/**
	Sets the player's number of oxen when the game starts using a random range of values.
	@param oxen the number of oxen.
	*/
	public void setOxen(int oxen) {
		//this method uses range checks to determine a number of lost, since losing 4 oxen in a short period is not realistic.
		//instead, the play can only lose a max of 2 oxen, but more likely none.
		int rOxen = random.nextInt(100) + 1;
		if (rOxen <= 80)
			return;
		if (rOxen > 80 && rOxen <= 95)
			oxen -= 1; //lose 1 ox
		if (rOxen > 95 && rOxen <= 100)
			oxen -= 2; //lose 2 oxen
	}
	/**
	Sets the player's number of spare parts when the game starts using a random range of values.
	@param parts the player's current amount of spare parts.
	*/
	public void setParts(int parts) {
		parts -= random.nextInt(3); //player can lose 0-2 spare parts
		
	}
	/**
	Sets the player's amount of ammunition when the game starts using a random range of values.
	@param ammo the player's current amount of ammo.
	*/
	public void setAmmo(int ammo) {
		int rAmmo;
		do {
			rAmmo = random.nextInt(75) + 1;
		} while (rAmmo >= 45);
	}
	
	/**
	Sets the player's amount of medicine when the game starts using a random range of values.
	@param medicine the player's current amount of medicine.
	*/
	public void setMedicine(int medicine) {
			medicine -=  random.nextInt(20) + 1;
	}
	
	/**
	Sets the player's amount of clothing when the game starts using a random range of values.
	@param clothes the player's current amount of clothing.
	*/
	public void setClothes(int clothes) {
		int rClothes = random.nextInt() + 1;
		if (rClothes <= 60)
			return;
		if (rClothes > 60 && rClothes <= 90)
			clothes -= 1; //lose 1 set of clothing
		if (rClothes > 90 && rClothes <= 100)
			clothes -= 2; //lose 2 sets of clothes
	}
}
